package Server;

import Code.Club;
import Code.FootballAssociation;
import Code.Player;
import Transferables.transferClub;
import Transferables.transferMarket;
import Transferables.transferPlayer;
import util.NetworkUtil;

import java.util.List;
import java.util.Map;

public class ServerThread implements Runnable {
    Thread thr;
    Map<Club, NetworkUtil> clubList;
    List<Player> playerMarket;
    NetworkUtil networkUtil;
    FootballAssociation fa;

    public ServerThread(Map<Club, NetworkUtil> clientList, NetworkUtil networkUtil, List<Player> playerMarket,FootballAssociation fa) {
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
                if(o instanceof transferMarket) {
                    transferMarket tm = (transferMarket) o;
                    for(Player i:playerMarket){
                        tm.getPlayerMarket().add(i);
                    }
                    networkUtil.write(tm);
                }
                else if(o instanceof transferPlayer){
                    transferPlayer tp=(transferPlayer) o;
                    if(tp.getMethod().equals("forSell")){
                        boolean e=false;
                        for(Player i: playerMarket){
                            if(i.equals(tp.getPlayer())){
                                e=true;
                                break;
                            }
                        }
                        if(!e){
                            playerMarket.add(tp.getPlayer());
                        }
                        transferMarket tmm = new transferMarket();
                        for(Player i:playerMarket){
                            tmm.getPlayerMarket().add(i);
                        }
                        networkUtil.write(tmm);
                    }
                    else if(tp.getMethod().equals("Buy")){
                        System.out.println("mark 0");
                        boolean e=false;
                        Player p=null;
                        int indx=0;
                        for(Player i:playerMarket){
                            System.out.println(i);
                            System.out.println(i.equals(tp.getPlayer()));
                            if(i.equals(tp.getPlayer())){
                                System.out.println("mark1");
                                e=true;
                                //playerMarket.remove(i);
                                System.out.println(playerMarket);
                                break;
                            }
                            indx++;
                        }
                        Club buyer=null;
                        if(e){
                            System.out.println("mark 2");
                            Club seller=null;
                            for(Player i:fa.getPlayers()){
                                if(i.equals(tp.getPlayer())){
                                    p=i;
                                    seller=i.getC();
                                    i.setClub(tp.getFromClub().getName());
                                }
                            }
                            if(!seller.equals(tp.getFromClub())) {
                                playerMarket.remove(indx);
                                for (Club i : fa.getClubs()) {
                                    if (i.equals(tp.getFromClub()) && !i.equals(seller)) {
                                        i.addPlayer(p);
                                        p.setC(i);
                                        buyer = i;
                                    }
                                    if (i.equals(seller)) {
                                        i.removePlayer(p);
                                    }
                                }
                                transferMarket tmm = new transferMarket();
                                for(Player i:playerMarket){
                                    tmm.getPlayerMarket().add(i);
                                }
                                networkUtil.write(tmm);
                                transferClub tc=new transferClub("Done");
                                networkUtil.write(tc);
                            }
                            else{
                                transferMarket tmm = new transferMarket();
                                for(Player i:playerMarket){
                                    tmm.getPlayerMarket().add(i);
                                }
                                networkUtil.write(tmm);
                                transferClub tc=new transferClub("Failed");
                                networkUtil.write(tc);
                            }
                        }
                        else{
                            transferMarket tmm = new transferMarket();
                            for(Player i:playerMarket){
                                tmm.getPlayerMarket().add(i);
                            }
                            networkUtil.write(tmm);
                            transferClub tc=new transferClub("Failed");
                            networkUtil.write(tc);
                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
