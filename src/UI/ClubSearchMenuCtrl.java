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
    String key="";
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

        if(salaryTab.isSelected() && !key.equals("")){
            notFound.setText("");
            salaryTable.setItems(FXCollections.observableList(SearchClub.maxSalary(key,fa.getClubs())));
            if(salaryTable.getItems().isEmpty()){
                notFound.setText("Club not found");
            }
        }

        else if(ageTab.isSelected() && !key.equals("")){
            notFound.setText("");
            ageTable.setItems(FXCollections.observableList(SearchClub.maxAge(key,fa.getClubs())));
            if(ageTable.getItems().isEmpty()){
                notFound.setText("Club not found");
            }
        }

        else if(heightTab.isSelected() && !key.equals("")){
            notFound.setText("");
            heightTable.setItems(FXCollections.observableList(SearchClub.maxHeight(key,fa.getClubs())));
            if(heightTable.getItems().isEmpty()){
                notFound.setText("Club not found");
            }
        }

        else if(yrSalaryTab.isSelected() && !key.equals("")){
            notFound.setText("");
            double d=SearchClub.totalSalary(key,fa.getClubs());
            if(d==0){
                notFound.setText("Club not found");
            }
            else{
                String tmp=Utility.polishString(key)+"'s total yearly salary: "+String.valueOf(d);
                yearlySal.setText(tmp);
            }
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void init(FootballAssociation fa,int i){
        this.fa=fa;
        tabPane.getSelectionModel().select(i);

        TableColumn<Player, String> name = new TableColumn<>("Player Name");
        name.setMinWidth(200);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country = new TableColumn<>("Player's Country");
        country.setMinWidth(175);
        country.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club = new TableColumn<>("Player's Club");
        club.setMinWidth(175);
        club.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, Double> salary = new TableColumn<>("Player's Salary");
        salary.setMinWidth(150);
        salary.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

        TableColumn<Player, String> button = new TableColumn<>("Profile");
        button.setMinWidth(80);

        TableColumn<Player, String> name2 = new TableColumn<>("Player Name");
        name2.setMinWidth(200);
        name2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country2 = new TableColumn<>("Player's Country");
        country2.setMinWidth(175);
        country2.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club2 = new TableColumn<>("Player's Club");
        club2.setMinWidth(175);
        club2.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, Integer> age2 = new TableColumn<>("Player's Age");
        age2.setMinWidth(150);
        age2.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player, String> button2 = new TableColumn<>("Profile");
        button2.setMinWidth(80);

        TableColumn<Player, String> name3 = new TableColumn<>("Player Name");
        name3.setMinWidth(200);
        name3.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> country3 = new TableColumn<>("Player's Country");
        country3.setMinWidth(175);
        country3.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Player, String> club3 = new TableColumn<>("Player's Club");
        club3.setMinWidth(175);
        club3.setCellValueFactory(new PropertyValueFactory<>("club"));

        TableColumn<Player, Double> height3 = new TableColumn<>("Player's Height");
        height3.setMinWidth(150);
        height3.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player, String> button3 = new TableColumn<>("Profile");
        button3.setMinWidth(80);
        //buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        salaryTable.getColumns().addAll(name, country, club, salary, button);
        ageTable.getColumns().addAll(name2, country2, club2, age2,button2);
        heightTable.getColumns().addAll(name3, country3, club3, height3,button3);

    }

    public void salaryTabPressed(Event event) {
        if (salaryTab.isSelected() && !key.equals("")) {
            notFound.setText("");
            salaryTable.setItems(FXCollections.observableList(SearchClub.maxSalary(key, fa.getClubs())));
            if (salaryTable.getItems().isEmpty()) {
                notFound.setText("Club not found");
            }
        }
    }

    public void ageTabPressed(Event event) {
        if(ageTab.isSelected() && !key.equals("")){
            notFound.setText("");
            ageTable.setItems(FXCollections.observableList(SearchClub.maxAge(key,fa.getClubs())));
            if(ageTable.getItems().isEmpty()){
                notFound.setText("Club not found");
            }
        }
    }

    public void heightTabPressed(Event event) {
        if(heightTab.isSelected() && !key.equals("")){
            notFound.setText("");
            heightTable.setItems(FXCollections.observableList(SearchClub.maxHeight(key,fa.getClubs())));
            if(heightTable.getItems().isEmpty()){
                notFound.setText("Club not found");
            }
        }
    }

    public void ySalaryPressed(Event event) {
        if(yrSalaryTab.isSelected() && !key.equals("")){
            notFound.setText("");
            double d=SearchClub.totalSalary(key,fa.getClubs());
            if(d==0){
                notFound.setText("Club not found");
            }
            else{
                String tmp=Utility.polishString(key)+"'s total yearly salary: "+String.valueOf(d);
                yearlySal.setText(tmp);
            }
        }
    }

    public void backButton(ActionEvent actionEvent) {
        main.showClubSearchOpt();
    }
}
