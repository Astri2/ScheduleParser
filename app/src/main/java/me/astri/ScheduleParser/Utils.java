package me.astri.ScheduleParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
