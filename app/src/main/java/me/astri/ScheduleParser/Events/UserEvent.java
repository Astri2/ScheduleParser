package me.astri.ScheduleParser.Events;

import me.astri.ScheduleParser.Events.Event;

import java.util.HashMap;

public class UserEvent extends Event {
    protected final String location;

    public UserEvent(HashMap<String,String> map) {
        super(map);
        this.location = map.get("LOCATION");
    }
    @Override
    public String toString() {
        return super.toString() + "location:%s\n".formatted(this.location);
    }

    public String getLocation() {
        return location;
    }
}
