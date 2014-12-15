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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
    
    private ArrayList<Button> currentButtons = new ArrayList<>();
    
    private GridPane calendarGrid;
    private VBox calendarInfoBox;
    private int selectedScreeningID = 0, currentDay = 1;
    
    public CalendarController(Statement SQLStatement, ArrayList<Seat> ALLSeats,
            ArrayList<Customer> ALLCustomers, ArrayList<Reservation> ALLReservations,
            ArrayList<ArrayList<Screening>> ALLScreenings) throws Exception{
        
        this.ALLSeats = ALLSeats;
        this.ALLCustomers = ALLCustomers;
        this.ALLReservations = ALLReservations;
        this.ALLScreenings = ALLScreenings;
        this.SQLStatement = SQLStatement;
    }
    
    public void FXMLLoader(GridPane calendarGrid, VBox calendarInfoBox){
        this.calendarGrid = calendarGrid;
        this.calendarInfoBox = calendarInfoBox;
    }
    
    public void initialiseGrid(){
        Integer dayCounter = 24, dayLimit = 30;
        boolean flag = true;
        
        // This long bit of code is only neccesary because we don't want to
        // be able to click days where we don't have screenings. This bit could
        // be significantly shorter and more understandable if the system
        // was set-up to deal with a calendar year based.
        
        for(int week = 1; week < 7; week++){
            for(int weekDay = 0; weekDay < 7; weekDay++){
                if(flag){
                    if(dayCounter <= dayLimit){
                        Label label = new Label(dayCounter.toString());
                        Text text = new Text(dayCounter.toString());
                        label.setLabelFor(text);

                        label.setAlignment(Pos.CENTER);
                        label.setPrefHeight(71);
                        label.setPrefWidth(103);

                        calendarGrid.add(label, weekDay, week);
                        dayCounter++;
                    }else if(dayCounter > dayLimit){
                        dayLimit++;
                        flag = !flag;
                        dayCounter = 1;
                        weekDay--;
                    }else{
                        
                        Label label = new Label(dayCounter.toString());
                        Text text = new Text(dayCounter.toString());
                        label.setLabelFor(text);

                        label.setAlignment(Pos.CENTER);
                        label.setPrefHeight(71);
                        label.setPrefWidth(103);

                        calendarGrid.add(label, weekDay, week);
                        
                        dayCounter++;
                    }
                }else{
                    if(dayCounter < 24){
                        if(dayCounter <= dayLimit){
                            Label label = new Label(dayCounter.toString());
                            Text text = new Text(dayCounter.toString());
                            label.setLabelFor(text);
                            label.setOnMouseClicked(evt -> dayClicked(getText(label)));

                            label.setAlignment(Pos.CENTER);
                            label.setPrefHeight(71);
                            label.setPrefWidth(103);

                            calendarGrid.add(label, weekDay, week);
                            dayCounter++;
                        }else if(dayCounter > dayLimit){
                            dayLimit++;
                            flag = !flag;
                            dayCounter = 1;
                            weekDay--;
                        }else{
                            Label label = new Label(dayCounter.toString());
                            Text text = new Text(dayCounter.toString());
                            label.setLabelFor(text);
                            label.setOnMouseClicked(evt -> dayClicked(getText(label)));

                            label.setAlignment(Pos.CENTER);
                            label.setPrefHeight(71);
                            label.setPrefWidth(103);

                            calendarGrid.add(label, weekDay, week);
                            dayCounter++;
                        }
                    }else{
                            if(dayCounter <= dayLimit){
                            Label label = new Label(dayCounter.toString());
                            Text text = new Text(dayCounter.toString());
                            label.setLabelFor(text);

                            label.setAlignment(Pos.CENTER);
                            label.setPrefHeight(71);
                            label.setPrefWidth(103);

                            calendarGrid.add(label, weekDay, week);
                            dayCounter++;
                        }else if(dayCounter > dayLimit){
                            dayLimit++;
                            flag = !flag;
                            dayCounter = 1;
                            weekDay--;
                        }else{

                            Label label = new Label(dayCounter.toString());
                            Text text = new Text(dayCounter.toString());
                            label.setLabelFor(text);

                            label.setAlignment(Pos.CENTER);
                            label.setPrefHeight(71);
                            label.setPrefWidth(103);

                            calendarGrid.add(label, weekDay, week);

                            dayCounter++;
                        }
                    }
                }
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
            currentDay = dayInt;
            getDayInfo(dayInt);
        }
    }
    
    public void update(){
        getDayInfo(currentDay);
    }
    
    private void getDayInfo(int day){
        calendarInfoBox.getChildren().clear();
        currentButtons.clear();
        
        ArrayList<Screening> screeningsThisDay;
        screeningsThisDay = ALLScreenings.get(day);
        
        for(int counter = 0; counter < screeningsThisDay.size(); counter++){
            Button thisButton = new Button();
            thisButton.setPrefWidth(303);
            
            thisButton.setAlignment(Pos.TOP_LEFT);
            thisButton.setPadding(new Insets(5,5,5,5));
            
            Screening thisScreening = screeningsThisDay.get(counter);
            
            String movieText = thisScreening.getMovieTitle() + " Sal " + thisScreening.getTheaterNumber();
            
            String seatText = "Reserverede sæder: " + thisScreening.getAmountReserved() + "/" + thisScreening.getAmountSeats();
            
            String timeText = "Spilletid: " + thisScreening.getMovieLength() + "\n" + "Filmen starter: " + thisScreening.getTime();
                    
            thisButton.setText(movieText + "\n \n" + seatText + "\n \n" + timeText);
            thisButton.setOnAction(evt -> 
                calendarButtonClicked(thisScreening.getScreeningID()));
            
            calendarInfoBox.getChildren().add(thisButton);
            currentButtons.add(thisButton);
        }
    }
    
    private void calendarButtonClicked(int screeningID){
        selectedScreeningID = screeningID;
    }
    
    public int getSelectedScreening(){
        return selectedScreeningID;
    }
}
