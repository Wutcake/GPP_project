package model;
/**
 * Write a description of class Movie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movie
{
    private String movieTitle;
    private String length;
    
    public Movie(String movieTitle, String length){
        this.movieTitle = movieTitle;
        this.length = length;
    }
    
    public String getMovieTitle(){
        return movieTitle;
    }

    public String getLength(){
        return length;
    }

    public String toString(){
        return "movieTitle '" + movieTitle + "'; length '" + length + "'";
    }

}
