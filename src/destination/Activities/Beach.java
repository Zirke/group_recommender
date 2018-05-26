package destination.Activities;

import destination.Activity;

public class Beach implements Activity {

    private String name;
    private String location;
    private int avgTemp;

    public Beach(String name, String location, int avgTemp) {
        this.name = name;
        this.location = location;
        this.avgTemp = avgTemp;
    }

    @Override
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
