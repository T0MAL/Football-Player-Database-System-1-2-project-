package Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class SearchPlayer {

    public static List<Player> searchByName(String s,List<Player> p){
        List <Player> tmp=new ArrayList();
        for(Player i:p){
            if(i.getName().equalsIgnoreCase(s)){
                tmp.add(i);
                break;
            }
        }
        return tmp;
    }

    public static List<Player> searchByPosition(String s,List<Player> p){
        List <Player> tmp=new ArrayList();
        for(Player i:p){
            if(i.getPosition().equalsIgnoreCase(s)){
                tmp.add(i);
            }
        }
        return tmp;

    }

    public static List<Player> searchByCountryNClub(String country,String club,List<Player> p){
        List <Player> tmp=new ArrayList();
        for(Player i:p){
            if(i.getCountry().equalsIgnoreCase(country)&&club.equalsIgnoreCase("ANY")){
                tmp.add(i);
            }
            else if(i.getCountry().equalsIgnoreCase(country)&&club.equalsIgnoreCase(i.getClub())){
                tmp.add(i);
            }
        }
        return tmp;
    }

    public static List<Player> searchBySalaryRange(int min,int max,List<Player> p){
        List <Player> tmp=new ArrayList();

        for(Player i:p){
            if(i.getWeeklySalary()>=min && i.getWeeklySalary()<=max){
                tmp.add(i);
            }
        }
        return tmp;
    }

    public static Map<String,Integer> countryWiseCount(List <Player> p) {
        Map<String, Integer> hm = new HashMap();
        for (Player i : p) {
            if (hm.containsKey(i.getCountry())) {
                hm.put(i.getCountry(), hm.get(i.getCountry()) + 1);
            } else {
                hm.put(i.getCountry(), 1);
            }
        }
        return hm;
    }

}
