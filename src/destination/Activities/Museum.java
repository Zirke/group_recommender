package destination.Activities;

import destination.Activity;

public class Museum implements Activity {

    String name;
    String location;

    public Museum(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getType() {
        return "Museum";
    }

    @Override
    public String getLocation(){
        return location;
    }
}
