package GPP_project.model;

import java.util.ArrayList;

/**
 * Write a description of class ReservedSeats here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReservedSeats
{
    ArrayList<Seat> seats;
    public ReservedSeats(){
        
        
    }

    public void reserveNewSeat(Seat seat, int ID){
        // Send reserved seats to database. somewhere in here or in seat
        seat.setReservationID(ID);
        seats.add(seat);
    }
    
    
}
