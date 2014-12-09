package model;


/**
 * Write a description of class Time here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Time
{
    private int hour;
    private int minute;
    
    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
    
    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }
}
