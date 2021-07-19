package UI;

import Code.Club;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.NetworkUtil;
import java.io.IOException;

public class Main extends Application {
    private Stage stg;

    @Override
    public void start(Stage primaryStage){
        stg=primaryStage;
        showLoginWindow();
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

        LoginWindowCtrl ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stg.setTitle("Login");
        Scene scn=new Scene(root,960,540);
        scn.getStylesheets().add(getClass().getResource("Style2.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    public void showClubHome(Club c, NetworkUtil networkUtil){
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
        ctrl.init(c,networkUtil);

        stg.setTitle("Home");
        Scene scn=new Scene(root,960,540);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    public void stop() throws IOException {
        stg.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
