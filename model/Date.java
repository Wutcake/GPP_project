package model;
/**
 * Write a description of class Date here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Date
{
    private int day;
    private int month;
    private int year;
    private String date;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date){
        this.date = date;
    }
    
    public int getDay(){
        return day;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getYear(){
        return year;
    }

    public String toString(){
        return "date '" + date + "'";
    }
}
