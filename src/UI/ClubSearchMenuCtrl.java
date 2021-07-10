package UI;

import Code.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClubSearchMenuCtrl {
    private Main main;
    FootballAssociation fa;
    String key;
    ObservableList<Player> player;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab salaryTab;
    @FXML
    private TextField searchKey;
    @FXML
    private TableView salaryTable;
    public void searchKeyPressed(ActionEvent actionEvent) {
        key=searchKey.getText();
        if(salaryTab.isSelected()){
            if(salaryTab.isSelected()){
                System.out.println(key);
                TableColumn<Player, String> name = new TableColumn<>("Player Name");
                name.setMinWidth(250);
                name.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<Player, String> country = new TableColumn<>("Player's Country");
                country.setMinWidth(250);
                country.setCellValueFactory(new PropertyValueFactory<>("country"));

                TableColumn<Player, String> club = new TableColumn<>("Player's Club");
                club.setMinWidth(220);
                club.setCellValueFactory(new PropertyValueFactory<>("club"));

                TableColumn<Player, Void> buttonCol = new TableColumn<>("Profile");
                buttonCol.setMinWidth(80);
                buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));

                salaryTable.getColumns().addAll(name, country, club, buttonCol);
                salaryTable.setItems(FXCollections.observableList(SearchClub.maxSalary(key,fa.getClubs())));
            }
        }
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void init(FootballAssociation fa){
        this.fa=fa;
    }

    public void salaryTabPressed(Event event) {
//        if(salaryTab.isSelected()){
//            System.out.println(key);
//            TableColumn<Player, String> name = new TableColumn<>("Player Name");
//            name.setMinWidth(250);
//            name.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//            TableColumn<Player, String> country = new TableColumn<>("Player's Country");
//            country.setMinWidth(250);
//            country.setCellValueFactory(new PropertyValueFactory<>("country"));
//
//            TableColumn<Player, String> club = new TableColumn<>("Player's Club");
//            club.setMinWidth(220);
//            club.setCellValueFactory(new PropertyValueFactory<>("club"));
//
//            TableColumn<Player, String> buttonCol = new TableColumn<>("Profile");
//            buttonCol.setMinWidth(80);
//            buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));
//
//            salaryTable.getColumns().addAll(name, country, club, buttonCol);
//            salaryTable.setItems(FXCollections.observableList(SearchClub.maxSalary(key,fa.getClubs())));
//        }

    }
}
