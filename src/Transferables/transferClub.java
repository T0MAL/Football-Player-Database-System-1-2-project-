package Transferables;

import Code.Club;
import Code.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class transferClub implements Serializable {
    String s;


    public transferClub(String s){
        this.s=s;
    }

    public String getC() {
        return s;
    }
}
