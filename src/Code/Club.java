package Code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {
    //attributes
    private String name;
    private List<Player> players;
    private List<Player> onMarket;
    private int playerCount;
    private double maxSalaray;
    private int oldMan;
    private double tallestMan;
    private double totalSalary;

    public Club(String name,Player p){
        players=new ArrayList<>();
        onMarket=new ArrayList<>();
        this.name=name;
        playerCount=0;
        maxSalaray=0;
        oldMan=0;
        tallestMan=0;
        totalSalary=0;
        addPlayer(p);
    }

    public Club(){
        players=new ArrayList<>();
        onMarket=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers(){
         return players;
    }

    public double getMaxSalaray() {
        return maxSalaray;
    }

    public double getTallestMan() {
        return tallestMan;
    }

    public int getOldMan() {
        return oldMan;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public int getPlayerCount() {
        return playerCount;
    }


    public void addPlayer(Player p){
        players.add(p);
        p.setC(this);
        if(players.get(playerCount).getWeeklySalary()>maxSalaray)
            maxSalaray=players.get(playerCount).getWeeklySalary();
        if(players.get(playerCount).getAge()>oldMan)
            oldMan=players.get(playerCount).getAge();
        if(players.get(playerCount).getHeight()>tallestMan)
            tallestMan=players.get(playerCount).getHeight();
        totalSalary+=players.get(playerCount).getWeeklySalary();
        playerCount++;
    }
//    public void showClub(){
//        System.out.println("--- Club Details of "+name+" ---");
//        System.out.println("Name of the Club: "+ name);
//        System.out.println("Players:");
//        for(int i=0;i<playerCount;i++){
//            System.out.println(players[i]);
//        }
//        System.out.println("\n");
//    }

    public void removePlayer(Player p){
        players.remove(p);
        playerCount--;
    }
    @Override
    public String toString() {
        return String.format(name);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Club)) {
            return false;
        }
        Club c = (Club) o;

        return name.equals(c.getName());

    }

    public List<Player> getOnMarket() {
        return onMarket;
    }

    public void setOnMarket(List<Player> onMarket) {
        this.onMarket = onMarket;
    }

    public void addToMarket(Player p){
        onMarket.add(p);
    }

    public void removeFromMarket(Player p){
        onMarket.remove(p);
    }

    //    @Override
//    public int hashCode()
//    {
//        return ;
//    }
}
