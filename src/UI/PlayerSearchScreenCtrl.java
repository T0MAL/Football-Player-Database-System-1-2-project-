package UI;


import Code.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

public class PlayerSearchScreenCtrl {
    private Main main;
    private FootballAssociation fa;
    @FXML
    private RadioButton oCountry;
    @FXML
    private Label empty1;
    @FXML
    private Label empty2;
    @FXML
    private Label empty3;
    @FXML
    private Label empty4;
    @FXML
    private Label positionNotFound;
    @FXML
    private Label ccNotFound;
    @FXML
    private Label srNotFound;
    @FXML
    private Label nameNotFound;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab nameTab;
    @FXML
    private TextField nameKey;
    @FXML
    private Tab positionTab;
    @FXML
    private TextField positionKey;
    @FXML
    private Tab ccTab;
    @FXML
    private TextField countryKey;
    @FXML
    private TextField clubKey;
    @FXML
    private Tab srTab;
    @FXML
    private TextField minSalKey;
    @FXML
    private TextField maxSalKey;
    @FXML
    private Tab cwpTab;
    @FXML
    private TableView nameTable;
    @FXML
    private TableView positionTable;
    @FXML
    private TableView ccTable;
    @FXML
    private TableView srTable;
    @FXML
    private TableView cwpTable;

    public void setMain(Main main) {
        this.main=main;
    }

    public void init(FootballAssociation fa,int tabIndx){
        this.fa=fa;
        tabPane.getSelectionModel().select(tabIndx);

        TableColumn<Player, String> name = new TableColumn<>("Player Name");
        name.setMinWidth(200);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country = new TableColumn<>("Player's Country");
        country.setMinWidth(150);
        country.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club = new TableColumn<>("Player's Club");
        club.setMinWidth(150);
        club.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> position = new TableColumn<>("Player's Position");
        position.setMinWidth(200);
        position.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, String> button = new TableColumn<>("Profile");
        button.setMinWidth(85);


        TableColumn<Player, String> name2 = new TableColumn<>("Player Name");
        name2.setMinWidth(200);
        name2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country2 = new TableColumn<>("Player's Country");
        country2.setMinWidth(150);
        country2.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club2 = new TableColumn<>("Player's Club");
        club2.setMinWidth(150);
        club2.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> position2 = new TableColumn<>("Player's Position");
        position2.setMinWidth(200);
        position2.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, String> button2 = new TableColumn<>("Profile");
        button2.setMinWidth(85);


        TableColumn<Player, String> name3 = new TableColumn<>("Player Name");
        name3.setMinWidth(200);
        name3.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country3 = new TableColumn<>("Player's Country");
        country3.setMinWidth(150);
        country3.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club3 = new TableColumn<>("Player's Club");
        club3.setMinWidth(150);
        club3.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> position3 = new TableColumn<>("Player's Position");
        position3.setMinWidth(200);
        position3.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<Player, String> button3 = new TableColumn<>("Profile");
        button3.setMinWidth(85);


        TableColumn<Player, String> name4 = new TableColumn<>("Player Name");
        name4.setMinWidth(200);
        name4.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country4 = new TableColumn<>("Player's Country");
        country4.setMinWidth(150);
        country4.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club4 = new TableColumn<>("Player's Club");
        club4.setMinWidth(150);
        club4.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, Double> salary4 = new TableColumn<>("Player's Salary");
        salary4.setMinWidth(200);
        salary4.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

        TableColumn<Player, String> button4 = new TableColumn<>("Profile");
        button4.setMinWidth(85);


        TableColumn<Country, String> cname = new TableColumn<>("Country");
        cname.setMinWidth(400);
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Country,Integer> count = new TableColumn<>("Number of Players");
        count.setMinWidth(385);
        count.setCellValueFactory(new PropertyValueFactory<>("count"));



        nameTable.getColumns().addAll(name, country, club, position, button);
        positionTable.getColumns().addAll(name2, country2, club2, position2, button2);
        ccTable.getColumns().addAll(name3, country3, club3, position3, button3);
        srTable.getColumns().addAll(name4, country4, club4, salary4, button4);
        cwpTable.getColumns().addAll(cname,count);
    }

    public void nameTabPressed(Event event) {
        nameTable.getItems().clear();
        nameKey.clear();
        nameNotFound.setText("");
    }

    public void nameKeyPressed(ActionEvent actionEvent) {
        if(nameTab.isSelected()){
            nameNotFound.setText("");
            String s=nameKey.getText();
            nameTable.setItems(FXCollections.observableList(SearchPlayer.searchByName(s,fa.getPlayers())));
            //System.out.println(nameTable.getItems());
            if(nameTable.getItems().isEmpty()){
                nameNotFound.setText("No Player With This Name");
            }
        }
    }

    public void positionTabPressed(Event event) {
        positionTable.getItems().clear();
        positionKey.clear();
        positionNotFound.setText("");
    }

    public void positionKeyPressed(ActionEvent actionEvent) {
        if(positionTab.isSelected()){
            positionNotFound.setText("");
            String s=positionKey.getText();
            positionTable.setItems(FXCollections.observableList(SearchPlayer.searchByPosition(s,fa.getPlayers())));
            if(positionTable.getItems().isEmpty()){
                positionNotFound.setText("No Player With This Position. Position Suggestion: Forward, Midfielder, Defender, Goalkeeper");
            }
        }
    }

    public void ccTabPressed(Event event) {
        ccTable.getItems().clear();
        countryKey.clear();
        clubKey.clear();
        ccNotFound.setText("");
        empty1.setText("");
        empty2.setText("");

    }


    public void srTabPressed(Event event) {
        srTable.getItems().clear();
        minSalKey.clear();
        maxSalKey.clear();
        srNotFound.setText("");
        empty3.setText("");
        empty4.setText("");
    }


    public void cwpTabPressed(Event event) {
        if(cwpTab.isSelected())
            cwpTable.setItems(FXCollections.observableList(SearchPlayer.countryWiseCount(fa.getPlayers())));
    }

    public void searchCCButt(ActionEvent actionEvent) {
        ccNotFound.setText("");
        empty1.setText("");
        empty2.setText("");
        if(ccTab.isSelected()){
            ccTable.setItems(FXCollections.observableList(SearchPlayer.searchByCountryNClub(countryKey.getText(),clubKey.getText(),fa.getPlayers())));

            if(countryKey.getText().isEmpty()){
                empty1.setText("Country Cant Be Empty");
            }

            if(clubKey.getText().isEmpty()){
                empty2.setText("Club Cant Be Empty");
            }
            else if (ccTable.getItems().isEmpty()) {
                    ccNotFound.setText("No Player With This Club and Country");
            }

        }
    }

    public void searchSRButt(ActionEvent actionEvent) {

        if(srTab.isSelected()){
            srNotFound.setText("");
            empty3.setText("");
            empty4.setText("");
            srTable.setItems(FXCollections.observableList(SearchPlayer.searchBySalaryRange(Integer.parseInt(minSalKey.getText()),Integer.parseInt(maxSalKey.getText()),fa.getPlayers())));
            if(minSalKey.getText().isEmpty()){
                empty3.setText("This Field Cant Be Empty");
            }

            if(maxSalKey.getText().isEmpty()){
                empty4.setText("This Field Cant Be Empty");
            }
            else if (srTable.getItems().isEmpty()) {
                ccNotFound.setText("No Player Within This Range");
            }
        }
    }

    public void onlyCountry(ActionEvent actionEvent) {
        if(oCountry.isSelected()){
            clubKey.setText("Any");
            clubKey.setEditable(false);
        }
        else{
            clubKey.setText("");
            clubKey.setEditable(true);
        }
    }

    public void backButton(ActionEvent actionEvent) {
        main.showPlayerSearchMenu();
    }
}
