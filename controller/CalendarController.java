/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPP_project.controller;

import GPP_project.model.Customer;
import GPP_project.model.Reservation;
import GPP_project.model.Screening;
import GPP_project.model.Seat;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alexander
 */
public class CalendarController {
    
    private ArrayList<Customer> ALLCustomers;
    private ArrayList<Reservation> ALLReservations;
    private ArrayList<ArrayList<Seat>> ALLSeatsRowCol;
    private ArrayList<ArrayList<Screening>> ALLScreenings;
    private ArrayList<Seat> ALLSeats;
    private Statement SQLStatement;
    
    private Label testLabel;
    private Text testText;
    private GridPane calendarGrid;
    private ScrollPane calendarInfoBox;
    
    
    
    public CalendarController(Statement SQLStatement, ArrayList<Seat> ALLSeats,
            ArrayList<Customer> ALLCustomers, ArrayList<Reservation> ALLReservations,
            ArrayList<ArrayList<Screening>> ALLScreenings) throws Exception{
        
        this.ALLSeats = ALLSeats;
        this.ALLCustomers = ALLCustomers;
        this.ALLReservations = ALLReservations;
        this.ALLScreenings = ALLScreenings;
        this.SQLStatement = SQLStatement;
    }
    
    public void FXMLLoader(GridPane calendarGrid, ScrollPane calendarInfoBox){
        this.calendarGrid = calendarGrid;
        this.calendarInfoBox = calendarInfoBox;
    }
    
    public void initialiseGrid(){
        Integer dayCounter = 24, dayLimit = 30;
        
        for(int week = 1; week < 7; week++){
            for(int weekDay = 0; weekDay < 7; weekDay++){
                if(dayCounter <= dayLimit){
                    Label label = new Label(dayCounter.toString());
                    Text text = new Text(dayCounter.toString());
                    label.setLabelFor(text);
                    label.setOnMouseClicked(evt -> dayClicked(getText(label)));
                    
                    label.setAlignment(Pos.CENTER);
                    label.setPrefHeight(71);
                    label.setPrefWidth(103);
                    
                    calendarGrid.add(label, weekDay, week);
                }else{
                    dayLimit++;
                    dayCounter = 1;
                    Label label = new Label(dayCounter.toString());
                    Text text = new Text(dayCounter.toString());
                    label.setLabelFor(text);
                    label.setOnMouseClicked(evt -> dayClicked(getText(label)));
                    
                    label.setAlignment(Pos.CENTER);
                    label.setPrefHeight(71);
                    label.setPrefWidth(103);
                    
                    calendarGrid.add(label, weekDay, week);
                }
                dayCounter++;
            }
        }
    }
    
    private String getText(Label label){
        if(label.getLabelFor() instanceof Text){
            Text tempText = (Text) label.getLabelFor();
            return tempText.getText();
        }
        return null;
    }
    
    private void dayClicked(String day){
        Scanner scanner;
        
        String toParse = day;
        scanner = new Scanner(toParse);
        if(scanner.hasNext()){
            Integer dayInt = scanner.nextInt();
            System.out.println(dayInt);
            
            getDayInfo(dayInt);
        }
    }
    
    private void getDayInfo(int day){
        ArrayList<Screening> screeningsThisDay;
        screeningsThisDay = ALLScreenings.get(day);
        

        
        for(int counter = 0; counter < screeningsThisDay.size(); counter++){
            
            VBox dayInfo = new VBox();
            dayInfo.setSpacing(6);
            dayInfo.setAlignment(Pos.TOP_CENTER);
            
            Screening thisScreening = screeningsThisDay.get(counter);
            
            Text movieText = new Text(thisScreening.getMovieTitle() + " Sal " + thisScreening.getTheaterNumber());
            dayInfo.getChildren().add(movieText);
            
            Text seatText = new Text("Reserverede sæder: " + thisScreening.getAmountReserved() + "/" + thisScreening.getAmountSeats());
        }
    }
}
