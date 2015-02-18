package com.example.ryan.planme;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.util.Arrays;
import java.util.TimeZone;

/**
 * Created by Ryan on 2/18/2015.
 * Manages java event objects
 */
public class EventManager {
    /**
     *
     * @param startHour
     * @param startMin
     * @param endHour
     * @param endMin
     * @param dayOfWeek
     * @param eventName
     * @param eventLocation
     * @return Event
     * Create and return a new event
     */
    public static Event createEvent(int startHour, int startMin, int endHour, int endMin,
                                    String dayOfWeek, String eventName, String eventLocation,
                                    Semester semester){
        Event event = new Event();
        DateTime start;
        DateTime end;
        event.setSummary(eventName);
        event.setLocation(eventLocation);
        DateTime beginTime = semester.getBeginDate();
        int i;
        //Figure out when the first date of the event for the semester is
        outerloop: for(i = 0; i < 7; i++){
            beginTime = DateTime.parseRfc3339(Rfc3339Parser.incrementDate(beginTime.toStringRfc3339()));
            if(Rfc3339Parser.getDayOfWeek(beginTime.toStringRfc3339()).equalsIgnoreCase(dayOfWeek)) {
                break outerloop;
            }
        }
        beginTime = DateTime.parseRfc3339(Rfc3339Parser.changeTime(beginTime.toStringRfc3339(),
                startHour, startMin, 00));
        DateTime endTime = DateTime.parseRfc3339(Rfc3339Parser.changeTime(beginTime
                .toStringRfc3339(), endHour, endMin, 00));

        //Set beginning and end dates
        start = beginTime;
        end = endTime;
        event.setStart(new EventDateTime().setDateTime(start).setTimeZone(getTimeZone()));
        event.setEnd(new EventDateTime().setDateTime(end).setTimeZone(getTimeZone()));

        return event;
    }

    /**
     *
     * @return String
     * Determines and returns the system timezone ID
     */
    private static String getTimeZone(){
        java.util.Calendar c = java.util.Calendar.getInstance();
        TimeZone tz = c.getTimeZone();
        return tz.getID();
    }

    /**
     *
     * @param startHour
     * @param startMin
     * @param endHour
     * @param endMin
     * @param dayOfWeek
     * @param eventName
     * @param eventLocation
     * @param semester
     * @return Event
     * Create and return a new recurring event
     */
    public static Event createRecurringEvent(int startHour, int startMin, int endHour, int endMin,
                                             String dayOfWeek, String eventName,
                                             String eventLocation, Semester semester){
        Event recurringEvent = createEvent(startHour, startMin, endHour, endMin, dayOfWeek,
                eventName, eventLocation, semester);

        //Make date weekly, recurring
        recurringEvent.setRecurrence(Arrays.asList("RRULE:FREQ=WEEKLY;UNTIL=20110701T170000Z"));
        return recurringEvent;

    }
}
