/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class FlightsControllerTest {
    private FlightsController fc1,fc2,fc3,fc4,fc5;
    private String[] filters1 = new String[7];
    private ArrayList<Flight> flightList;
    
    public FlightsControllerTest() {
        filters1[0] = "%"; // Flight number
        filters1[1] = "2019-06-06"; // Min date
        filters1[2] = "2019-06-06"; // Max date
        filters1[3] = "Reykjavik"; // Departure
        filters1[4] = "Akureyri"; // Arrival
        filters1[5] = "0"; // Min price
        filters1[6] = "50000"; // Max price
        fc1.filters = filters1;
        assertEquals("NY112",fc1.returnFlightList());
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        fc1 = new FlightsController();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class FlightsController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        FlightsController instance = new FlightsController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
