package UI;

import Code.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClubSearchMenuCtrl {
    private Main main;
    FootballAssociation fa;
    String key;
    boolean clubExists;
    @FXML
    private Label notFound;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab salaryTab;
    @FXML
    private Tab ageTab;
    @FXML
    private Tab heightTab;
    @FXML
    private Tab yrSalaryTab;
    @FXML
    private TextField searchKey;
    @FXML
    private TableView salaryTable;
    @FXML
    private TableView ageTable;
    @FXML
    private TableView heightTable;
    @FXML
    private Label yearlySal;
    public void searchKeyPressed(ActionEvent actionEvent) {
        key=searchKey.getText();
        for(Club i:fa.getClubs()){
            if(i.getName().equalsIgnoreCase(key)){
                clubExists=true;
                notFound.setText("");
                break;
            }
            clubExists=false;
        }
        if(!clubExists){
            notFound.setText("Club not found");
            salaryTable.getItems().clear();
            ageTable.getItems().clear();
            heightTable.getItems().clear();
            yearlySal.setText("");
        }
        if(salaryTab.isSelected() && clubExists){
            //salaryTable.getColumns().addAll(name, country, club, button);
            salaryTable.setItems(FXCollections.observableList(SearchClub.maxSalary(key,fa.getClubs())));
        }
        else if(ageTab.isSelected() && clubExists){
           // ageTable.getColumns().addAll(name, country, club, button);
            ageTable.setItems(FXCollections.observableList(SearchClub.maxAge(key,fa.getClubs())));
        }
        else if(heightTab.isSelected() && clubExists){
           // heightTable.getColumns().addAll(name, country, club, button);
            heightTable.setItems(FXCollections.observableList(SearchClub.maxHeight(key,fa.getClubs())));
        }
        else if(yrSalaryTab.isSelected() && clubExists){
            String tmp=Utility.polishString(key)+"'s total yearly salary: "+String.valueOf(SearchClub.totalSalary(key,fa.getClubs()));
            yearlySal.setText(tmp);
        }
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void init(FootballAssociation fa){
        this.fa=fa;

        TableColumn<Player, String> name = new TableColumn<>("Player Name");
        name.setMinWidth(200);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country = new TableColumn<>("Player's Country");
        country.setMinWidth(150);
        country.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club = new TableColumn<>("Player's Club");
        club.setMinWidth(150);
        club.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, Double> salary = new TableColumn<>("Player's Salary");
        salary.setMinWidth(200);
        salary.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

        TableColumn<Player, String> button = new TableColumn<>("Profile");
        button.setMinWidth(80);

        TableColumn<Player, String> name2 = new TableColumn<>("Player Name");
        name2.setMinWidth(300);
        name2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country2 = new TableColumn<>("Player's Country");
        country2.setMinWidth(200);
        country2.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club2 = new TableColumn<>("Player's Club");
        club2.setMinWidth(200);
        club2.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> button2 = new TableColumn<>("Profile");
        button2.setMinWidth(80);

        TableColumn<Player, String> name3 = new TableColumn<>("Player Name");
        name3.setMinWidth(300);
        name3.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country3 = new TableColumn<>("Player's Country");
        country3.setMinWidth(200);
        country3.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club3 = new TableColumn<>("Player's Club");
        club3.setMinWidth(200);
        club3.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, String> button3 = new TableColumn<>("Profile");
        button3.setMinWidth(80);
        //buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        salaryTable.getColumns().addAll(name, country, club, salary, button);
        ageTable.getColumns().addAll(name2, country2, club2, button2);
        heightTable.getColumns().addAll(name3, country3, club3, button3);

    }

    public void salaryTabPressed(Event event) {
        //System.out.println("sal pressed");
        if(salaryTab.isSelected() && clubExists){
           // salaryTable.getColumns().addAll(name, country, club, button);
            salaryTable.setItems(FXCollections.observableList(SearchClub.maxSalary(key,fa.getClubs())));
        }
    }

    public void ageTabPressed(Event event) {
        if(ageTab.isSelected() && clubExists){
           // ageTable.getColumns().addAll(name, country, club, button);
            ageTable.setItems(FXCollections.observableList(SearchClub.maxAge(key,fa.getClubs())));
        }
    }

    public void heightTabPressed(Event event) {
        if(heightTab.isSelected() && clubExists){
           // heightTable.getColumns().addAll(name, country, club, button);
            heightTable.setItems(FXCollections.observableList(SearchClub.maxHeight(key,fa.getClubs())));
        }
    }

    public void ySalaryPressed(Event event) {
        if(yrSalaryTab.isSelected() && clubExists) {
            String tmp = Utility.polishString(key) + "'s total yearly salary: " + String.valueOf(SearchClub.totalSalary(key, fa.getClubs()));
            yearlySal.setText(tmp);
        }
    }
}
