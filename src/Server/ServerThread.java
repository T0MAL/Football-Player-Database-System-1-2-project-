package Server;

import Code.Club;
import Code.FootballAssociation;
import Code.Player;
import Transferables.transferLogin;
import Transferables.transferMarket;
import Transferables.transferPlayer;
import util.NetworkUtil;

import java.util.List;
import java.util.Map;

public class ServerThread implements Runnable {
    Thread thr;
    Map<Club, NetworkUtil> clubList;
    Map<Player,Club> playerMarket;
    FootballAssociation fa;
    NetworkUtil networkUtil;

    public ServerThread(Map<Club, NetworkUtil> clientList, NetworkUtil networkUtil, Map<Player, Club> playerMarket, FootballAssociation fa) {
        this.clubList=clientList;
        this.networkUtil=networkUtil;
        thr=new Thread(this);
        this.playerMarket=playerMarket;
        this.fa=fa;
        thr.start();
    }

    @Override
    public void run() {
        try{
            while(true){
                Object o=networkUtil.read();
                if(o instanceof transferMarket){
                    transferMarket tm=(transferMarket) o;
                    for(Map.Entry<Player,Club> i:playerMarket.entrySet()){
                        tm.getPlayerMarket().add(i.getKey());
                    }
                    networkUtil.write(tm);
                }
                else if(o instanceof transferPlayer){
                    transferPlayer tp=(transferPlayer) o;
                    if(tp.getMethod().equals("forSell")){
                        if(!playerMarket.containsKey(tp.getPlayer()))
                            playerMarket.put(tp.getPlayer(),tp.getFromClub());
                    }
                    else if(tp.getMethod().equals("Buy")){
                        if(playerMarket.containsKey(tp.getPlayer())){
                            tp.getFromClub().addPlayer(tp.getPlayer());
                            playerMarket.get(tp.getPlayer()).removePlayer(tp.getPlayer());
                            playerMarket.remove(tp.getPlayer());
                        }

                    }
                    transferMarket tmm=new transferMarket();
                    for(Map.Entry<Player,Club> i:playerMarket.entrySet()){
                        tmm.getPlayerMarket().add(i.getKey());
                    }
                    for(Map.Entry<Club,NetworkUtil> i: clubList.entrySet()){
                        i.getValue().write(tmm);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
