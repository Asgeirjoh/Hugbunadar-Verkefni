/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.booking;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class BookingController implements Initializable {

    @FXML
    private AnchorPane nDialog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * confirmBookingShow opens booking.fxml
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
        ButtonType haettaVid = new ButtonType("Hætta við", 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(haettaVid);
        // Dialog birtur - svarið ekki notað 
        d.showAndWait();
    }
}
