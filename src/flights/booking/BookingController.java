/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.booking;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class BookingController implements Initializable {

    @FXML
    private AnchorPane nDialog;
    @FXML
    private TableView<?> results;
    @FXML
    private Button cancelBookingButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelBookingHandler(ActionEvent event) {
    }
    
}
