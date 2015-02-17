package com.example.ryan.planme;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Created by Ryan on 2/16/2015.
 */
public class Course {
    private String courseCode;
    private String title;
    private GregorianCalendar[] lectures;
    private GregorianCalendar[] recitations;
    private GregorianCalendar[] labs;
    private Semester semester;

    public Course(String courseCode, String title, Semester semester) {
        this.courseCode = courseCode;
        this.title = title;
        this.semester = semester;
        lectures = new GregorianCalendar[semester.getNumberOfWeeks() * 5];
        recitations = new GregorianCalendar[semester.getNumberOfWeeks() * 5];
        labs = new GregorianCalendar[semester.getNumberOfWeeks() * 5];
    }

    public String getCourseCode(){
        return courseCode;
    }

    public String getTitle() {
        return title;
    }



}
