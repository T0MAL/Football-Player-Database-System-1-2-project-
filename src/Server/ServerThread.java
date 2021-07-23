package Server;

import Code.Club;
import Code.FileIO;
import Code.FootballAssociation;
import Code.Player;
import Transferables.*;
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
                if(o instanceof transferLogin){
                    transferLogin tl = (transferLogin) o;
                    boolean found=false;
                    for (Club c : fa.getClubs()) {
                        if (c.getName().equalsIgnoreCase(tl.getName()) ) {
                            found = true;
                            if(!clubList.containsKey(c)) {
                                tl.setResponse("Accepted");
                                tl.setClub(c);
                                networkUtil.write(tl);
                                clubList.put(c, networkUtil);
                                transferPlayerBroadcast tpb=new transferPlayerBroadcast();
                                tpb.setPlayerMarket(playerMarket);
                                networkUtil.write(tpb);
                                break;
                            }
                            else{
                                tl.setResponse("Exists");
                                networkUtil.write(tl);
                            }
                        }
                    }
                    if(!found) {
                        tl.setResponse("Denied");
                        networkUtil.write(tl);
                    }
                }
                else if(o instanceof transferClose){
                    transferClose tl=(transferClose) o;
                    for(Map.Entry<Club,NetworkUtil> i: clubList.entrySet()){
                        if(i.getKey().getName().equals(tl.getName())){
                            clubList.remove(i.getKey(),i.getValue());
                            break;
                        }
                    }
                }
                else if(o instanceof transferLogOUT){
                    transferLogOUT tl=(transferLogOUT) o;
                    for(Map.Entry<Club,NetworkUtil> i: clubList.entrySet()){
                        if(i.getKey().getName().equals(tl.getName())){
                            clubList.remove(i.getKey(),i.getValue());
                            tl.setName("Done");
                            networkUtil.write(tl);
                            break;
                        }
                    }
                }
                else if(o instanceof transferMarket) {
                    transferMarket tm = (transferMarket) o;
                    tm.setPlayerMarket(playerMarket);
                    networkUtil.write(tm);
                }
                else if(o instanceof transferClub) {
                    transferClub tm = (transferClub) o;
                    if(tm.getMsg().equals("Club")){
                        for (Club c : fa.getClubs()) {
                            if (tm.getClub().equals(c)) {
                                tm.setClub(c);
                                tm.setMsg("Done2");
                                break;
                            }
                        }
                    }
                    else {
                        for (Club c : fa.getClubs()) {
                            if (tm.getMsg().equals(c.getName())) {
                                tm.setClub(c);
                                tm.setMsg("Done");
                                break;
                            }
                        }
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
                            FileIO.writeFile(("market.txt"),playerMarket);
                        }
                        transferPlayerBroadcast tpb=new transferPlayerBroadcast();
                        tpb.setPlayerMarket(playerMarket);
                        for(Map.Entry<Club,NetworkUtil> i: clubList.entrySet()){
                            i.getValue().write(tpb);
                        }
                    }
                    else if(tp.getMethod().equals("Buy")){
                        boolean e=false;
                        Player p=null;
                        int indx=0;
                        for(Player i:playerMarket){
                            if(i.equals(tp.getPlayer())){
                                e=true;
                                break;
                            }
                            indx++;
                        }
                        Club buyer=null;
                        Club seller=null;
                        if(e){
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
                                    if (i.equals(tp.getFromClub())) {
                                        i.addPlayer(p);
                                        p.setC(i);
                                        buyer = i;
                                    }
                                    if (i.equals(seller)) {
                                        i.removePlayer(p);
                                    }
                                }
                                transferMarket tm = new transferMarket();
                                tm.setPlayerMarket(playerMarket);
                                networkUtil.write(tm);
                                transferClubBackground tc=new transferClubBackground("Done");
                                tc.setClub(buyer);
                                networkUtil.write(tc);
                                tc.setClub(seller);
                                for(Map.Entry<Club,NetworkUtil> i: clubList.entrySet()){
                                   if( i.getKey().equals(seller)){
                                        i.getValue().write(tc);
                                        break;
                                    }
                                }
                                transferPlayerBroadcast tpb=new transferPlayerBroadcast();
                                tpb.setPlayerMarket(playerMarket);
                                for(Map.Entry<Club,NetworkUtil> i: clubList.entrySet()){
                                    i.getValue().write(tpb);
                                }
                                try{
                                    FileIO.writeFile("players.txt",fa.getPlayers());
                                    FileIO.writeFile(("market.txt"),playerMarket);

                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                            else{
                                transferClub tc=new transferClub("Same Club");
                                networkUtil.write(tc);
                            }
                        }
                        else{
                            transferMarket tmm = new transferMarket();
                            tmm.setPlayerMarket(playerMarket);
                            networkUtil.write(tmm);
                            transferClub tc=new transferClub("Unavailable");
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