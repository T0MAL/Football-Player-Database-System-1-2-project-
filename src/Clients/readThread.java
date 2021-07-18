//package Clients;
//
//import Code.Club;
//import Code.Player;
//import Transferables.transferClub;
//import Transferables.transferMarket;
//import Transferables.transferPlayer;
//import UI.ClubHomeCtrl;
//import javafx.concurrent.Task;
//import javafx.scene.control.ListView;
//import util.NetworkUtil;
//
//import java.io.IOException;
//import java.util.List;
//
//public class readThread extends Task {
//    private final Thread thr;
//    private NetworkUtil networkUtil;
//    ListView lv;
//    ListView pl;
//    Club c;
//    Player tmp;
//
//    List<Player> marketListPlayers;
//
//    public readThread(NetworkUtil networkUtil, List<Player> mlp, ListView lv,ListView pl,Club c) {
//        this.thr = new Thread(this);
//        this.networkUtil = networkUtil;
//        this.marketListPlayers = mlp;
//        this.lv = lv;
//        this.c=c;
//        this.pl=pl;
//        thr.start();
//    }
//
//
////    @Override
////    public void run() {
////        try {
////            while (true) {
////                Object o = networkUtil.read();
////                if (o instanceof transferMarket) {
////                    transferMarket tmm = (transferMarket) o;
////                    lv.getItems().clear();
////                    marketListPlayers = tmm.getPlayerMarket();
////                    lv.getItems().addAll(marketListPlayers);
////                } else if (o instanceof transferClub) {
////                    transferClub cc = (transferClub) o;
////                    if(cc.getC().equals("Done")){
////                        c.addPlayer(tmp);
////                        tmp.setClub(c.getName());
////                        pl.getItems().clear();
////                        pl.getItems().addAll(c.getPlayers());
////                        tmp=null;
////                    }
////                    else if(cc.getC().equals("Failed")){
////
////                    }
////                    else{
////                        String n=cc.getC();
////                        for(Player i:c.getPlayers()){
////                            if(n.equalsIgnoreCase(i.getName())){
////                                c.removePlayer(i);
////                                break;
////                            }
////                        }
////                        pl.getItems().clear();
////                        pl.getItems().addAll(c.getPlayers());
////                    }
////
////                }
////            }
////        } catch (Exception e) {
////            System.out.println(e);
////        } finally {
////            try {
////                networkUtil.closeConnection();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
//
//    public void Market() {
//        transferMarket tm = new transferMarket();
//        try {
//            networkUtil.write(tm);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sell(int i) {
//        transferPlayer tp = new transferPlayer("forSell");
//        tp.setFromClub(c);
//        tp.setPlayer(c.getPlayers()[i]);
//        try {
//            networkUtil.write(tp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void buy(int i) {
//        transferPlayer tp = new transferPlayer("Buy");
//        tp.setFromClub(c);
//        tmp=marketListPlayers.get(i);
//        tp.setPlayer(tmp);
//        try {
//            networkUtil.write(tp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected Object call() throws Exception {
//        try {
//            while (true) {
//                Object o = networkUtil.read();
//                if (o instanceof transferMarket) {
//                    transferMarket tmm = (transferMarket) o;
//                    lv.getItems().clear();
//                    marketListPlayers = tmm.getPlayerMarket();
//                    lv.getItems().addAll(marketListPlayers);
//                } else if (o instanceof transferClub) {
//                    transferClub cc = (transferClub) o;
//                    if(cc.getC().equals("Done")){
//                        c.addPlayer(tmp);
//                        tmp.setClub(c.getName());
//                        pl.getItems().clear();
//                        pl.getItems().addAll(c.getPlayers());
//                        tmp=null;
//                    }
//                    else if(cc.getC().equals("Failed")){
//
//                    }
//                    else{
//                        String n=cc.getC();
//                        for(Player i:c.getPlayers()){
//                            if(n.equalsIgnoreCase(i.getName())){
//                                c.removePlayer(i);
//                                break;
//                            }
//                        }
//                        pl.getItems().clear();
//                        pl.getItems().addAll(c.getPlayers());
//                    }
//
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                networkUtil.closeConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//}
//
