package destination.Activities;

import destination.Activity;

public class Beach implements Activity {

    String name;
    String location;
    int avgTemp;

    public Beach(String name, String location, int avgTemp) {
        this.name = name;
        this.location = location;
        this.avgTemp = avgTemp;
    }

    public Integer getTypeSpecific() {
        return avgTemp;
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
