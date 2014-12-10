package GPP_project.model;

/**
 * Write a description of class model.Name here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Name
{
    private String name;
    
    public Name(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public String toString(){
        return "name '" + name + "'";
    }
}
