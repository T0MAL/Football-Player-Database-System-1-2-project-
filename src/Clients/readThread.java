package Clients;

import Transferables.transferMarket;
import util.NetworkUtil;

import java.io.IOException;

public class readThread implements Runnable{
    private final Thread thr;
    private NetworkUtil networkUtil;

    public readThread(NetworkUtil networkUtil) {
        this.thr = new Thread(this);
        this.networkUtil=networkUtil;
        thr.start();
    }

    @Override
    public void run() {
        try{
            while(true){
                Object o=networkUtil.read();
                if(o instanceof transferMarket){
                    transferMarket tm=(transferMarket) o;

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
