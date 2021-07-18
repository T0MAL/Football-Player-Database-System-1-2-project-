package UI;

import Code.Club;
import Code.Player;
import Transferables.transferClub;
import Transferables.transferMarket;
import Transferables.transferPlayer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class ClubHomeCtrl{
    private Main main;
    private Club c;
    private List<Player> marketListPlayers;
    NetworkUtil networkUtil;
    @FXML
    private Label clubTitle;
    @FXML
    private ListView marketList;
    @FXML
    private ListView myPlayers;

    public void setMain(Main main) {
        this.main = main;
    }

    public void init(Club c,NetworkUtil networkUtil){
        this.c=c;
        clubTitle.setText(this.c.getName());
        this.networkUtil=networkUtil;
        myPlayers.getItems().addAll(c.getPlayers());
        //rt=new readThread(networkUtil,marketListPlayers,marketList);
    }

//    @Override
//    public void run() {
//        try{
//            while(true){
//                Object o=networkUtil.read();
//                if(o instanceof transferMarket){
//                    transferMarket tm=(transferMarket) o;
//                    marketList.getItems().clear();
//                    marketListPlayers=tm.getPlayerMarket();
//                    marketList.getItems().addAll(marketListPlayers);
//                }
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                networkUtil.closeConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    synchronized public void getMarketButt(ActionEvent actionEvent) throws InterruptedException {
        transferMarket tm=new transferMarket();
        try {
            networkUtil.write(tm);
            Object o = networkUtil.read();
            if (o instanceof transferMarket) {
                transferMarket tmm = (transferMarket) o;
                marketList.getItems().clear();
                marketListPlayers = tmm.getPlayerMarket();
                System.out.println(marketListPlayers);
                marketList.getItems().addAll(marketListPlayers);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    synchronized public void sellButt(ActionEvent actionEvent) {
        ObservableList selectedIndices = myPlayers.getSelectionModel().getSelectedIndices();
        Player tmp = null;
        for(Object o : selectedIndices){
            tmp= c.getPlayers().get((int) o);
        }
        System.out.println(c);
        transferPlayer tp=new transferPlayer("forSell");
        tp.setFromClub(c);
        tp.setPlayer(tmp);
        System.out.println("here 0");
        try {
            networkUtil.write(tp);
            System.out.println("here 1");
            Object o = networkUtil.read();
            System.out.println("here 2");
            if (o instanceof transferMarket) {
                transferMarket tmm = (transferMarket) o;
                marketList.getItems().clear();
                System.out.println(tmm.getPlayerMarket());
                marketListPlayers = tmm.getPlayerMarket();
                marketList.getItems().addAll(marketListPlayers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    synchronized public void buyButt(ActionEvent actionEvent) {
        ObservableList selectedIndices = marketList.getSelectionModel().getSelectedIndices();
        Player tmp = null;
        System.out.println(marketListPlayers);
        for(Object o : selectedIndices){
            tmp= marketListPlayers.get((int)o);
        }
        //System.out.println(tmp);
        transferPlayer tp=new transferPlayer("Buy");
        tp.setFromClub(c);
        tp.setPlayer(tmp);
        try {
            networkUtil.write(tp);
            Object o = networkUtil.read();
            Object oo = networkUtil.read();
            if (o instanceof transferMarket) {
                transferMarket tmm = (transferMarket) o;
                marketList.getItems().clear();
                marketListPlayers = tmm.getPlayerMarket();
                marketList.getItems().addAll(marketListPlayers);
            }
            if (oo instanceof transferClub) {
                transferClub cc = (transferClub) oo;
                if(cc.getC().equals("Done")){
                    c.addPlayer(tmp);
                    tmp.setClub(c.getName());
                    myPlayers.getItems().clear();
                    myPlayers.getItems().addAll(c.getPlayers());
                }
                else if(cc.getC().equals("Failed")){

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}