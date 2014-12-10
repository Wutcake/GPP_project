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
    ReservedSeats seats = new ReservedSeats();

    public Reservation(Screening screening, String name, int phoneNumber){
        this.screening = screening;
        this.customer = new Customer(name, phoneNumber);
    }

    public Reservation(Screening screening, Customer customer){
        this.screening = screening;
        this.customer = customer;
    }
    
    public int getPhoneNumber(){
        return customer.getPhoneNumber();
    }
    
    public String getName(){
        return customer.getName();
    }
    
    // reservation methods
    public void reserveNewSeat(Seat seat){
        seats.reserveNewSeat(seat);
    }
   
    public ReservedSeats getSeats(){
        return seats;
    }

    public String toString(){
        return screening + "; " + customer + "; " + seats;
    }
}
