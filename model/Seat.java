package GPP_project.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;


/**
 * Write a description of class Seat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seat
{
    private final int seatID;
    private final int number;
    private boolean isSelected = false;
    
    public Seat(int number, int seatID){
        this.seatID = seatID;
        this.number = number;
    }
    
    public void select(){
        isSelected = !isSelected;
    }
    
    public int getValue(){
        return reservationID.get();
    }

    public int getSeatNumber(){
        return number;
    }
    
    public boolean isSelected(){
        return isSelected;
    }

    public String toString(){
        return "seatNumber int(" + number + "); isSelected boolean(" + isSelected + ")";
    }
}
