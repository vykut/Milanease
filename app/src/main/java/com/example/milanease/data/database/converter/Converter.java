package com.example.milanease.data.database.converter;

import androidx.room.TypeConverter;

import com.example.milanease.core.ui.dashboard.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Converter {

    @TypeConverter
    public static List<Utility> fromUtilityString(String s) {
        String[] utilities = s.split(",");
        List<Utility> utilityList = new ArrayList<>();
        for (String utility : utilities) {
            utilityList.add(Utility.valueOf(utility));
        }
        return utilityList;
    }

    @TypeConverter
    public static String fromUtilities(List<Utility> utilities) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Utility utility:
             utilities) {
            stringBuilder.append(utility.toString());
            stringBuilder.append(",");
        }

        return stringBuilder.toString();
    }

    @TypeConverter
    public static String fromUtility(Utility utility) {
        return utility.toString();
    }

    @TypeConverter
    public static Utility fromString(String s) {
        return Utility.valueOf(s);
    }

    @TypeConverter
    public static long fromCalendar(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public static Calendar fromTimestamp(Long l) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(l);
        return c;
    }
}
