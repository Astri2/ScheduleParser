package me.astri.ScheduleParser.Events;

import java.util.HashMap;

public abstract class Event {
    protected final String summary;
    protected String description;
    protected final String start;
    protected final String end;

    protected Event(HashMap<String,String> map) {
        this.summary = map.get("SUMMARY");
        this.start = map.get("DTSTART");
        this.end = map.get("DTEND");

        String desc = map.get("DESCRIPTION"); //remove unnecessary \n\n and footer
        this.description = desc.substring(4,desc.lastIndexOf("\\n(Exported"));
    }


    @Override
    public String toString() {
        return "Summary:%s\nDesc:%s\nstart:%s\nend:%s\n".formatted(this.summary,this.description,this.start,this.end);
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }


    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
