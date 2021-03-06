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
    ArrayList<Seat> seats = new ArrayList<Seat>();
    public ReservedSeats(){
    }

    public ArrayList<Seat> getSeats(){
        return seats;
    }

    public void reserveNewSeat(Seat seat){
        seats.add(seat);
    }

    public String toString(){
        String output = "";
        for(int cnt = 0; cnt < seats.size(); cnt++){
            output += "seats[" + cnt + "] == " + seats.get(cnt).toString();
            if(cnt < seats.size()-1)
                output += "; ";
        }
        return output;
    }
}
