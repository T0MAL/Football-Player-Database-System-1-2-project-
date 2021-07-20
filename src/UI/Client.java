//package UI;
//
//import Clients.readThread;
//import Code.Club;
//import Code.Player;
//import util.NetworkUtil;
//
//import java.io.IOException;
//import java.util.List;
//
//public class Client {
//    private NetworkUtil networkUtil;
//    private readThread rt;
//    public Club club;
//    public List<Player> marketList;
//
//    public Client(){
//        try {
//            networkUtil= new NetworkUtil("127.0.0.1",33333);
//            rt=new readThread(networkUtil);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setClub(Club club) {
//        this.club = club;
//    }
//
//    public Club getClub() {
//        return club;
//    }
//
//    public List<Player> getMarketList() {
//        return marketList;
//    }
//
//    public NetworkUtil getNetworkUtil() {
//        return networkUtil;
//    }
//
//    public readThread getRt() {
//        return rt;
//    }
//
//    public void setMarketList(List<Player> marketList){
//        this.marketList=marketList;
//    }
//
//}
