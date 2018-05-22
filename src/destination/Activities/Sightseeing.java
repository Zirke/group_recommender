package destination.Activities;

import destination.Activity;

public class Sightseeing implements Activity {

    String name;
    String location;
    String sightseeingType;

    public Sightseeing(String name, String location, String sightseeingType) {
        this.name = name;
        this.location = location;
        this.sightseeingType = sightseeingType;
    }

    public String getTypeSpecific() {
        return sightseeingType;
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
