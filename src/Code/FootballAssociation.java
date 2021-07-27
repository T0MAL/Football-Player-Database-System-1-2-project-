package Code;

import javafx.scene.image.Image;

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
        tmpPlayer=FileIO.readFromFile("players.txt");
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

    public void addPlayer(Player p){
        players.add(p);
        boolean foundInClub=false;
        for(Club j:clubs){
            if(p.getClub().equalsIgnoreCase(j.getName())){
                j.addPlayer(p);
                foundInClub=true;
            }
        }
        if(!foundInClub)
            clubs.add(new Club(p.getClub(),p));
    }

}
