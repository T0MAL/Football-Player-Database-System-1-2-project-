package UI;

import Code.Club;
import Code.FileIO;
import Code.FootballAssociation;
import Server.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;

public class Main extends Application {
    private Stage stg;
    private Stage playerListStage=new Stage();
    private Stage loginStg;
    private Thread serverThread;
    FootballAssociation fa;
    Server server;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg=primaryStage;
        fa=new FootballAssociation();
        server=new Server(fa);
        serverThread=new Thread(server);
        serverThread.start();
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

    void showClubSearchMenu(int i) {
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
        ctrl.init(fa,i);

        stg.setTitle("Club Search Screen");
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
    void showClubSearchOpt() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ClubSearchOpt.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        ClubSearchOptCtrl ctrl = loader.getController();
        ctrl.setMain(this);

        stg.setTitle("Club Search Menu");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }

        Stage clientStg=new Stage();

        LoginWindowCtrl ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init(clientStg);


        clientStg.setTitle("Login");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        clientStg.setScene(scn);
        clientStg.show();
    }
    public void showClubHome(Stage s, Club c, NetworkUtil networkUtil){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ClubHome.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            System.out.println(e);
        }
        ClubHomeCtrl ctrl = loader.getController();
        ctrl.setMain(this);
        c.showClub();
        ctrl.init(c,networkUtil);

        //Stage clientStg=new Stage();
        s.setTitle("Home");
        Scene scn=new Scene(root,800,450);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        s.setScene(scn);
        s.show();
    }
    public void stop() throws IOException {
        FileIO.writeFile(fa.getPlayers());
        server.setServerOn(false);
        stg.close();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
