package Code;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class FootballAssociation {
    //attributes
    private List<Player> players;
    private List<Club> clubs;

    //constructor
    public FootballAssociation() throws IOException {
        players=new ArrayList();
        clubs=new ArrayList();
        List<Player> tmpPlayer=new ArrayList();
        tmpPlayer=FileIO.readFromFile();
        for(Player i: tmpPlayer){
            addPlayer(i);
        }
    }

    //getters and setters
    public List<Player> getPlayers(){
        return players;
    }
    public List<Club> getClubs(){ return clubs;}

    //methods
    public void showAllPlayers(){
        for(Player i:players){
            i.show();
        }
    }
    public void showAllClubs(){
        for(Club i:clubs){
            i.showClub();
        }
    }
    public int addPlayer(Player p){
        for(Player i: players){
            if(i.getName().equalsIgnoreCase(p.getName())){
                return 1;
            }
        }
        for(Club j:clubs){
            if(p.getClub().equalsIgnoreCase(j.getName())){
                boolean tmp1=j.getPNumbers().contains(p.getNumber());
                boolean tmp2=j.getPlayerCount()>=7;
                if(tmp1 && tmp2) {
                    return 4;
                }
                else if(tmp2) {
                    return 2;
                }
                else if(tmp1)
                    return 3;
                players.add(p);
                j.addPlayer(p);
                return 0;
            }
        }
        players.add(p);
        clubs.add(new Club(p.getClub(),p));
        return 0;
    }

}
