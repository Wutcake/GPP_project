package GPP_project.model;


/**
 * Write a description of class Seat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seat
{
    private int number;
    private int row;
    private int reservationID;
    
    public Seat(int number, int row){
        this.number = number;
        this.row = row;
    }
    
    public void setReservationID(int ID){
        reservationID = ID;
        return;
    }
    
    public int getReservationID(){
        return reservationID;
    }
    
    public int getSeatNumber(){
        return number;
    }
    
    public int getSeatRow(){
        return row;
    }
}
