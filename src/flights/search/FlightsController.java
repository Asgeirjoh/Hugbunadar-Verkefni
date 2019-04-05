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
import java.util.ArrayList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

/**
 *
 * @author asgei
 */
public class FlightsController implements Initializable {
    
    private DatabaseManager db;
    
    //Stores filter values from UI that are used in Database call. Length to be decided.
    //TODO: decide default values, and set them in initialise EDIT: maybe create a defaultFilters array, store the defaults here, create object here.
    
    public String[] filters = new String[7];
    private String[] defaultFilters = {"%", "2019-06-06", "2019-06-06", "Reykjavik", "Akureyri", "0", "50000"};
    
    private ArrayList<Flight> flightList;
    
    @FXML
    private ChoiceBox<?> setDepartureLocation;
    @FXML
    private ChoiceBox<?> setArrivalLocation;
    @FXML
    private TextField priceLowerBound;
    @FXML
    private TextField priceUpperBound;
    @FXML
    private DatePicker dateLower;
    @FXML
    private DatePicker dateUpper;
    @FXML
    private Button searchButton;
    @FXML
    private TextField priceLowerBound1;
    @FXML
    private TableView<Flight> results;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resultsTableInit();
        
        //flightnumber;departure;arrival;depTime;arrTime;date;airline;aisle;window;price;
        
        
        
//        filters[0] = "%"; // Flight number
//        filters[1] = "2019-06-06"; // Min date
//        filters[2] = "2019-06-06"; // Max date
//        filters[3] = "Reykjavik"; // Departure
//        filters[4] = "Akureyri"; // Arrival
//        filters[5] = "0"; // Min price
//        filters[6] = "50000"; // Max price
//        // TODO
//        db = new DatabaseManager();
//        //db.getDB();
//        flightList = db.filterDB(filters);
//         for(int i = 0; i < flightList.size(); i++) {
//            System.out.println(flightList.get(i).toString());
//        }
//        System.out.println(flightList.size());
//        
    }
    private void resultsTableInit() {
        
        //make results columns
      

        

        TableColumn flightNumber = new TableColumn("Flight Number");
        flightNumber.setMinWidth(60);
        flightNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
        TableColumn departureLocation = new TableColumn("From");
        departureLocation.setMinWidth(60);
        departureLocation.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureLocation"));
        TableColumn arrivalDestination = new TableColumn("To");
        arrivalDestination.setMinWidth(60);
        arrivalDestination.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalDestination"));
        TableColumn departureTime = new TableColumn("Takeoff");
        departureTime.setMinWidth(60);
        departureTime.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureTime"));
        TableColumn arrivalTime = new TableColumn("Landing");
        arrivalTime.setMinWidth(60);
        arrivalTime.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
        TableColumn date = new TableColumn("date");
        date.setMinWidth(60);
        date.setCellValueFactory(new PropertyValueFactory<Flight, String>("date"));
        TableColumn airline = new TableColumn("Airline");
        airline.setMinWidth(60);
        airline.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline"));
        TableColumn seating = new TableColumn("Available Seats");
        seating.setMinWidth(60);
        seating.setCellValueFactory(new PropertyValueFactory<Flight, String>("seating"));
        TableColumn price = new TableColumn("Price");
        price.setMinWidth(60);
        price.setCellValueFactory(new PropertyValueFactory<Flight, String>("price"));
        
        results.setItems(flightList);
    //add them to the table
        results.getColumns().addAll(flightNumber, departureLocation, arrivalDestination, departureTime, arrivalTime, date, airline, seating, price);
        
        
        
    }
    
    /*
     * Get the filters set by the user from the UI, and put into the filters String array
     */
    private void getUIFilters(){
        
    }
    
    //Search method, uses filters from UI to call the database manager and get a list of flights to display in the UI
    public void search(String[] filters){
        /*
        filters[0] = "%"; // Flight number
        filters[1] = "2019-06-06"; // Min date
        filters[2] = "2019-06-06"; // Max date
        filters[3] = "Reykjavik"; // Departure
        filters[4] = "Akureyri"; // Arrival
        filters[5] = "0"; // Min price
        filters[6] = "50000"; // Max price
        */
        db = new DatabaseManager();
        flightList = db.filterDB(filters);
        showResults();
                
    }
    
    //call to sort the list by a new parameter
    public void sortBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // returns ArrayList<Flight>
    public ArrayList<Flight> returnFlightList() {
        return flightList;
    }
    
    private void showResults() {
        //clear items from table
        results.getItems().clear();
        
    }

    @FXML
    private void searchHandler(ActionEvent event) {
        getUIFilters();
        search(filters);
    }

    
}
