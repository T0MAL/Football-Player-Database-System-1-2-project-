package Transferables;

import java.io.Serializable;

public class transferClose implements Serializable {
    String name;

    public void setName(String name) {
        this.name = name;
    }
    public transferClose(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
