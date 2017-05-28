////////////////////////////////////////////////////////////////////////////
//DESTINATIONTIME CLASS - contains information on desinationTime
//contains setters/getters
package assignment1carseek;

import java.util.Calendar;
import java.util.Date;

public class DestinationTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DestinationTime(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Date getDate() {
        Date newDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        try {
            cal.setTime(newDate);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        } catch (Exception invalidDate) {

            System.out.println("Error: Invalid value for " + invalidDate.getMessage());
        }
        return null;
    }

}
