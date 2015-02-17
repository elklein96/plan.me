package com.example.ryan.planme;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Created by Ryan on 2/16/2015.
 */
public class Course {
    String courseCode;
    String title;
    Calendar[] lectures;
    Calendar[] recitations;
    Calendar[] labs;
    Semester semester;

    public Course(String courseCode, String title, Semester semester) {
        this.courseCode = courseCode;
        this.title = title;
        this.semester = semester;
    }

}
