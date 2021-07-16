package Server;

import Code.Club;
import Code.FootballAssociation;
import Code.Player;
import Transferables.transferLogin;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    Map<Club, NetworkUtil> clientList;
    Map<Player,Club> playerMarket;
    volatile private boolean serverOn;
    FootballAssociation fa;

    public Server(FootballAssociation fa) {

        this.fa = fa;
        playerMarket=new HashMap<>();
        serverOn = true;
        clientList=new HashMap<>();
        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("Server Online");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        Object o = networkUtil.read();
        transferLogin tl = (transferLogin) o;
        boolean found=false;

        for (Club c : fa.getClubs()) {
            if (c.getName().equalsIgnoreCase(tl.getName()) ) {
                found = true;
                if(!clientList.containsKey(c)) {
                    System.out.println("loggedin");
                    tl.setResponse("Accepted");
                    tl.setClub(c);
                    networkUtil.write(tl);
                    clientList.put(c, networkUtil);
                    new ServerThread(clientList, networkUtil,playerMarket,fa);

                    break;
                }
                else{
                    tl.setResponse("Exists");
                    networkUtil.write(tl);
                    networkUtil.closeConnection();
                }
            }
        }
        if(!found) {
            tl.setResponse("Denied");
            networkUtil.write(tl);
            networkUtil.closeConnection();
        }
    }

    @Override
    public void run() {
        try {
            while (serverOn) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setServerOn(boolean serverOn) {
        this.serverOn = serverOn;
    }
}
