package Transferables;

import Code.Club;
import Code.Player;

import java.io.Serializable;

public class transferPlayer implements Serializable {
    private Player player;
    private String method;
    private Club fromClub;

    public transferPlayer(String method){
        this.method=method;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Club getFromClub() {
        return fromClub;
    }

    public void setFromClub(Club fromClub) {
        this.fromClub = fromClub;
    }
}
