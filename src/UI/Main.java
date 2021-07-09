package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg=primaryStage;
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


    public static void main(String[] args) {
        launch(args);
    }
}
