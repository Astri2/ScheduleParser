package me.astri.ScheduleParser;

import me.astri.ScheduleParser.Events.Event;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalendarEDT<T extends Event> {
    final Class<T> eventType;
    final String filePath;
    final HashMap<String, ArrayList<T>> days = new HashMap<>();

    public CalendarEDT(Class<T> eventType, String filePath) {
        this.eventType = eventType;
        this.filePath = filePath;
    }

    private static final Pattern eventPropertiesPattern = Pattern.compile("(.+?):(.*?)\\r\\n", Pattern.DOTALL);
    private static final Pattern eventP = Pattern.compile("BEGIN:VEVENT(.+?)END:VEVENT", Pattern.DOTALL);
    public void init() throws IOException {
        String file = Files.readString(Paths.get(this.filePath), StandardCharsets.UTF_8).replace("\r\n ","");
        Matcher eventM = eventP.matcher(file);

        while(eventM.find()) {
            try {
                Matcher m = eventPropertiesPattern.matcher(eventM.group(1));
                HashMap<String,String> map = new HashMap<>();
                while(m.find()) {
                    map.put(m.group(1),m.group(2)); // group(0) = whole find
                }
                if(map.size() == 0) throw new Exception("Can't find event properties",new Throwable(eventM.group(1)));
                System.out.println(eventM.group(1));
                System.out.println(map);
                @SuppressWarnings("unchecked")
                T e = (T) eventType.getConstructors()[0].newInstance(map);

                String date = e.getStart().split("T")[0]; //remove time
                days.computeIfAbsent(date, k -> new ArrayList<>());
                days.get(date).add(e);

            } catch(Exception e) {e.printStackTrace(); return;}
        }
        days.get("20220128").forEach(System.out::println);
    }
}
