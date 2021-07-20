package Transferables;

import Code.Club;
import Code.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class transferClubBackground implements Serializable {
    String msg;
    Club club;

    public transferClubBackground(String msg){
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
