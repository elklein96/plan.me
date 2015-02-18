package com.example.ryan.planme;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Ryan on 2/18/2015.
 * Parses rfc3339 date/time format strings
 */
public class Rfc3339Parser {

    /**
     *
     * @param rfc3339Date
     * @return String
     * Pass in an rfc3339 style date/time, parse the string to increment the date and return a
     * string with a day one greater than the day of the one passed in
     */
    public static String incrementDate(String rfc3339Date){
        //Parse the year, month, day, hour, minute, and second from the rfc339 string
        int year = Integer.parseInt(rfc3339Date.substring(0, 4));
        int month = Integer.parseInt(rfc3339Date.substring(5, 7));
        int day = Integer.parseInt(rfc3339Date.substring(8, 10));
        int hour = Integer.parseInt(rfc3339Date.substring(11, 13));
        int min = Integer.parseInt(rfc3339Date.substring(14, 16));
        int sec = Integer.parseInt(rfc3339Date.substring(17, 19));

        //Figure out what the next day is and set date to the next day
        if(validDayOfMonth(day++, month, year))
            day++;
        else{
            day = 1;
            if(isValidMonth(month++))
                month++;
            else{
                month = 1;
                year++;
            }
        }

        //Parse the new values back into an rfc3339 date/time string and return
        String returnRfc3339 = getRfc3339(year, month, day, hour, min, sec);
        return returnRfc3339;
    }

    /**
     *
     * @param day
     * @param month
     * @param year
     * @return boolean
     * Checks if the passed in day is withing the range for a given month.  E.g. between 1 and
     * 31 for January
     */
    private static boolean validDayOfMonth(int day, int month, int year){
        switch(month){
            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                if(day <= 31)
                    return true;
                break;
            case 4:case 6: case 9: case 11:
                if(day <= 30)
                    return true;
                break;
            case 2:
                if(isLeapYear(year) && day <= 29)
                    return true;
                else if(day <= 28)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }

    /**
     *
     * @param year
     * @return boolean
     * Returns true if the passed in year is a leap year
     */
    private static boolean isLeapYear(int year){
        if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)){
            return true;
        }
        return false;
    }

    /**
     *
     * @param month
     * @return boolean
     * Returns true if the passed in month is between 1 and 12, inclusive
     */
    private static boolean isValidMonth(int month){
        if(month >= 1 && month <= 12){
            return true;
        }
        return false;
    }

    /**
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param min
     * @param sec
     * @return String
     * Takes values for year, month, day, hour, minute, and second then parses them and UTC time
     * zone into valid rfc3339 date/time string
     */
    public static String getRfc3339(int year, int month, int day, int hour, int min, int sec){
        String date = year + "-" + String.format("%02d", month) + "-" + String.format("%02d",
                day) + "T" + hour + ":" + min + ":" + sec + ".000";
        int gmtOffset = getTimeZone();
        if(gmtOffset < 0){
            date += "-" + String.format("%02d", gmtOffset) + ":00";
        }
        else{
            date += "+" + String.format("%02d", gmtOffset) + ":00";
        }
        return date;
    }

    /**
     *
     * @return int
     * Gets the system time zone
     */
    private static int getTimeZone(){
        Calendar c = Calendar.getInstance();
        TimeZone tz = c.getTimeZone();
        int offsetMillis = tz.getRawOffset();
        int offsetHours = offsetMillis/3600000;
        return offsetHours;
    }

    /**
     *
     * @param rfc3339Date
     * @return String
     * Determines the day of the week for a given date in the rfc3339 standard format, passed in
     * as a string.  Loosely based off Zeller's Congruence
     */
    public static String getDayOfWeek(String rfc3339Date){
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        int year = Integer.parseInt(rfc3339Date.substring(0, 4));
        int month = Integer.parseInt(rfc3339Date.substring(5, 7));
        int day = Integer.parseInt(rfc3339Date.substring(8, 10));

        //Make February the last month
        month -= 2;
        if (month < 1) {
            month += 12;
            year--;
        }

        //Split up the century and the number of years into that century
        int century = year / 100;
        year %= 100;

        //Calculate and return.  Nifty, huh?
        return days[((26 * month - 2) / 10 + day + year + year / 4 + century / 4 + 5 * century) % 7];
    }

    public static String changeTime(String rfc3339DateTime, int hour, int min, int sec){
        String newRfc3339DateTime = rfc3339DateTime.substring(0, 11);
        newRfc3339DateTime += String.format("%02d", hour) + ":" + String.format("%02d", min) +
                ":" + String.format("%02d", sec) + ".000";
        newRfc3339DateTime += rfc3339DateTime.substring(23);
        return newRfc3339DateTime;
    }
}
