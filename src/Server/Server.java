package Server;

import Code.Club;
import Code.FileIO;
import Code.FootballAssociation;
import Code.Player;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
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
            playerMarket=FileIO.readFromFile("market.txt");
            System.out.println(playerMarket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientList= Collections.synchronizedMap(new HashMap<>());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ServerThread(clientList, networkUtil,playerMarket,fa);
        System.out.println("Accepted one client");
    }

    public static void main(String[] args) {
        new Server();
    }
}