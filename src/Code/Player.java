package Code;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.Serializable;

public class Player implements Serializable {
    //attributes
    private String name;
    private String country;
    private String club;
    private int age;
    private int number;
    private String position;
    private double height;
    private double weeklySalary;


    //getters and setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry(){
        return this.country;
    }

    public void setClub(String club){
        this.club=club;
    }

    public String getClub() {
        return this.club;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    //methods
    public void show(){
        System.out.println("--- Player Details of: "+name+" ---");
        System.out.println("Name         : "+name);
        System.out.println("Country      : "+country);
        System.out.println("Age          : "+age);
        System.out.println("Height(meter): "+height);
        System.out.println("Club         : "+club);
        System.out.println("Position     : "+position);
        System.out.println("Number       : "+number);
        System.out.println("Weekly Salary: "+weeklySalary);
        System.out.println("\n");
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

        if (!(o instanceof Player)) {
            return false;
        }
        Player p = (Player) o;

        return name.equals(p.getName())
                && club.equals(p.getClub())
                && country.equals(p.getCountry())
                && position.equals(p.getPosition())
                && age==p.getAge()
                && height==p.getHeight()
                && weeklySalary==p.getWeeklySalary()
                && number==p.getNumber();
    }

}
