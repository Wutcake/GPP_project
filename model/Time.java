package GPP_project.model;


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
    private String time;
    
    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public Time(String time)
    {
        this.time = time;
    }
    
    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public String getTime() {
        return time;
    }

    public String toString(){
        return "time '" + time + "'";
    }
}
