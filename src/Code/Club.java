package Code;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {
    //attributes
    private String name;
    private List<Player> players;
    private MyImage img=new MyImage();

    public Club(String name,Player p){
        players=new ArrayList<>();
        this.name=name;
        setImage(new Image(FileIO.class.getResourceAsStream("/Server/images/Clubs/"+this.name+".png")));
        addPlayer(p);
    }

    public Club(){
        players=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers(){
         return players;
    }




    public void addPlayer(Player p){
        players.add(p);
        p.setC(this);
    }
    public Image getImage() {
        return img.getImage();
    }

    public void setImage(Image image) {
        img.setImage(image);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }
    @Override
    public String toString() {
        return String.format(name);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Club)) {
            return false;
        }
        Club c = (Club) o;

        return name.equals(c.getName());

    }


    //    @Override
//    public int hashCode()
//    {
//        return ;
//    }
}
