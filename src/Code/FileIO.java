package Code;

import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class FileIO {

    public static List<Player> readFromFile(String fileName) throws IOException {
        List <Player> playerArray= Collections.synchronizedList(new ArrayList<Player>());
        BufferedReader br=new BufferedReader(new FileReader(fileName));
        while(true){
            String tmp=br.readLine();
            if(tmp==null) {
                break;
            }
            String [] tokens=tmp.split(",");
            Player p=new Player();
            p.setName(tokens[0]);
            p.setCountry(tokens[1]);
            p.setAge(Integer.parseInt(tokens[2]));
            p.setHeight(Double.parseDouble(tokens[3]));
            p.setClub(tokens[4]);
            p.setPosition(tokens[5]);
            p.setNumber(Integer.parseInt(tokens[6]));
            p.setWeeklySalary(Double.parseDouble(tokens[7]));
            //String tmp="/images/Players/"+i.getName()+".png";
            p.setImage(new Image(FileIO.class.getResourceAsStream("/Server/images/Players/"+p.getName()+".png")));
            playerArray.add(p);
        }
        br.close();
        return playerArray;
    }

    public static void writeFile(String fileName,List<Player> playerArray) throws IOException {
        BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
        for(Player p:playerArray){
            bw.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getNumber()+","+p.getWeeklySalary());
            bw.write("\n");
        }
        bw.close();
    }

}
