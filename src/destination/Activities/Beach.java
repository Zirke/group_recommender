package destination.Activities;

import destination.Activity;

public class Beach implements Activity {

    String name;
    String location;

    public Beach(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getType() {
        return "Beach";
    }

    @Override
    public String getLocation(){
        return location;
    }
}
