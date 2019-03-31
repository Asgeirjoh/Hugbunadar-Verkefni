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

/**
 *
 * @author asgei
 */
public class FlightsController implements Initializable {
    
    private DatabaseManager db;
    
    //Stores filter values from UI that are used in Database call. Length to be decided.
    //TODO: decide default values, and set them in initialise.
    private String[] filters = new String[10];
    
    private ArrayList<Flight> flightList;
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new DatabaseManager();
        //db.getDB();
        flightList = db.filterDB(filters);
         for(int i = 0; i < flightList.size(); i++) {
            System.out.println(flightList.get(i).toString());
        }
        System.out.println(flightList.size());
    }
    
    //Search method, uses filters from UI to call the database manager and get a list of flights to display in the UI
    public void search(){
        flightList = db.filterDB(filters);
        
    }
    
    //call to sort the list by a new parameter
    public void sortBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
