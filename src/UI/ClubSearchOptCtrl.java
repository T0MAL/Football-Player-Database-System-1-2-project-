package UI;

import javafx.event.ActionEvent;

public class ClubSearchOptCtrl {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void backButton(ActionEvent actionEvent) {
        main.showMainMenu();
    }

    public void goAge(ActionEvent actionEvent) {
        main.showClubSearchMenu(2);
    }

    public void goHeight(ActionEvent actionEvent) {
        main.showClubSearchMenu(3);
    }

    public void goTotal(ActionEvent actionEvent) {
        main.showClubSearchMenu(0);
    }

    public void goSal(ActionEvent actionEvent) {
        main.showClubSearchMenu(1);
    }
}
