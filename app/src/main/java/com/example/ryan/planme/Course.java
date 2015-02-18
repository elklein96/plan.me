package com.example.ryan.planme;

//Import lots of stuff
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.api.client.util.DateTime;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.TimeZone;
import java.util.Arrays;


/**
 * Created by Ryan on 2/16/2015.
 * Stores and manages data for a given course
 */
public class Course {
    //Variable Declarations
    private String courseCode;
    private String title;
    private Event[] lectures;
    private Event[] recitations;
    private Event[] labs;
    private int numberOfLectures;
    private int numberOfRecitations;
    private int numberOfLabs;
    private Semester semester;


    /**
     *
     * @param courseCode
     * @param title
     * @param semester
     * Constructor where course code, title of course and semester in which course takes place
     * are passed in.  All other values are set to 0 by default
     */
    public Course(String courseCode, String title, Semester semester) {
        this.courseCode = courseCode;
        this.title = title;
        this.semester = semester;
        lectures =new Event[5];
        numberOfLectures = 0;
        recitations = new Event[5];
        numberOfRecitations = 0;
        labs = new Event[5];
        numberOfLabs = 0;
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
     * Adds a new lecture to the course, repeating once a week on the given day from the beginning
     * to the end of the semester
     */
    public void addLecture(int startHour, int startMin, int endHour, int endMin, String dayOfWeek,
                           String eventName, String eventLocation){
        lectures[numberOfLectures] = EventManager.createRecurringEvent(startHour, startMin,
         endHour, endMin, dayOfWeek, eventName, eventLocation, semester);
        numberOfLectures++;
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
     * Adds a new recitation to the course, repeating once a week on the given day from the beginning
     * to the end of the semester
     */
    public void addRecitation(int startHour, int startMin, int endHour, int endMin, String dayOfWeek,
                           String eventName, String eventLocation){
        recitations[numberOfRecitations] = EventManager.createRecurringEvent(startHour, startMin,
                endHour, endMin, dayOfWeek, eventName, eventLocation, semester);
        numberOfRecitations++;
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
     * Adds a new lab to the course, repeating once a week on the given day from the beginning
     * to the end of the semester
     */
    public void addLab(int startHour, int startMin, int endHour, int endMin, String dayOfWeek,
                           String eventName, String eventLocation){
        labs[numberOfLabs] = EventManager.createRecurringEvent(startHour, startMin,
                endHour, endMin, dayOfWeek, eventName, eventLocation, semester);
        numberOfLabs++;
    }


}
