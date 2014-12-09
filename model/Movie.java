package GPP_project.model;
/**
 * Write a description of class Movie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movie
{
    private String movieTitle;
    private int movieID;
    
    public Movie(String movieTitle, int movieID){
        this.movieTitle = movieTitle;
        this.movieID = movieID;
    }
    
    public String getMovieTitle(){
        return movieTitle;
    }
    
    public int getMovieID(){
        return movieID;
    }

}
