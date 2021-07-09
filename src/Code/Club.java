package Code;

import java.util.ArrayList;
import java.util.List;

public class Club {
    //attributes
    private String name;
    private Player[] players;
    private int playerCount;
    private double maxSalaray;
    private int oldMan;
    private double tallestMan;
    private double totalSalary;
    private List<Integer> pNumbers;

    public Club(String name,Player p){
        this.name=name;
        players=new Player[7];
        playerCount=0;
        maxSalaray=0;
        oldMan=0;
        tallestMan=0;
        totalSalary=0;
        pNumbers=new ArrayList();
        addPlayer(p);
    }

    public String getName() {
        return name;
    }

    public Player[] getPlayers(){
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

    public List<Integer> getPNumbers() {
        return pNumbers;
    }

    public void addPlayer(Player p){
        players[playerCount]=p;
        if(players[playerCount].getWeeklySalary()>maxSalaray)
            maxSalaray=players[playerCount].getWeeklySalary();
        if(players[playerCount].getAge()>oldMan)
            oldMan=players[playerCount].getAge();
        if(players[playerCount].getHeight()>tallestMan)
            tallestMan=players[playerCount].getHeight();
        totalSalary+=players[playerCount].getWeeklySalary();
        pNumbers.add(p.getNumber());
        playerCount++;
    }
    public void showClub(){
        System.out.println("--- Club Details of "+name+" ---");
        System.out.println("Name of the Club: "+ name);
        System.out.println("Players:");
        for(int i=0;i<playerCount;i++){
            System.out.println(i+1+". "+players[i].getName());
        }
        System.out.println("\n");
    }
}
