package Transferables;

import Code.Club;
import Code.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class transferMarket implements Serializable {
    List<Player> playerMarket=new ArrayList<>();
    public List<Player> getPlayerMarket() {
        return playerMarket;
    }

}
