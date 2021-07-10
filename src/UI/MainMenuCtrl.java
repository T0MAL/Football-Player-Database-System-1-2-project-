package UI;

import javafx.event.ActionEvent;
import Code.*;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuCtrl {
    private Main main;
    private FootballAssociation fa;

    public void init(FootballAssociation fa){
        this.fa=fa;
    }

    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    public void searchPlayerButton(ActionEvent actionEvent) {
        main.showPlayerSearchMenu(fa);
    }
    @FXML
    public void searchClubButton(ActionEvent actionEvent) {
        main.showClubSearchMenu(fa);
    }
    @FXML
    public void exitButton(ActionEvent actionEvent) {

    }
    @FXML
    public void addPlayerButton(ActionEvent actionEvent) {

    }
}
