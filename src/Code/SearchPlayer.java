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

    public static List<Country> countryWiseCount(List <Player> p) {
        List<Country> country=new ArrayList<>();
        boolean isFound;
        for (Player i : p) {
            isFound=false;
            for(Country c:country){
                if(c.getName().equalsIgnoreCase(i.getCountry())){
                    c.increaseCount();
                    isFound=true;
                    break;
                }
            }
            if(!isFound){
                country.add(new Country(i.getCountry()));
            }
        }
        return country;
    }

}
