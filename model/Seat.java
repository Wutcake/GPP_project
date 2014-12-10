package model;


/**
 * Write a description of class Seat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seat
{
    private final int number;
    private boolean isSelected = false;
    
    public Seat(int number){
        this.number = number;
    }
    
    public void select(){
        isSelected = !isSelected;
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
