package UI;

import Code.*;
import javafx.event.ActionEvent;

public class PlayerSearchMenuCtrl {
    private Main main;

    public void setMain(Main main){
        this.main=main;
    }

    public void init(){

    }

    public void goName(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(0);
    }

    public void goPos(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(1);
    }

    public void goSR(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(3);
    }

    public void goCC(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(2);
    }

    public void goCWP(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(4);
    }

    public void backButton(ActionEvent actionEvent) {
        main.showMainMenu();
    }
}
