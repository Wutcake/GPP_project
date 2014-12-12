package GPP_project.model;
/**
 * Write a description of class Screening here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screening
{
    private Movie movie;
    private int theaterID;
    private Time time;
    private Date date;
    private int amountSeatsReserved = 0;
    private int screeningID;
    
    public Screening(Movie movie, int theaterID, String time, String date, int ID){
        this.movie = movie;
        this.theaterID = theaterID;
        this.time = new Time(time);
        this.date = new Date(date);
        screeningID = ID;
    }
    
    // Theater accessor methods
    public int getTheaterNumber(){
        return theaterID;
    }
    
    // Movie accessor methods
    public String getMovieTitle(){
        return movie.getMovieTitle();
    }
    
    public String getDate(){
        return date.getDate();
    }
    
    // Date accessor methods
    public int getDay(){
        return date.getDay();
    }
    
    public int getMonth(){
        return date.getMonth();
    }
    
    public int getYear(){
        return date.getYear();
    }
    
    // Time accessor methods
    public int getHour(){
        return time.getHour();
    }
    
    public int getMinute(){
        return time.getMinute();
    }
    
    public String getTime(){
        return time.getTime();
    }
    
    public int getAmountReserved(){
        return amountSeatsReserved;
    }
    
    public void setAmountReserved(int amountSeatsReserved){
        this.amountSeatsReserved = amountSeatsReserved;
        return;
    }

    public String toString()
    {
        return "seatsReserved int(" + amountSeatsReserved + "); " + movie + "; theaterID int(" + theaterID + "); " + time + "; " + date;
    }
    
    public int getScreeningID(){
        return screeningID;
    }
}
