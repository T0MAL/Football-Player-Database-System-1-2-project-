package Clients;

import Code.Player;
import Transferables.transferMarket;
import UI.ClubHomeCtrl;
import javafx.scene.control.ListView;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class readThread implements Runnable{
    private final Thread thr;
    private NetworkUtil networkUtil;
    ListView lv;

    List<Player> marketListPlayers;
    public readThread(NetworkUtil networkUtil, List<Player> mlp, ListView lv) {
        this.thr = new Thread(this);
        this.networkUtil=networkUtil;
        this.marketListPlayers=mlp;
        this.lv=lv;
        thr.start();
    }



    @Override
    public void run() {
        try{
            while(true){
                Object o=networkUtil.read();
                if(o instanceof transferMarket){
                    transferMarket tm=(transferMarket) o;
                    lv.getItems().clear();
                    marketListPlayers=tm.getPlayerMarket();
                    lv.getItems().addAll(marketListPlayers);
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

}
