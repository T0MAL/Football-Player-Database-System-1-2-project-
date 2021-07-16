package Transferables;

import Code.Club;

import java.io.Serializable;

public class transferLogin implements Serializable {
    private String name;
    private String response;
    private Club club;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public transferLogin(String name){
        this.name=name;
        this.response="pending";
        this.club=null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
