/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author asgei
 */
public class FlightsController implements Initializable {
    
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
    }
    
    //Search method, uses filters from UI to call the database manager and get a list of flights to display in the UI
    public void search(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //call to sort the list by a new parameter
    public void sortBy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
