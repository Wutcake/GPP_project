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
    private final int number;
    private final int row;
    private final IntegerProperty reservationID = new SimpleIntegerProperty();
    private boolean isSelected = false;
    
    public Seat(int number, int row){
        this.number = number;
        this.row = row;
        reservationID.set(0);
    }
    
    public void select(){
        isSelected = !isSelected;
    }
    
    public void setReservationID(int ID){
        reservationID.set(ID);
        return;
    }
    
    public IntegerProperty reservationIDProperty(){
        return reservationID;
    }
    
    public int getValue(){
        return reservationID.get();
    }
    
    public int getReservationID(){
        return reservationID.get();
    }
    
    public int getSeatNumber(){
        return number;
    }
    
    public int getSeatRow(){
        return row;
    }
    
    public boolean isSelected(){
        return isSelected;
    }
}
