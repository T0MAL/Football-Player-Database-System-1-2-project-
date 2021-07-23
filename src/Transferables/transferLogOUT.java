package Transferables;

import java.io.Serializable;

public class transferLogOUT implements Serializable {
    String name;

    public void setName(String name) {
        this.name = name;
    }
    public transferLogOUT(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
