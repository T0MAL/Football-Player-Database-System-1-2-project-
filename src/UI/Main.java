package UI;

import Clients.readThread;
import Code.Club;
import Code.Player;
import Transferables.transferClose;
import Transferables.transferLogOUT;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private boolean inMarket=false;
    private Stage stg;
    public static Club club;
    public static List<Player> marketListPlayers;
    private NetworkUtil networkUtil;
    private readThread rt;

    @Override
    public void start(Stage primaryStage){
        stg=primaryStage;
        stg.setOnCloseRequest(e -> closeApp());
        try {
            connectToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showLoginWindow();
    }

    private void closeApp() {
        if(club!=null){
            try {
                showAlert("You will be signed out automatically");
                networkUtil.write(new transferClose(club.getName()));
                club=null;
                marketListPlayers=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stg.close();
        System.exit(0);
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new readThread(this);
    }

    public boolean isInMarket() {
        return inMarket;
    }


    public void setInMarket(boolean inMarket) {
        this.inMarket = inMarket;
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

    public void showClubHome(int indx){
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
        ctrl.init(indx);

        stg.setTitle("Home");
        Scene scn=new Scene(root,960,540);
        scn.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stg.setScene(scn);
        stg.show();
    }

    public void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public readThread getRt() {
        return rt;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Player> getMarketListPlayers() {
        return marketListPlayers;
    }

    public void setMarketListPlayers(List<Player> marketListPlayers) {
        this.marketListPlayers = marketListPlayers;
    }


    public static void main(String[] args) {
        launch(args);
    }
}