package com.example.ryan.planme;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Created by Ryan on 2/16/2015.
 * Semester object
 * Contains data for the name, beginning, and end of the semester
 */
public class Semester {
    Calendar beginDate;
    Calendar endDate;
    String name;

    public Semester(int beginYear, int beginMonth, int beginDay, int endYear, int endMonth,
                    int endDay, String name) {
        beginDate = new GregorianCalendar(beginYear, beginMonth, beginDay);
        endDate = new GregorianCalendar(endYear, endMonth, endDay);
        this.name = name;
    }

}
