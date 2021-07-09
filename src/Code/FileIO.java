package Code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract public class FileIO {

    public static List<Player> readFromFile() throws IOException {
        List <Player> playerArray=new ArrayList();
        BufferedReader br=new BufferedReader(new FileReader("players.txt"));
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
            playerArray.add(p);
        }
        br.close();
        return playerArray;
    }

    public static void writeFile(List<Player> playerArray) throws IOException {
        BufferedWriter bw=new BufferedWriter(new FileWriter("players.txt"));
        for(Player p:playerArray){
            bw.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getNumber()+","+p.getWeeklySalary());
            bw.write("\n");
        }
        bw.close();
    }

}
