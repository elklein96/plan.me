package com.example.ryan.planme;

import com.google.api.client.util.DateTime;

/**
 * Created by Ryan on 2/16/2015.
 * Semester object
 * Contains data for the name, beginning, and end of the semester
 */
public class Semester {
    //Declare variables
    private DateTime beginDate;
    private DateTime endDate;
    private String name;


    /**
     *
     * @param beginYear
     * @param beginMonth
     * @param beginDay
     * @param endYear
     * @param endMonth
     * @param endDay
     * @param name
     * Constructor method
     */
    public Semester(int beginYear, int beginMonth, int beginDay, int endYear, int endMonth,
                    int endDay, String name) {
        beginDate = DateTime.parseRfc3339(Rfc3339Parser.getRfc3339(beginYear, beginMonth, beginDay, 0, 0, 0));
        endDate = DateTime.parseRfc3339(Rfc3339Parser.getRfc3339(endYear, endMonth, endDay, 23, 59, 59));
        this.name = name;
    }

    /**
     *
     * @return DateTime
     * Returns the beginning date for the semester
     */
    public DateTime getBeginDate(){
        return beginDate;
    }

    /**
     *
     * @return DateTime
     * returns the ending date for the semester
     */
    public DateTime getEndDate(){
        return endDate;
    }

}
