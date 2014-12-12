/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPP_project.controller;

import GPP_project.model.Customer;
import GPP_project.model.Reservation;
import GPP_project.model.Seat;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Alexander
 */
public class CalenderController {
    
    private ArrayList<Customer> ALLCustomers;
    private ArrayList<Reservation> ALLReservations;
    private ArrayList<ArrayList<Seat>> ALLSeatsRowCol;
    private ArrayList<Seat> ALLSeats;
    private Statement SQLStatement;
    
    private Label testLabel;
    private Text testText;
    private GridPane calenderGrid;
    
    
    
    public CalenderController(Statement SQLStatement,  
            ArrayList<Seat> ALLSeats, ArrayList<Customer> ALLCustomers, ArrayList<Reservation> ALLReservations) throws Exception{
        
        this.ALLSeats = ALLSeats;
        this.ALLCustomers = ALLCustomers;
        this.ALLReservations = ALLReservations;
        this.SQLStatement = SQLStatement;
    }
    
    public void FXMLLoader(GridPane calenderGrid){
        this.testLabel = testLabel;
        
        testText = new Text("Day 1");
        
        testLabel.setLabelFor(testText);
        System.out.println(getText(testLabel));
    }
    
    private void initialiseGrid(){
        Integer dayCounter = 24, dayLimit = 30;
        
        for(int weekDay = 0; weekDay < 7; weekDay++){
            for(int week = 1; week < 7; week++){
                if(dayCounter <= dayLimit){
                    Label label = new Label(dayCounter.toString());
                    Text text = new Text(dayCounter.toString());
                    label.setLabelFor(text);
                    label.setOnMouseClicked(evt -> dayClicked(getText(label)));
                    
                    calenderGrid.add(label, week, weekDay);
                }else{
                    dayLimit++;
                    dayCounter = 1;
                    Label label = new Label(dayCounter.toString());
                    Text text = new Text(dayCounter.toString());
                    label.setLabelFor(text);
                    label.setOnMouseClicked(evt -> dayClicked(getText(label)));
                    
                    calenderGrid.add(label, week, weekDay);
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
            
            
        }
    }
    
}
