package Clients;

import util.NetworkUtil;

import java.io.IOException;

public class writeThread implements Runnable{
    private final Thread thr;
    NetworkUtil networkUtil;

    public writeThread(NetworkUtil networkUtil) {
        this.thr = new Thread(this);
        this.networkUtil=networkUtil;
        thr.start();
    }

    @Override
    public void run() {
        try{

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
