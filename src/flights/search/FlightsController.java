/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import flights.database.DatabaseManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;  
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;


/**
 *
 * @author asgeir
 */
public class FlightsController implements Initializable {
    
    private DatabaseManager db;
    
    //Stores filter values from UI that are used in Database call. Length to be decided.
    //TODO: decide default values, and set them in initialise EDIT: maybe create a defaultFilters array, store the defaults here, create object here.
    
    public String[] filters = new String[7];
    //Todo: make date filters use todays date and today + week/month
    private final String[] defaultFilters = {"%",
                                             LocalDate.now().toString(),
                                             LocalDate.now().plusDays(7).toString(),
                                             "Reykjavik", //
                                             "%",
                                             "0",
                                             "50000"};
    
    private ArrayList<Flight> flightList;
    
    private ArrayList<String> airportList;
    
    private ObservableList<Flight> oFlightList;
    
    @FXML
    private ChoiceBox<?> setDepartureLocation;
    @FXML
    private ChoiceBox<?> setArrivalLocation;
    @FXML
    private TextField setPriceMin;
    @FXML
    private TextField setPriceMax;
    @FXML
    private DatePicker setDateMin;
    @FXML
    private DatePicker setDateMax;
    @FXML
    private TextField setFlightNumber;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<Flight> results;
    @FXML
    private Button bookButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        db = new DatabaseManager();
        db.fetchAirports();
        
        
        filtersInit();
        resultsTableInit();
        searchHandler();
        showResults();
        
        //flightnumber;departure;arrival;depTime;arrTime;date;airline;aisle;window;price;
//        filters[0] = "%"; // Flight number
//        filters[1] = "2019-06-06"; // Min date
//        filters[2] = "2019-06-06"; // Max date
//        filters[3] = "Reykjavik"; // Departure
//        filters[4] = "Akureyri"; // Arrival
//        filters[5] = "0"; // Min price
//        filters[6] = "50000"; // Max price
    }
    
    /**
     * Initialise the results TableView UI element
     * Adds columns and instructions on what variable from Flight is 
     */
    private void resultsTableInit() {
        
        TableColumn flightNumber = new TableColumn("Flight Number");  //Make columns for the table
        flightNumber.setMinWidth(100);  //set width for columns
        flightNumber.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("flightNumber"));  //define the value of cells in the column
        
        TableColumn departureLocation = new TableColumn("From");
        departureLocation.setMinWidth(60);
        departureLocation.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("departureLocation"));

        TableColumn arrivalDestination = new TableColumn("To");
        arrivalDestination.setMinWidth(60);
        arrivalDestination.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("arrivalDestination"));

        TableColumn departureTime = new TableColumn("Takeoff");
        departureTime.setMinWidth(60);
        departureTime.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("departureTime"));

        TableColumn arrivalTime = new TableColumn("Landing");
        arrivalTime.setMinWidth(60);
        arrivalTime.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("arrivalTime"));

        TableColumn date = new TableColumn("date");
        date.setMinWidth(60);
        date.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("date"));

        TableColumn airline = new TableColumn("Airline");
        airline.setMinWidth(60);
        airline.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("airline"));



        TableColumn seating = new TableColumn("Available\nSeats");
        seating.setMinWidth(60);
        seating.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("seating"));

        TableColumn price = new TableColumn("Price");
        price.setMinWidth(80);
        price.setCellValueFactory(
                new PropertyValueFactory<Flight, String>("price"));

        //Put columns into results TableView
        results.getColumns().addAll(flightNumber, 
                                    departureLocation, 
                                    arrivalDestination, 
                                    departureTime, 
                                    arrivalTime, 
                                    date, 
                                    airline, 
                                    seating, 
                                    price
        );
        
    }
    
    /**
     * Initialises UI filter elements
     */
    private void filtersInit() {
       limitToNumbers(setPriceMin);
       limitToNumbers(setPriceMax);
       
       //TODO: initialise ChoiceBox for departure/arrival locaitions
       
       setDefaultFilters();
    }
    
    /**
     * Introduces the default filter values into the UI  elements
     */
    private void setDefaultFilters() {
        // setFlightNumber default null//    setDateMin;
        setDateMin.setValue(LocalDate.parse(defaultFilters[1]));

        // setDateMax;
        setDateMax.setValue(LocalDate.parse(defaultFilters[2]));

        //TODO: implement choicebox and set the correct values into them
        // filters[3] = "Reykjavik"; // Departure
        // filters[4] = "%"; // Arrival
        
        // setDepartureLocation;
        // ArrivalLocation;
        // setPriceMin;
        setPriceMin.setText(defaultFilters[5]);
        //    setPriceMax;
        setPriceMax.setText(defaultFilters[6]);
        //    setDateMin;
    }

    /**
     * Updates the filters array with the filters set by the user in the UI
     */
    private void updateFilters(){
        if (!setFlightNumber.getText().isEmpty()) {
            filters[0] = setFlightNumber.getText();
        } else {
            filters[0] = defaultFilters[0];
        }
//        filters[1], default:  LocalDate.now().toString(); // Min date
        filters[1] = setDateMin.getValue().toString();

//        filters[2], default:  LocalDate.now().plusDays(7).toString(); // Max date
        filters[2] = setDateMax.getValue().toString();

//        filters[3], default:  "Reykjavik"; // Departure
        if (setDepartureLocation.getValue() != null) {
            filters[3] = setDepartureLocation.getValue().toString();
        } else {
            filters[3] = defaultFilters[3];
        }
//        filters[4], default:  "%"; // Arrival
        if (setArrivalLocation.getValue() != null) {
            filters[4] = setArrivalLocation.getValue().toString();
        } else {
            filters[4] = defaultFilters[4];
        }

//        filters[5], default:  "0"; // Min price
        filters[5] = setPriceMin.getText();
//        filters[6], default:  "50000"; // Max price
        filters[6] = setPriceMax.getText();

        for (String filter : filters) {
            System.out.print(filter + ", ");
        }

    }
    /**
     * Search method updates the FlightList according to the filters specified, 
     * calls the databaseManager
     * @param filters String Array
     */
    public void search(String[] filters){
        db = new DatabaseManager();
        flightList = db.filterDB(filters);
    }
    
    // returns ArrayList<Flight>
    public ArrayList<Flight> returnFlightList() {
        return flightList;
    }
    
    private void showResults() {
        //Update the observable list
        oFlightList = FXCollections.observableArrayList(flightList);
        //Display the observable list in results TableView
        results.setItems(oFlightList);
        
    }
 /**
  * Handles the searchButton event.
  * updates the filters, performs a database search with the updated filters,
  * then displays the results in the results table.
  */
    @FXML
    private void searchHandler() {
        updateFilters();
        search(filters);
        showResults();
    }
    /**
     * Handles the bookButton event.
     * Takes current selected flight and advances the user to the booking interface
     * @param event 
     */

    @FXML
    private void bookHandler(ActionEvent event) {

    }
    
    /**
     * Prohibits any input that is not a number in text field that this is applied to.
     * @param numberField, a text field
     */
    private void limitToNumbers(TextField numberField) { 
        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    
}
