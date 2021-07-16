package UI;

import Code.Club;
import Code.Player;
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

public class ClubHomeCtrl implements Runnable{
    private Main main;
    private Club c;
    private Thread thr;
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
        thr=new Thread(this);
        this.c=c;
        clubTitle.setText(this.c.getName());
        this.networkUtil=networkUtil;
        myPlayers.getItems().addAll(c.getPlayers());
        thr.start();
    }

    @Override
    public void run() {
        try{
            while(true){
                Object o=networkUtil.read();
                if(o instanceof transferMarket){
                    transferMarket tm=(transferMarket) o;
                    marketList.getItems().clear();
                    marketListPlayers=tm.getPlayerMarket();
                    marketList.getItems().addAll(marketListPlayers);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getMarketButt(ActionEvent actionEvent) {
        transferMarket tm=new transferMarket();
        try {
            networkUtil.write(tm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sellButt(ActionEvent actionEvent) {
        ObservableList selectedIndices = myPlayers.getSelectionModel().getSelectedIndices();
        Player tmp = null;
        for(Object o : selectedIndices){
            tmp=c.getPlayers()[(int)o];
        }
        //System.out.println(tmp);
        transferPlayer tp=new transferPlayer("forSell");
        tp.setFromClub(c);
        tp.setPlayer(tmp);
        try {
            networkUtil.write(tp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buyButt(ActionEvent actionEvent) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
