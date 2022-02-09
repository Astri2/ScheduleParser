package me.astri.ScheduleParser;

import me.astri.ScheduleParser.Events.UserEvent;

public class App {
    public static void main(String[] args) throws Exception {
        CalendarEDT<UserEvent> cal = new CalendarEDT<>(UserEvent.class, "app/src/main/resources/L1GB.ics");
        cal.init();
    }
}