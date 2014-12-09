package model;

import java.util.ArrayList;

/**
 * Write a description of class SeatRow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SeatRow
{
    ArrayList<Seat> seatRow;
    
    public SeatRow(ArrayList<Seat> seatRow){
        this.seatRow = seatRow;
        
    }
    
    public int getSeatNumber(int column){
        return seatRow.get(column).getSeatNumber();
    }
    
    public int getSeatReservationID(int column){
        return seatRow.get(column).getReservationID();
    }
    
    public int getLength(){
        return seatRow.size();
    }
       
}
