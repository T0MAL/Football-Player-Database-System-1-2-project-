package Clients;

import Code.Club;
import Code.Player;
import Transferables.transferClub;
import Transferables.*;
import UI.Main;
import javafx.application.Platform;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class readThread implements Runnable {
    private final Thread thr;
    private Main main;

    public readThread(Main main) {
        this.main=main;
        this.thr = new Thread(this);
        thr.start();
    }


    @Override
    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if(o instanceof transferLogin){
                    transferLogin tmp=(transferLogin) o;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(tmp.getResponse().equals("Accepted")){
                                main.setClub(tmp.getClub());
                                main.showClubHome(0);
                            }
                            else if(tmp.getResponse().equals("Exists")){
                                main.showAlert("Already signed in");
                            }
                            else{
                                main.showAlert("Club doesn't exist");
                            }
                        }
                    });

                }
                else if (o instanceof transferMarket) {
                    transferMarket tmm = (transferMarket) o;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            main.setMarketListPlayers(tmm.getPlayerMarket());
                            System.out.println(main.getMarketListPlayers());
                            main.showClubHome(3);
                        }
                    });

                } else if (o instanceof transferClub) {
                    transferClub cc = (transferClub) o;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(cc.getMsg().equals("Done")){
                                main.setClub(cc.getClub());
                                main.showClubHome(1);
                            }
                            else if(cc.getMsg().equals("Done2")){
                                main.setClub(cc.getClub());
                                main.showClubHome(2);
                            }
                            else if(cc.getMsg().equals("Same Club")){
                                main.showAlert("Cant buy this player");
                            }
                            else if(cc.getMsg().equals("Unavailable")){
                                main.showAlert("Player not available in the market");
                            }
                        }
                    });



                }
                else if(o instanceof transferClubBackground){
                    transferClubBackground tcb=(transferClubBackground) o;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(tcb.getMsg().equals("Done")){
                                main.setClub(tcb.getClub());
                            }
                        }
                    });

                }
                else if(o instanceof transferPlayerBroadcast){
                    transferPlayerBroadcast tpb=(transferPlayerBroadcast) o;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            main.setMarketListPlayers(tpb.getPlayerMarket());
                            if(main.isInMarket()){
                                main.showClubHome(3);
                            }
                        }
                    });
                }
                else if(o instanceof  transferLogOUT){
                    transferLogOUT tl=(transferLogOUT) o;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            main.setClub(null);
                            main.setInMarket(false);
                            main.setMarketListPlayers(null);
                            main.showLoginWindow();
                        }
                    });
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
