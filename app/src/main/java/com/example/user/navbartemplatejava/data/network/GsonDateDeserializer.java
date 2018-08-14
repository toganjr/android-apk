package com.example.user.navbartemplatejava.data.network;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class GsonDateDeserializer implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String asString = json.getAsString();
        try {
            if (DATE_PATTERN.matcher(asString).matches()) {
                return getDateFormat().parse(asString);
            } else if (DATE_TIME_PATTERN.matcher(asString).matches()) {
                return getDateTimeFormat().parse(asString);
            } else {
                throw new JsonParseException("Could not parse to date: " + json);
            }
        } catch (ParseException e) {
            throw new JsonParseException("Could not parse to date: " + json, e);
        }
    }

    private static DateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    private static DateFormat getDateTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        String dateFormatted;
        if (hours == 0 && minutes == 0 && seconds == 0) {
            dateFormatted = getDateFormat().format(date);
        } else {
            dateFormatted = getDateTimeFormat().format(date);
        }
        return new JsonPrimitive(dateFormatted);
    }
}
