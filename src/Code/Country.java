package Code;

public class Country {
    private String name;
    private int count;

    Country(String name){
        this.name=name;
        count=1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void increaseCount(){
        this.count++;
    }

    @Override
    public String toString() {
        return "Country: "+String.format(name)+" , Count: "+ String.valueOf(count);
    }
}
