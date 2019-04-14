/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.booking;

import flights.booking.BookingsController;
import flights.booking.Booking;
import flights.database.DatabaseManager;
import flights.search.Flight;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class BookFlightDialogController implements Initializable {

    private final String[] paymentTypes = {"Credit Card",
                                           "Paypal"};

    private ObservableList<String> opaymentTypes;
    private ObservableList<String> oseatTypes;
    
    public String getName;
    public String getIdNumber;
    public String getSeatType;
    public String getPaymentType;
    
    private DatabaseManager db;
      
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
     * @param flight
     */
    public void confirmBookingShow(Flight flight) {
        DialogPane p = new DialogPane();
        nDialog.setVisible(true);
        // Content set as Pane from Scene builder 
        p.setContent(nDialog);
        
        // Frame created
        Dialog<ButtonType> d = new Dialog();
        
        // Content put in frame 
        d.setDialogPane(p);
        // set Title of window
        d.setTitle("Book Flight");
        // set cancel button
        ButtonType cancel = new ButtonType("Cancel", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(cancel);
        // set confirm button
        ButtonType confirmBooking = new ButtonType("Confirm Booking", 
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(confirmBooking);
        
        final Node stadfestingHnappur = p.lookupButton(confirmBooking);
        stadfestingHnappur.disableProperty()
                .bind(setIdNumber.textProperty().isEmpty()
                        .or(setName.textProperty().isEmpty())
                );
        
        // call optionsInit()
        optionsInit(flight);
       
        // Dialog shown
        Optional<ButtonType> outcome = d.showAndWait();
        if (outcome.isPresent() && outcome.get()      
                .getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            
            // get User information from UI
            getName = setName.getText();
            getIdNumber = setIdNumber.getText();
            getSeatType = setSeatType.getValue();
            getPaymentType = setPaymentType.getValue();
            bookFlight(new Booking(
                                             getName, 
                                             getIdNumber, 
                                             getPaymentType, 
                                             flight.getFlightNumber(), 
                                             flight.getDepartureLocation(), 
                                             flight.getArrivalDestination(), 
                                             flight.getDepartureTime().toString(), 
                                             flight.getArrivalTime().toString(), 
                                             flight.getDate().toString(), 
                                             flight.getAirline(), 
                                             getSeatType, 
                                             String.valueOf(flight.getPrice())
                )
            );
            // reserve the seat by decrementing availeble seats in flights 
            // of type getSeatType
            db.decrementSeats(flight, getSeatType);
        }
    }
    
    /**
     * 
     * @param booking 
     */
    private void bookFlight(Booking booking) {
        db = new DatabaseManager();
        // puts booking into bookedFlights database table
        db.setBooking(booking);
        System.out.println(booking);
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
            String[] seatTypes = {"Aisle Seat",
                                  "Window Seat"};
            oseatTypes = FXCollections.observableArrayList(seatTypes);
            }
        // populate the options
        opaymentTypes = FXCollections.observableArrayList(paymentTypes);
        setPaymentType.setItems(FXCollections.observableArrayList(opaymentTypes));
        setPaymentType.getSelectionModel().selectFirst();
        
        setSeatType.setItems(FXCollections.observableArrayList(oseatTypes));
        setSeatType.getSelectionModel().selectFirst();
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
