package GPP_project.model;
/**
 * Write a description of class Reservation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reservation
{
    private Screening screening;
    private Customer customer;
    private ReservedSeats seats = new ReservedSeats();
	private int ReservationID;

    public Reservation(Screening screening, Customer customer){
        this.screening = screening;
        this.customer = customer;
		reservationID = 0;
    }
	
	public Reservation(Screening screening, Customer customer, int reservationID){
        this.screening = screening;
        this.customer = customer;
		this.reservationID = reservationID;
    }
	
    public Screening getScreening(){
        return screening;
    }
	
    public int getPhoneNumber(){
        return customer.getPhoneNumber();
    }
    
    public String getName(){
        return customer.getName();
    }
	
	public int getReservationID(){
		return reservationID;
	}
	
	public void nullifyReservationID(){
		reservationID = 0;
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
    public String printSeats(){
        String sts = "Seats: ";
        for(Seat i : seats.seats){
            sts = sts+i.getSeatNumber()+", ";
        }
        return sts;
    }
}
