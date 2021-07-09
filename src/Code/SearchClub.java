package Code;

import java.util.ArrayList;
import java.util.List;

abstract public class SearchClub {

    public static List<Player> maxSalary(String s,List<Club> c){
        List <Player> tmp=new ArrayList();
        for(Club i:c){
            if(i.getName().equalsIgnoreCase(s)){
                Player[] pp=i.getPlayers();
                for(int j=0;j<i.getPlayerCount();j++){
                    if(pp[j].getWeeklySalary() == i.getMaxSalaray()){
                        tmp.add(pp[j]);
                    }
                }
                break;
            }
        }
        return tmp;
    }

    public static List<Player> maxAge(String s,List<Club> c){
        List <Player> tmp=new ArrayList();
        for(Club i:c){
            if(i.getName().equalsIgnoreCase(s)){
                Player[] pp=i.getPlayers();
                for(int j=0;j<i.getPlayerCount();j++){
                    if(pp[j].getAge()==i.getOldMan()){
                        tmp.add(pp[j]);
                    }
                }
                break;
            }
        }
        return tmp;
    }

    public static List<Player> maxHeight(String s,List<Club> c){
        List <Player> tmp=new ArrayList();
        for(Club i:c){
            if(i.getName().equalsIgnoreCase(s)){
                Player[] pp=i.getPlayers();
                for(int j=0;j<i.getPlayerCount();j++)
                    if (Double.compare(pp[j].getHeight(), i.getTallestMan()) == 0) {
                        tmp.add(pp[j]);
                    }
                break;
            }
        }
        return tmp;
    }

    public static double totalSalary(String s,List<Club> c){
        for(Club i:c){
            if(i.getName().equalsIgnoreCase(s)){
                return 52*i.getTotalSalary();
            }
        }
        return 0;
    }

}
