package GPP_project.model;
/**
 * Write a description of class Reservation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reservation
{
    Screening screening;
    Customer customer;
    ReservedSeats seats;
    public Reservation(Screening screening, String name, int phoneNumber){
        this.screening = screening;
        this.customer = new Customer(name, phoneNumber);
    }
    
    public int getPhoneNumber(){
        return customer.getPhoneNumber();
    }
    
    public String getName(){
        return customer.getName();
    }
    
    // reservation methods
    public void reserveNewSeat(Seat seat){
        seats.reserveNewSeat(seat, customer.getReservationID());
    }
   
    public ReservedSeats getSeats(){
        return seats;
    }
    
    
    
}
