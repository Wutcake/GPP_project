package GPP_project.model;

/**
 * Write a description of class model.Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    PhoneNumber phoneNumber;
    Name name;
    int customerID;
    
    public Customer(String name, int phoneNumber, int customerID){
        this.name = new Name(name);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        // Generate reservation ID based on database
        this.customerID = customerID;
    }
    
    public String getName(){
        return name.getName();
    }
    
    public int getPhoneNumber(){
        return phoneNumber.getPhoneNumber();
    }
    
    public int getCustomerID(){
        return customerID;
    }

    public String toString(){
        return name + "; " + phoneNumber;
    }
    
}
