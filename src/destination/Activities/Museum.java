package destination.Activities;

import destination.Activity;

public class Museum implements Activity {

    private String name;
    private String location;
    private String openingHours;

    public Museum(String name, String location, String openingHours) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
    }

    @Override
    public String getTypeSpecific() {
        return openingHours;
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
