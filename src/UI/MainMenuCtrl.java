package UI;

import javafx.event.ActionEvent;
import Code.*;

import java.io.IOException;

public class MainMenuCtrl {
    private Main main;
    private FootballAssociation fa;

    MainMenuCtrl(){
        try {
            fa=new FootballAssociation();
        } catch (IOException e) {

        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void searchPlayerButton(ActionEvent actionEvent) {

    }

    public void searchClubButton(ActionEvent actionEvent) {

    }

    public void exitButton(ActionEvent actionEvent) {

    }

    public void addPlayerButton(ActionEvent actionEvent) {

    }
}
