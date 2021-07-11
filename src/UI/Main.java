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

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg=primaryStage;
        FootballAssociation fa=new FootballAssociation();
        showMainMenu(fa);
    }

    void showMainMenu(FootballAssociation fa) {
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
        ctrl.init(fa);

        stg.setTitle("Main Menu");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    void showPlayerSearchMenu(FootballAssociation fa) {
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
        ctrl.init(fa);

        stg.setTitle("Player Search Menu");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    void showClubSearchMenu(FootballAssociation fa) {
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

    void showPlayerSearchScreen(FootballAssociation fa,int tabIndx) {
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


    public static void main(String[] args) {
        launch(args);
    }
}
