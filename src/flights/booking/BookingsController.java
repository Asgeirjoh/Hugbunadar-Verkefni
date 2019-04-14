/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.booking;

import flights.database.DatabaseManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class BookingsController implements Initializable {

    @FXML
    private AnchorPane nDialog;
    @FXML
    private TableView<Booking> results;
    private DatabaseManager db;
    private ArrayList<Booking> bookingList;
    private ObservableList<Booking> oBookingList;
    @FXML
    private Button cancelBookingButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /**
     * Opens Booking.fxml
     */
    public void bookingShow() {
        DialogPane p = new DialogPane();
        nDialog.setVisible(true);
        // Content set as Pane from Scene builder 
        p.setContent(nDialog);
        
        // Frame created
        Dialog<ButtonType> d = new Dialog();
        
        // Content put in frame 
        d.setDialogPane(p);
        // set Title of window
        d.setTitle("Book Flight");
        // set cancel button
        ButtonType cancel = new ButtonType("Cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        // set ok button
        ButtonType confirmBooking = new ButtonType("Ok", 
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(confirmBooking);
        resultsTableInit();
        showResults();
        d.showAndWait();
        // clear columns after closing window
        results.getColumns().clear();
    }
    
   /**
    * populate table with booking elements from bookedFlights
    */
    private void resultsTableInit() {
        TableColumn name = new TableColumn("Name");  //Make columns for the table
        name.setMinWidth(100);  //set width for columns
        name.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("name"));  //define the value of cells in the column
        
        TableColumn idNumber = new TableColumn("Id Number");  //Make columns for the table
        idNumber.setMinWidth(100);  //set width for columns
        idNumber.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("idNumber"));  //define the value of cells in the column
        
        TableColumn paymentType = new TableColumn("Payment Type");  //Make columns for the table
        paymentType.setMinWidth(100);  //set width for columns
        paymentType.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("paymentType"));  //define the value of cells in the column
        
        TableColumn flightNumber = new TableColumn("Flight Number");  //Make columns for the table
        flightNumber.setMinWidth(100);  //set width for columns
        flightNumber.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("flightNumber"));  //define the value of cells in the column
        
        TableColumn departureLocation = new TableColumn("From");
        departureLocation.setMinWidth(60);
        departureLocation.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("departureLocation"));

        TableColumn arrivalDestination = new TableColumn("To");
        arrivalDestination.setMinWidth(60);
        arrivalDestination.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("arrivalDestination"));

        TableColumn departureTime = new TableColumn("Takeoff");
        departureTime.setMinWidth(60);
        departureTime.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("departureTime"));

        TableColumn arrivalTime = new TableColumn("Landing");
        arrivalTime.setMinWidth(60);
        arrivalTime.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("arrivalTime"));

        TableColumn date = new TableColumn("date");
        date.setMinWidth(60);
        date.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("date"));

        TableColumn airline = new TableColumn("Airline");
        airline.setMinWidth(60);
        airline.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("airline"));



        TableColumn typeofSeat = new TableColumn("Seat Type");
        typeofSeat.setMinWidth(60);
        typeofSeat.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("typeofSeat"));

        TableColumn price = new TableColumn("Price");
        price.setMinWidth(80);
        price.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("price"));

        //Put columns into results TableView
        results.getColumns().addAll(name, 
                                    idNumber, 
                                    paymentType, 
                                    flightNumber, 
                                    departureLocation, 
                                    arrivalDestination, 
                                    departureTime, 
                                    arrivalTime, 
                                    date, 
                                    airline, 
                                    typeofSeat, 
                                    price
        );
    }
    
    /**
     * Refreshes the observable list the table views.
     */
    private void showResults() {
        db = new DatabaseManager();
        bookingList = db.getBookings();
        //Update the observable list
        oBookingList = FXCollections.observableArrayList(bookingList);
        //Display the observable list in results TableView
        results.setItems(oBookingList);      
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void cancelBookingHandler(ActionEvent event) {
        db = new DatabaseManager();
        if (results.getSelectionModel().getSelectedItem() == null) {
            System.out.println("No booking Selected");
        }
        else {
            db.cancelBooking(results.getSelectionModel().getSelectedItem());
            db.incrementSeats(results.getSelectionModel().getSelectedItem());
            showResults();
        }
    }
}
