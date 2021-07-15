package UI;

import javafx.event.ActionEvent;
import Code.*;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuCtrl {
    private Main main;

    public void init(){

    }

    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    public void searchPlayerButton(ActionEvent actionEvent) {
        main.showPlayerSearchMenu();
    }
    @FXML
    public void searchClubButton(ActionEvent actionEvent) {
        main.showClubSearchOpt();
    }
    @FXML
    public void exitButton(ActionEvent actionEvent) {
        try {
            main.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void addPlayerButton(ActionEvent actionEvent) {
        main.showPlayerAddScreen();
    }
}
