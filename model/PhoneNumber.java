package model;

/**
 * Write a description of class model.PhoneNumber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhoneNumber
{
    private int phoneNumber;
    
    public PhoneNumber(int phoneNumber){
       this.phoneNumber = phoneNumber;
    }
    
    public int getPhoneNumber(){
        return phoneNumber;
    }

    public String toString(){
        return "phoneNumber int(" + phoneNumber + ")";
    }

}
