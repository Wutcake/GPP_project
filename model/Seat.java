package model;

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
<<<<<<< HEAD
=======
    private final int row;
    private final IntegerProperty reservationID = new SimpleIntegerProperty();
>>>>>>> origin/master
    private boolean isSelected = false;
    
    public Seat(int number){
        this.number = number;
<<<<<<< HEAD
=======
        this.row = row;
        reservationID.set(0);
>>>>>>> origin/master
    }
    
    public void select(){
        isSelected = !isSelected;
    }
    
<<<<<<< HEAD
=======
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
    
>>>>>>> origin/master
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
