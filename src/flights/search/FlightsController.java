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
import javafx.scene.control.DatePicker;
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
    //private String[] defaultFilters = 
    
    private ArrayList<Flight> flightList;
    
    @FXML
    private TextField setDepartureLocation;
    @FXML
    private TextField setArrivalLocation;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        filters[0] = "%"; // Flight number
        filters[1] = "2019-06-06"; // Min date
        filters[2] = "2019-06-06"; // Max date
        filters[3] = "Reykjavik"; // Departure
        filters[4] = "Akureyri"; // Arrival
        filters[5] = "0"; // Min price
        filters[6] = "50000"; // Max price
        // TODO
        db = new DatabaseManager();
        //db.getDB();
        flightList = db.filterDB(filters);
         for(int i = 0; i < flightList.size(); i++) {
            System.out.println(flightList.get(i).toString());
        }
        System.out.println(flightList.size());
        */
    }
    /*
     * Get the filters set by the user from the UI, and put into the filters String array
     */
    private void getFilters(){
        
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
        
    }
    
    //call to sort the list by a new parameter
    public void sortBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // returns ArrayList<Flight>
    public ArrayList<Flight> returnFlightList() {
        return flightList;
    }
    
}
