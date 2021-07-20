package UI;

import Clients.readThread;
import Code.Club;
import Code.Player;
import Code.SearchPlayer;
import Code.Utility;
import Transferables.transferClub;
import Transferables.transferMarket;
import Transferables.transferPlayer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class ClubHomeCtrl{
    public Tab hTab;
    public Tab msTab;
    public Tab cTab;
    public Tab tTAb;
    public TextField searchField2;
    public Button searchBoob;
    public TabPane tabPane;
    private Main main;
    private Club c;
    private List<Player> marketListPlayers;
    NetworkUtil networkUtil;

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
        clubTitle.setText(main.club.getName());
        tabPane.getSelectionModel().select(indx);
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
        //
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
            tmp= c.getPlayers().get((int) o);
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
        myPlayers.getItems().clear();
        myPlayers.getItems().addAll(main.getClub().getPlayers());
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


    }

    public void squadTab(Event event) {
        if(msTab.isSelected()) {
            c = main.getClub();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(c.getPlayers());
        }
    }

    public void homeTab(Event event) {


    }

    public void searchButt(ActionEvent actionEvent) {
        String s=(String) searchMode.getSelectionModel().getSelectedItem();
        if(s.equals("All Players")){

        }
        else if(s.equals("Search By Name")){
            String ss=searchField.getText();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(SearchPlayer.searchByName(ss,main.getClub().getPlayers()));
        }
        else if(s.equals("By Club and Country")){
            String ss=searchField.getText();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(SearchPlayer.searchByCountryNClub(ss,main.getClub().getName(),main.getClub().getPlayers()));
        }
        else if(s.equals("Search By Position")){
            String ss=searchField.getText();
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(SearchPlayer.searchByPosition(ss,main.getClub().getPlayers()));
        }
        else if(s.equals("Search By Salary Range")) {
            String ss = searchField.getText();
            String sss = searchField2.getText();
            if (Utility.isInt(ss) && Utility.isInt(sss)) {
                myPlayers.getItems().clear();
                myPlayers.getItems().addAll(SearchPlayer.searchBySalaryRange(Integer.parseInt(sss), Integer.parseInt(ss), main.getClub().getPlayers()));
            }
        }
    }

    public void gg(ActionEvent event){
        String s=searchMode.getValue();
        if(s.equals("All Players")){
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(main.getClub().getPlayers());
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(false);
            searchBoob.setDisable(true);
            searchField.setPromptText("");
            searchField2.setPromptText("");
        }
        else if(s.equals("Search By Name")){
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(main.getClub().getPlayers());
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(true);
            searchBoob.setDisable(false);
            searchField.setPromptText("Enter Name");
            searchField2.setPromptText("");
        }
        else if(s.equals("By Country")){
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(main.getClub().getPlayers());
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(true);
            searchBoob.setDisable(false);
            searchField.setPromptText("Enter Country");
            searchField2.setPromptText("");
        }
        else if(s.equals("Search By Position")){
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(main.getClub().getPlayers());
            searchField.clear();
            searchField2.clear();
            searchField2.setEditable(false);
            searchField.setEditable(true);
            searchBoob.setDisable(false);
            searchField.setPromptText("Enter Position");
            searchField2.setPromptText("");
        }
        else if(s.equals("Search By Salary Range")){
            myPlayers.getItems().clear();
            myPlayers.getItems().addAll(main.getClub().getPlayers());
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
}