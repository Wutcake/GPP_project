package GPP_project.controller;

import GPP_project.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MasterController {
    // TheaterController
    @FXML
    private GridPane theaterGrid;
    
    @FXML
    private Text movieField;
    
    @FXML
    private Text infoField;
    
    @FXML
    private Text availableSeatsText;
    
    @FXML
    private Text totalSeatsText;
    
    @FXML
    private Text amountSelectedSeatsText;
    
    @FXML
    private TextField nameInput;
     
    @FXML
    private TextField phoneNumberInput;
    
    TheaterController theaterController;
    
    //Reservations Controller
    @FXML 
    private VBox buttonPane;
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Text nameField;
    
    @FXML
    private Text phoneField;
    
    @FXML
    private Text titleField;
    
    @FXML
    private Text theaterField;
    
    @FXML
    private Text seatField;

    @FXML
    private Button theaterChangeButton;
    
    ReservationController reservationController;
    
    // Calendar Controller
    @FXML
    private GridPane calendarGrid;
    
    @FXML
    private VBox calendarInfoBox;
    
    @FXML
    private Button goToTheaterButton;
    
    CalendarController calendarController;
    
    // Global variables
    private ArrayList<Customer> ALLCustomers = new ArrayList<Customer>();
    private ArrayList<Movie> ALLMovies = new ArrayList<Movie>();
    private ArrayList<Reservation> ALLReservations = new ArrayList<Reservation>();
    private ArrayList<Seat> ALLSeats = new ArrayList<Seat>();
    private ArrayList<ArrayList<ArrayList<Seat>>> ALLSeatsTheaterRowCol = new ArrayList<ArrayList<ArrayList<Seat>>>();
    private ArrayList<Screening> ALLScreenings = new ArrayList<Screening>();
    private ArrayList<ArrayList<Screening>> ALLScreeningsDay = new ArrayList<ArrayList<Screening>>();

    // MySQL Stuff
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String database_name = "Widere_Biograf";
    static final String DB_URL = "jdbc:mysql://mysql.itu.dk/" + database_name;

    static final String USER = "Henriette_Hess";
    static final String PASS = "Henning_Torsten";

    private Connection connection = null;
    private Statement statement = null;
    
    public MasterController() throws Exception
    {
        
        arrayListInitialization();
        theaterController = new TheaterController(statement,  
            ALLSeats,  ALLCustomers,  ALLReservations);
        
       
        reservationController = new ReservationController(ALLCustomers, ALLReservations, ALLScreenings, 
             statement);
        
        calendarController = new CalendarController(statement,
            ALLSeats,  ALLCustomers,  ALLReservations, ALLScreeningsDay);
        
        
    }
    
    @FXML
    private void initialize() throws Exception{
    }
    
    @FXML
    private void reserveButton() throws Exception{
        theaterController.reserveButton();
        reservationListInitialization();
        customerListInitialization();
    }
    
    @FXML
    private void search() throws Exception{
        reservationController.search();
    }
    
    @FXML
    private void dateClicked(){
        System.out.println("clicked");
    }
    
    @FXML
    private void deleteReservation() throws Exception{
        reservationController.deleteReservation();
        reservationListInitialization();        
    }

    @FXML
    private void showInTheaterView() throws Exception{

    }
    
    @FXML
    private void goToTheater() throws Exception{
        int screeningID = calendarController.getSelectedScreening();
        int theaterID = ALLScreenings.get(screeningID).getTheaterNumber();
        
        theaterController.setTheater(screeningID, ALLScreenings.get(screeningID),
                ALLSeatsTheaterRowCol.get(theaterID));
    }
    
    public void test() throws Exception{
        
        theaterController.FXMLLoader(theaterGrid, movieField, infoField,
                availableSeatsText, totalSeatsText,amountSelectedSeatsText,
                nameInput, phoneNumberInput);
        
        reservationController.FXMLLoader(buttonPane, searchBar, searchButton,
                deleteButton, nameField, titleField, theaterField,
                seatField, phoneField);
        
        theaterController.setTheater(1, ALLScreenings.get(1),
                ALLSeatsTheaterRowCol.get(1));
        
        calendarController.FXMLLoader(calendarGrid, calendarInfoBox);
        
        calendarController.initialiseGrid();
    }
    
    public void print() {
        for(int cnt = 0; cnt < ALLCustomers.size(); cnt++) {
            System.out.println(ALLCustomers.get(cnt).toString());
        }

        System.out.println("");

        for(int cnt = 0; cnt < ALLMovies.size(); cnt++) {
            System.out.println(ALLMovies.get(cnt).toString());
        }

        for(int cnt = 0; cnt < ALLScreenings.size(); cnt++) {
            System.out.println(ALLScreenings.get(cnt).toString());
        }

        for(int cnt = 0; cnt < ALLSeats.size(); cnt++) {
            System.out.println(ALLSeats.get(cnt).toString());
        }

        for(int cnt = 0; cnt < ALLReservations.size(); cnt++) {
            System.out.println(ALLReservations.get(cnt).toString());
        }

        for(int theater_cnt = 0; theater_cnt < ALLSeatsTheaterRowCol.size(); theater_cnt++) {
            for(int row_cnt = 0; row_cnt < ALLSeatsTheaterRowCol.get(theater_cnt).size(); row_cnt++) {
                for(int col_cnt = 0; col_cnt < ALLSeatsTheaterRowCol.get(theater_cnt).get(row_cnt).size(); col_cnt++) {
                    System.out.println("Theater" + theater_cnt + "; Row " + row_cnt + "; Col " + col_cnt + "; " + ALLSeatsTheaterRowCol.get(theater_cnt).get(row_cnt).get(col_cnt));
                }
            }
        }
    }

    private void arrayListInitialization() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            // Håndtering af individuelle tables
            customerListInitialization();
            movieListInitialization();
            seatListInitialization();
            screeningListInitialization();
            reservationListInitialization();
            //seatReservationListInitialization(statement);

        } catch(Exception e) {
            e.printStackTrace(); // handle errors
        }
    }

    private void customerListInitialization() throws Exception{
        String query = "SELECT * FROM Customers";

        ResultSet rs = statement.executeQuery(query);

        ALLCustomers.add(new Customer("John Doe", 0, 0));

        while(rs.next()){
            String name = rs.getString("Name");
            int phoneNumber = rs.getInt("PhoneNumber");
            int customerID = rs.getInt("CustomerID");

            ALLCustomers.add(new Customer(name, phoneNumber, customerID));
        }
        rs.close();
    }

    private void movieListInitialization() throws Exception{
        String query = "SELECT * FROM Movies";

        ResultSet rs = statement.executeQuery(query);

        ALLMovies.add(new Movie("Arrival of a Train at La Ciotat", "00:01"));

        while(rs.next()){
            String movieTitle = rs.getString("MovieTitle");
            int lengthMinutes = rs.getInt("LengthMinutes");
            String length = (lengthMinutes/60) + ":" + (lengthMinutes%60);

            ALLMovies.add(new Movie(movieTitle, length));
        }
        rs.close();
    }

    private void seatListInitialization() throws Exception{
        String query = "SELECT * FROM Seats";

        ResultSet rs = statement.executeQuery(query);

        ALLSeats.add(new Seat(0,0));
        ALLSeatsTheaterRowCol.add(new ArrayList<ArrayList<Seat>>());
        ALLSeatsTheaterRowCol.get(0).add(new ArrayList<Seat>());
        ALLSeatsTheaterRowCol.get(0).get(0).add(ALLSeats.get(0));

        int totalTheaters = 0;
        int totalRows = 0;
        while(rs.next()){
            int seatID = rs.getInt("SeatID");
            int number = rs.getInt("Number");

            ALLSeats.add(new Seat(number, seatID));

            int theater = rs.getInt("Theater");
            int row = rs.getInt("Row");

            if(theater > totalTheaters){
                ALLSeatsTheaterRowCol.add(new ArrayList<ArrayList<Seat>>());
                ALLSeatsTheaterRowCol.get(theater).add(new ArrayList<Seat>());
                totalTheaters = theater;
                totalRows = 0;
            }

            if(row > totalRows){
                ALLSeatsTheaterRowCol.get(theater).add(new ArrayList<Seat>());
                ALLSeatsTheaterRowCol.get(theater).get(row).add(ALLSeats.get(0));
                totalRows = row;
            }

            ALLSeatsTheaterRowCol.get(theater).get(row).add(ALLSeats.get(seatID));
        }
        rs.close();
    }

    private void screeningListInitialization() throws Exception{
        String query = "SELECT * FROM Screenings";

        ResultSet rs = statement.executeQuery(query);

        ALLScreenings.add(new Screening(ALLMovies.get(0), 0, "00:00", "00-12-00", 0, 0));
        ALLScreeningsDay.add(new ArrayList<Screening>());
        ALLScreeningsDay.add(new ArrayList<Screening>());
        ALLScreeningsDay.get(0).add(ALLScreenings.get(0));

        String date = "";
        int dayCounter = 1;
        while(rs.next()){
            int screeningID = rs.getInt("ScreeningID");
            int movieID = rs.getInt("MovieID");
            String newDate = rs.getString("Date");
            String time = rs.getString("Time");
            int theaterID = rs.getInt("Theater");
            int amountSeatsReserved = rs.getInt("SeatsReserved");

            ALLScreenings.add(new Screening(ALLMovies.get(movieID), theaterID, time, newDate, ALLSeatsTheaterRowCol.get(theaterID).size(), screeningID));
            ALLScreenings.get(screeningID).setAmountReserved(amountSeatsReserved);

            if(!newDate.equals(date)){
                ALLScreeningsDay.add(new ArrayList<Screening>());
                dayCounter++;
            }
            ALLScreeningsDay.get(dayCounter).add(ALLScreenings.get(screeningID));
            date = newDate;
        }
        rs.close();
    }

    private void reservationListInitialization() throws Exception{
        ALLReservations.clear();
        String query = "SELECT * FROM Reservations";

        ResultSet rs = statement.executeQuery(query);

        ALLReservations.add(new Reservation(ALLScreenings.get(0), ALLCustomers.get(0)));
        ALLReservations.get(0).reserveNewSeat(ALLSeats.get(0));

        int reservationID = 0;
        while(rs.next()){
            int customerID = rs.getInt("CustomerID");
            int newReservationID = rs.getInt("ReservationID");
            if(newReservationID != reservationID) {
                int screeningID = rs.getInt("ScreeningID");

                ALLReservations.add(new Reservation(ALLScreenings.get(screeningID), ALLCustomers.get(customerID), newReservationID));
            }
            reservationID = newReservationID;

            int seatID = rs.getInt("SeatID");

            ALLReservations.get(reservationID).reserveNewSeat(ALLSeats.get(seatID));
        }
        rs.close();
    }
    

}
