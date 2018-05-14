package destination.Activities;

import destination.Activity;

public class Sightseeing implements Activity {

    String name;
    String location;

    public Sightseeing(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getType() {
        return "Sightseeing";
    }

    @Override
    public String getLocation(){
        return location;
    }
}
