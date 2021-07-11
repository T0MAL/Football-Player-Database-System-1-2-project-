package UI;

import Code.*;
import javafx.event.ActionEvent;

public class PlayerSearchMenuCtrl {
    private Main main;
    FootballAssociation fa;

    public void setMain(Main main){
        this.main=main;
    }

    public void init(FootballAssociation fa){
        this.fa=fa;
    }

    public void goName(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(fa,0);
    }

    public void goPos(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(fa,1);
    }

    public void goSR(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(fa,3);
    }

    public void goCC(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(fa,2);
    }

    public void goCWP(ActionEvent actionEvent) {
        main.showPlayerSearchScreen(fa,4);
    }
}
