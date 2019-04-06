/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asgei
 */
public class BookFlightController implements Initializable {

    @FXML
    private AnchorPane nDialog;
    @FXML
    private TextField name;
    @FXML
    private TextField idNumber;
    @FXML
    private ChoiceBox<String> seatType;
    @FXML
    private ChoiceBox<String> paymentType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * confirmBookingShow opens BookFlight.fxml
     */
    public void confirmBookingShow() {
        DialogPane p = new DialogPane();
        nDialog.setVisible(true);
        // Innihald sett sem Pane sem fengið er úr Scene builder 
        p.setContent(nDialog);
        
        // Umgjörðin búin til - Dialog sem gefur niðurstöðu í Leikmenn klasann
        Dialog<ButtonType> d = new Dialog();
        
        // og innihaldið sett í umgjörðina 
        d.setDialogPane(p);
        // Haus, titill og mynd ef vill 
        d.setTitle("Skoða dagskrárlið");
        
        ButtonType cancel = new ButtonType("Cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        
        ButtonType confirmBooking = new ButtonType("Confirm Booking", 
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(confirmBooking);
       
        // Dialog birtur
        Optional<ButtonType> outcome = d.showAndWait();
    }
}
