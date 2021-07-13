package UI;

import Code.FootballAssociation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stg;
    private Stage playerPro=new Stage();
    FootballAssociation fa;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg=primaryStage;
        fa=new FootballAssociation();
        showMainMenu();
    }

    void showMainMenu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainMenu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        MainMenuCtrl ctrl = loader.getController();
        ctrl.setMain(this);

        stg.setTitle("Main Menu");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    void showPlayerSearchMenu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerSearchMenu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        PlayerSearchMenuCtrl ctrl = loader.getController();
        ctrl.setMain(this);

        stg.setTitle("Player Search Menu");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    void showClubSearchMenu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ClubSearchMenu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        ClubSearchMenuCtrl ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init(fa);

        stg.setTitle("Club Search Menu");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    void showPlayerSearchScreen(int tabIndx) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerSearchScreen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        PlayerSearchScreenCtrl ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init(fa,tabIndx);

        stg.setTitle("Player Search Screen");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    void showPlayerAddScreen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerAddScreen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        PlayerAddScreenCtrl ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init(fa);

        stg.setTitle("Player Add Screen");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style2.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
