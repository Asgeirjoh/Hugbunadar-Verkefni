/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private final String[] paymentTypes = {"Credit Card",
                                           "Paypal"};
    private final String[] seatTypes = {"Aisle Seat",
                                        "Window Seat"};

    private ObservableList<String> opaymentTypes;
    private ObservableList<String> oseatTypes;
    
    @FXML
    private AnchorPane nDialog;
    @FXML
    private TextField setName;
    @FXML
    private TextField setIdNumber;
    @FXML
    private ChoiceBox<String> setSeatType;
    @FXML
    private ChoiceBox<String> setPaymentType;

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
        d.setTitle("Book Flight");
        
        ButtonType cancel = new ButtonType("Cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        
        ButtonType confirmBooking = new ButtonType("Confirm Booking", 
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(confirmBooking);
        
        opaymentTypes = FXCollections.observableArrayList(paymentTypes);
        oseatTypes = FXCollections.observableArrayList(seatTypes);
        setPaymentType.setItems(FXCollections.observableArrayList(opaymentTypes));
        setSeatType.setItems(FXCollections.observableArrayList(oseatTypes));
       
        // Dialog birtur
        Optional<ButtonType> outcome = d.showAndWait();
    }
}
