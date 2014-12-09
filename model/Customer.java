package GPP_project.model;
/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    PhoneNumber phoneNumber;
    Name name;
    int reservationID;
    
    public Customer(String name, int phoneNumber){
        this.name = new Name(name);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        // Generate reservation ID based on database
        reservationID = 1;
    }
    
    public String getName(){
        return name.getName();
    }
    
    public int getPhoneNumber(){
        return phoneNumber.getPhoneNumber();
    }
    
    public int getReservationID(){
        return reservationID;
    }
    
}
