package Code;

import java.util.ArrayList;
import java.util.List;

abstract public class SearchClub {

    public static List<Player> maxSalary(String s,List<Player> p){
        List <Player> tmp=new ArrayList();
        double maxSal=0;
        for(Player i:p) {
            if (i.getClub().equalsIgnoreCase(s)) {
                if (i.getWeeklySalary() > maxSal) {
                    tmp.clear();
                    tmp.add(i);
                    maxSal = i.getWeeklySalary();
                } else if (i.getWeeklySalary() == maxSal) {
                    tmp.add(i);
                }
            }
        }
        return tmp;
    }

    public static List<Player> maxAge(String s,List<Player> p) {
        List<Player> tmp = new ArrayList();
        int maxAge=0;
        for(Player i:p){
            if (i.getClub().equalsIgnoreCase(s)) {
                if (i.getAge() > maxAge) {
                    tmp.clear();
                    tmp.add(i);
                    maxAge = i.getAge();
                } else if (i.getAge() == maxAge) {
                    tmp.add(i);
                }
            }
        }
        return tmp;
    }


    public static List<Player> maxHeight(String s,List<Player> p){
        List <Player> tmp=new ArrayList();
        double maxHeight=0;
        for(Player i:p){
            if (i.getClub().equalsIgnoreCase(s)) {
                if (i.getHeight() > maxHeight) {
                    tmp.clear();
                    tmp.add(i);
                    maxHeight = i.getHeight();
                } else if (i.getHeight() == maxHeight) {
                    tmp.add(i);
                }
            }
        }
        return tmp;
    }

    public static double totalSalary(String s,List<Player> p){
        double total=0;
        for(Player i:p){
            if (i.getClub().equalsIgnoreCase(s)) {
                total += i.getWeeklySalary();
            }
        }
        return 52*total;
    }

}
