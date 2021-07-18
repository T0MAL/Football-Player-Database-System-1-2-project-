package Server;

import Code.Club;
import Code.FileIO;
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

public class Server{
    private ServerSocket serverSocket;
    private Map<Club, NetworkUtil> clientList;
    private List<Player> playerMarket;
    private FootballAssociation fa;

    public Server() {
        try {
            fa=new FootballAssociation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerMarket=new ArrayList<>();
        clientList=new HashMap<>();
        try {
            serverSocket = new ServerSocket(33333);
            System.out.println("ServerOn");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            serverSocket.close();
            FileIO.writeFile(fa.getPlayers());
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

    public static void main(String[] args) {
        new Server();
    }
}
