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
    private Theater theater;
    private Time time;
    private Date date;
    private int screeningID;
    
    
    public Screening(Movie movie, Theater theater, Time time, Date date){
        this.movie = movie;
        this.theater = theater;
        this.time = time;
        this.date = date;    
    }
    // Screening methods
    public void setScreeningID(int ID){
        screeningID = ID;
    }
    
    
    // Theater accessor methods
    public int getTheaterNumber(){
        return theater.getTheaterNumber();
    }
    
    public Seat getSeat(int row, int col){
        return theater.getSeat(row, col);
    }
    
    public int getSeatNumber(int row, int column){
        return theater.getSeatNumber(row, column);
    }
    
    public int getRowLength(){
        return theater.getRowLength();
    }
    
    public int getRowAmount(){
        return theater.getRowAmount();
    }
    
    public int getReservationID(int row, int column){
        return theater.getReservationID(row, column);
    }
    
    // Movie accessor methods
    public String getMovieTitle(){
        return movie.getMovieTitle();
    }
    
    public int getMovieID(){
        return movie.getMovieID();
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
    
    public int getMinite(){
        return time.getMinute();
    }
}
