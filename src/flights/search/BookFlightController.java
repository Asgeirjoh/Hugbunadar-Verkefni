/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private ObservableList<String> opaymentTypes;
    private ObservableList<String> oseatTypes;
    
    public String getName;
    public String getIdNumber;
    public String getSeatType;
    public String getPaymentType;
    
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
    public void confirmBookingShow(Flight flight) {
        DialogPane p = new DialogPane();
        nDialog.setVisible(true);
        // Innihald sett sem Pane sem fengið er úr Scene builder 
        p.setContent(nDialog);
        
        // Umgjörðin búin til - Dialog sem gefur niðurstöðu í Leikmenn klasann
        Dialog<ButtonType> d = new Dialog();
        
        // og innihaldið sett í umgjörðina 
        d.setDialogPane(p);
        // set Title of window
        d.setTitle("Book Flight");
        
        ButtonType cancel = new ButtonType("Cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        
        ButtonType confirmBooking = new ButtonType("Confirm Booking", 
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(confirmBooking);
        
        // call optionsInit()
        optionsInit(flight);
       
        // Dialog shown
        Optional<ButtonType> outcome = d.showAndWait();
        // get User information from UI
        getName = setName.getText();
        getIdNumber = setIdNumber.getText();
        getSeatType = setSeatType.getValue();
        getPaymentType = setPaymentType.getValue();
    }
    
    /**
     * initialize UI option elements
     */
    private void optionsInit(Flight flight) {
        // make sure you cant pick empty seats
        if (!(flight.getAisleSeats() > 0)) {
            String[] seatTypes = {"Window Seat"};
            oseatTypes = FXCollections.observableArrayList(seatTypes);
        }
        else if (!(flight.getWindowSeats() > 0)) {
            String[] seatTypes = {"Aisle Seat"};
            oseatTypes = FXCollections.observableArrayList(seatTypes);
        }
        else {
            String[] seatTypes = {"Window Seat",
                                  "Aisle Seat"};
            oseatTypes = FXCollections.observableArrayList(seatTypes);
            }
        // populate the options
        opaymentTypes = FXCollections.observableArrayList(paymentTypes);
        setPaymentType.setItems(FXCollections.observableArrayList(opaymentTypes));
        setSeatType.setItems(FXCollections.observableArrayList(oseatTypes));
        limitToNumbers(setIdNumber);
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
