/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class BookFlightDialogController implements Initializable {

    @FXML
    private AnchorPane nDialog;
    @FXML
    private TextField setName;
    @FXML
    private TextField setIdNumber;
    @FXML
    private ChoiceBox<?> setSeatType;
    @FXML
    private ChoiceBox<?> setPaymentType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
