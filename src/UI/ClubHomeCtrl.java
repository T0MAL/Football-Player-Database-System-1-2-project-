package UI;

import Code.*;
import Transferables.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ClubHomeCtrl{
    public Tab hTab;
    public Tab msTab;
    public Tab cTab;
    public Tab tTAb;
    public TextField searchField2;
    public Button searchBoob;
    public TabPane tabPane;
    public ChoiceBox<String> searchModeClub;
    public ListView myPlayersClub;
    public Label yearlySalary;
    public ListView countryWiseCount;
    public ImageView clubImage;
    private Main main;
    private List<Player> marketListPlayers;
    private List<Player> squadListPlayers;
    private List<Player> clubListPlayers;
    private List<Country> ccList=new ArrayList<>();

    @FXML
    private Label clubTitle;
    @FXML
    private ListView marketList;
    @FXML
    private ListView myPlayers;
    @FXML
    private TextField searchField;
    @FXML
    private ChoiceBox <String> searchMode;

    public void setMain(Main main) {
        this.main = main;
    }

    public void init(int indx){
        clubTitle.setText(main.club.getName().toUpperCase(Locale.ROOT));
        clubImage.setImage(main.getClub().getImage());
        searchMode.getItems().addAll("All Players","Search By Name","Search By Country","Search By Position",
                    "Search By Salary Range");
        searchModeClub.getItems().addAll("All Players","Player(s) with the maximum salary of a club","Player(s) with the maximum age of a club","Player(s) with the maximum height of a club");
        searchMode.setValue("All Players");
        searchModeClub.setValue("All Players");
        searchMode.setOnAction(this::gg);
        searchModeClub.setOnAction(this::wp);
        squadListPlayers=main.getClub().getPlayers();
        myPlayers.getItems().addAll(squadListPlayers);
        clubListPlayers=main.getClub().getPlayers();
        myPlayersClub.getItems().addAll(clubListPlayers);
        ccList=SearchPlayer.countryWiseCount(main.getClub().getPlayers());
        countryWiseCount.getItems().addAll(ccList);
        yearlySalary.setText("Total yearly salary of "+main.getClub().getName()+" is "+String.format("%.2f",SearchClub.totalSalary(main.getClub().getName(),main.getClub().getPlayers())));
        if(indx!=0){
            tabPane.getSelectionModel().select(indx);
        }
    }

    public void ccHandle(MouseEvent mouseEvent) {
        searchMode.setValue("Search By Country");
        ObservableList selectedIndices = countryWiseCount.getSelectionModel().getSelectedIndices();
        Country tmp = null;
        for(Object o : selectedIndices){
            tmp= ccList.get((int) o);
        }
        searchField.setText(tmp.getName());
        tabPane.getSelectionModel().select(1);
    }

    private void wp(Event event) {
            String s=(String) searchModeClub.getValue();
            if(s.equals("All Players")){
                clubListPlayers=main.getClub().getPlayers();
                myPlayersClub.getItems().clear();
                myPlayersClub.getItems().addAll(clubListPlayers);
            }
            else if(s.equals("Player(s) with the maximum salary of a club")){
                myPlayersClub.getItems().clear();
                clubListPlayers= SearchClub.maxSalary(main.getClub().getName(),main.getClub().getPlayers());
                myPlayersClub.getItems().addAll(clubListPlayers);
            }
            else if(s.equals("Player(s) with the maximum age of a club")){
                myPlayersClub.getItems().clear();
                clubListPlayers= SearchClub.maxAge(main.getClub().getName(), main.getClub().getPlayers());
                myPlayersClub.getItems().addAll(clubListPlayers);
            }
            else if(s.equals("Player(s) with the maximum height of a club")){
                myPlayersClub.getItems().clear();
                clubListPlayers= SearchClub.maxHeight(main.getClub().getName(), main.getClub().getPlayers());
                myPlayersClub.getItems().addAll(clubListPlayers);
            }

        }

    synchronized public void getMarketButt(ActionEvent actionEvent) throws InterruptedException {
        transferMarket tm=new transferMarket();
        try {
            main.getNetworkUtil().write(tm);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logOut(ActionEvent actionEvent) {
        try {
            main.getNetworkUtil().write(new transferLogOUT(main.getClub().getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleMouseClick(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerProfile.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }


        ObservableList selectedIndices = myPlayers.getSelectionModel().getSelectedIndices();
        Player tmp = null;
        for(Object o : selectedIndices){
            tmp= squadListPlayers.get((int) o);
        }
        PlayerProfileCtrl ctrl = loader.getController();
        ctrl.setMain(main);
        ctrl.init(tmp);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(root, 600, 300);
        dialogScene.getStylesheets().add(getClass().getResource("Pro.css").toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void reset(ActionEvent actionEvent) {
        squadListPlayers=main.getClub().getPlayers();
        myPlayers.getItems().clear();
        myPlayers.getItems().addAll(squadListPlayers);
        searchField.clear();
        searchField2.clear();
    }

    public void transfersTab(Event event) {
        if(tTAb.isSelected()) {
            main.setInMarket(true);
            marketListPlayers = main.getMarketListPlayers();
            marketList.getItems().clear();
            if (marketListPlayers != null)
                marketList.getItems().addAll(marketListPlayers);
        }
        else{
            main.setInMarket(false);
        }
    }

    public void clubTab(Event event) {
        if(cTab.isSelected()) {
            if(searchModeClub.getValue().equals("All Players")){
                clubListPlayers=main.getClub().getPlayers();
                myPlayersClub.getItems().clear();
                myPlayersClub.getItems().addAll(clubListPlayers);
            }
            else if(searchModeClub.getValue().equals("Player(s) with the maximum salary of a club")){
                myPlayersClub.getItems().clear();
                clubListPlayers= SearchClub.maxSalary(main.getClub().getName(), main.getClub().getPlayers());
                myPlayersClub.getItems().addAll(clubListPlayers);
            }
            else if(searchModeClub.getValue().equals("Player(s) with the maximum age of a club")){
                myPlayersClub.getItems().clear();
                clubListPlayers= SearchClub.maxAge(main.getClub().getName(), main.getClub().getPlayers());
                myPlayersClub.getItems().addAll(clubListPlayers);
            }
            else if(searchModeClub.getValue().equals("Player(s) with the maximum height of a club")){
                myPlayersClub.getItems().clear();
                clubListPlayers= SearchClub.maxHeight(main.getClub().getName(), main.getClub().getPlayers());
                myPlayersClub.getItems().addAll(clubListPlayers);
            }
        }
    }

    public void squadTab(Event event) {
        if(msTab.isSelected()) {
            if(searchMode.getValue().equals("All Players")){
                squadListPlayers=main.getClub().getPlayers();
                myPlayers.getItems().clear();
                myPlayers.getItems().addAll(squadListPlayers);
//                searchField.clear();
//                searchField2.clear();
                searchField2.setEditable(false);
                searchField.setEditable(false);
                searchBoob.setDisable(true);
                searchField.setPromptText("");
                searchField2.setPromptText("");
            }
            else if(searchMode.getValue().equals("Search By Name")){
                String ss=searchField.getText();
                myPlayers.getItems().clear();
                squadListPlayers=SearchPlayer.searchByName(ss,main.getClub().getPlayers());
                myPlayers.getItems().addAll(squadListPlayers);
//                searchField.clear();
//                searchField2.clear();
                searchField2.setEditable(false);
                searchField.setEditable(true);
                searchBoob.setDisable(false);
                searchField.setPromptText("Enter Name");
                searchField2.setPromptText("");
            }
            else if(searchMode.getValue().equals("Search By Country")){
                String ss=searchField.getText();
                myPlayers.getItems().clear();
                squadListPlayers=(SearchPlayer.searchByCountryNClub(ss,main.getClub().getName(),main.getClub().getPlayers()));
                myPlayers.getItems().addAll(squadListPlayers);
//                searchField.clear();
//                searchField2.clear();
                searchField2.setEditable(false);
                searchField.setEditable(true);
                searchBoob.setDisable(false);
                searchField.setPromptText("Enter Country");
                searchField2.setPromptText(null);
            }
            else if(searchMode.getValue().equals("Search By Position")){
                String ss=searchField.getText();
                myPlayers.getItems().clear();
                squadListPlayers=SearchPlayer.searchByPosition(ss,main.getClub().getPlayers());
                myPlayers.getItems().addAll(squadListPlayers);
//                searchField.clear();
//                searchField2.clear();
                searchField2.setEditable(false);
                searchField.setEditable(true);
                searchBoob.setDisable(false);
                searchField.setPromptText("Enter Position");
                searchField2.setPromptText("");
            }
            else if(searchMode.getValue().equals("Search By Salary Range")){
                String ss = searchField.getText();
                String sss = searchField2.getText();
                if (Utility.isInt(ss) && Utility.isInt(sss)) {
                    myPlayers.getItems().clear();
                    squadListPlayers=SearchPlayer.searchBySalaryRange(Integer.parseInt(sss), Integer.parseInt(ss), main.getClub().getPlayers());
                    myPlayers.getItems().addAll(squadListPlayers);
                }
//                searchField.clear();
//                searchField2.clear();
                searchField2.setEditable(true);
                searchField.setEditable(true);
                searchBoob.setDisable(false);
                searchField.setPromptText("Enter maximum salary");
                searchField2.setPromptText("Enter minimum salary");
            }
        }
    }

    public void homeTab(Event event) {
        //ccList=SearchPlayer.countryWiseCount(main.getClub().getPlayers());
        //countryWiseCount.getItems().clear();
       // countryWiseCount.getItems().addAll(ccList);
        //yearlySalary.setText("Total yearly salary of "+main.getClub().getName()+" is "+String.format("%.2f",SearchClub.totalSalary(main.getClub().getName(),main.getClub().getPlayers())));

    }

    public void searchButt(ActionEvent actionEvent) {
        String s=(String) searchMode.getSelectionModel().getSelectedItem();
        if(s.equals("All Players")){
        }
        else if(s.equals("Search By Name")){
            String ss=searchField.getText();
            myPlayers.getItems().clear();
            squadListPlayers=SearchPlayer.searchByName(ss,main.getClub().getPlayers());
            myPlayers.getItems().addAll(squadListPlayers);
        }
        else if(s.equals("Search By Country")){
            String ss=searchField.getText();
            myPlayers.getItems().clear();
            squadListPlayers=(SearchPlayer.searchByCountryNClub(ss,main.getClub().getName(),main.getClub().getPlayers()));
            myPlayers.getItems().addAll(squadListPlayers);
        }
        else if(s.equals("Search By Position")){
            String ss=searchField.getText();
            myPlayers.getItems().clear();
            squadListPlayers=SearchPlayer.searchByPosition(ss,main.getClub().getPlayers());
            myPlayers.getItems().addAll(squadListPlayers);
        }
        else if(s.equals("Search By Salary Range")) {
            String ss = searchField.getText();
            String sss = searchField2.getText();
            if (Utility.isInt(ss) && Utility.isInt(sss)) {
                myPlayers.getItems().clear();
                squadListPlayers=SearchPlayer.searchBySalaryRange(Integer.parseInt(sss), Integer.parseInt(ss), main.getClub().getPlayers());
                myPlayers.getItems().addAll(squadListPlayers);
            }
        }
    }

    public void gg(ActionEvent event){
        String s=searchMode.getValue();
        if(s.equals("All Players")){
            squadListPlayers=main.getClub().getPlayers();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(squadListPlayers);
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(false);
            searchBoob.setDisable(true);
            searchField.setPromptText("");
            searchField2.setPromptText("");
        }
        else if(s.equals("Search By Name")){
            squadListPlayers=main.getClub().getPlayers();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(squadListPlayers);
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(true);
            searchBoob.setDisable(false);
            searchField.setPromptText("Enter Name");
            searchField2.setPromptText("");
        }
        else if(s.equals("Search By Country")){
            squadListPlayers=main.getClub().getPlayers();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(squadListPlayers);
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(true);
            searchBoob.setDisable(false);
            searchField.setPromptText("Enter Country");
            searchField2.setPromptText(null);
        }
        else if(s.equals("Search By Position")){
            squadListPlayers=main.getClub().getPlayers();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(squadListPlayers);
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(true);
            searchBoob.setDisable(false);
            searchField.setPromptText("Enter Position");
            searchField2.setPromptText("");
        }
        else if(s.equals("Search By Salary Range")){
            squadListPlayers=main.getClub().getPlayers();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(squadListPlayers);
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(true);
            searchField.setEditable(true);
            searchBoob.setDisable(false);
            searchField.setPromptText("Enter maximum salary");
            searchField2.setPromptText("Enter minimum salary");
        }
    }

    public void handleMouseClick2(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerProfileMarket.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }


        ObservableList selectedIndices = marketList.getSelectionModel().getSelectedIndices();
        Player tmp = null;
        for(Object o : selectedIndices){
            tmp= marketListPlayers.get((int) o);
        }
        PlayerProfileMarketCtrl ctrl = loader.getController();
        ctrl.setMain(main);
        ctrl.init(tmp);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(root, 600, 300);
        dialogScene.getStylesheets().add(getClass().getResource("Pro.css").toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void handleMouseClick3(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerProfile.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }


        ObservableList selectedIndices = myPlayersClub.getSelectionModel().getSelectedIndices();
        Player tmp = null;
        for(Object o : selectedIndices){
            tmp= clubListPlayers.get((int) o);
        }
        PlayerProfileCtrl ctrl = loader.getController();
        ctrl.setMain(main);
        ctrl.init(tmp);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(root, 600, 300);
        dialogScene.getStylesheets().add(getClass().getResource("Pro.css").toExternalForm());
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void refreshSquad(ActionEvent event) {
        try {
            main.getNetworkUtil().write(new transferClub(main.getClub().getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshClub(ActionEvent event) {
        try {
            transferClub tc=new transferClub("Club");
            tc.setClub(main.getClub());
            main.getNetworkUtil().write(tc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}