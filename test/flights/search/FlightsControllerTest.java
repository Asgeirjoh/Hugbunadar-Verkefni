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
 * @author Ásgeir Jóhannes Tómasson, Háskóli Íslands, ajt2@hi.is
 */
public class FlightsControllerTest {
    private FlightsController fc;
    private String[] filters1 = new String[7];
    private ArrayList<Flight> flightList;
    public FlightsControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        fc = new FlightsController();
    }
    
    @AfterEach
    public void tearDown() {
        fc = null;
    }
    /**
     * Test of search method, of class FlightsController.
     */
    @Test
    public void testSearch() {
        Flight flight1 = new Flight("NY112","Reykjavik","Akureyri","07:10:00","07:55:00","2019-06-06",
                "Air Iceland Connect","7","10","27382"); // Create expected result from Search Query
        filters1[0] = "NY112"; // Flight number
        filters1[1] = "Reykjavik"; // Departure
        filters1[2] = "Akureyri"; // Arrival
        filters1[3] = "2019-06-06"; // Min date
        filters1[4] = "2019-06-06"; // Max date
        filters1[5] = "0"; // Min price
        filters1[6] = "50000"; // Max price
        fc.search(filters1); // Call search from FlightsController with the filters above
        flightList = fc.returnFlightList(); // Fetch List of Flights that derived from search()
        assertEquals(flight1.toString(), flightList.get(0).toString());
    }
    /**
     * Test of search method, of class FlightsController and getFlightNumber() method, of class Flight
     * to see if you can skip entering flight number, departure location and arrival destination but
     * still get flights on the correct date.
     */
    @Test
    public void testSearchDate() {
        filters1[0] = "%"; // Flight number
        filters1[1] = "%"; // Departure
        filters1[2] = "%"; // Arrival
        filters1[3] = "2019-06-06"; // Min date
        filters1[4] = "2019-06-06"; // Max date
        filters1[5] = "0"; // Min price
        filters1[6] = "50000"; // Max price
        fc.search(filters1); // Call search from FlightsController with the filters above
        flightList = fc.returnFlightList(); // Fetch List of Flights that derived from search()
        for (int i=0; i<flightList.size(); i++) {
            assertEquals("2019-06-06", flightList.get(i).getDate().toString());
        }
    }
    /**
     * Test of search method, of class FlightsController and getArrivalDestination() method, of class Flight
     * to see if searched destinations are all correct.
     */
    @Test
    public void testSearchDestination() {
        filters1[0] = "%"; // Flight number
        filters1[1] = "%"; // Departure
        filters1[2] = "Reykjavik"; // Arrival
        filters1[3] = "2019-05-01"; // Min date
        filters1[4] = "2019-06-01"; // Max date
        filters1[5] = "0"; // Min price
        filters1[6] = "50000"; // Max price
        fc.search(filters1); // Call search from FlightsController with the filters above
        flightList = fc.returnFlightList(); // Fetch List of Flights that derived from search()
        for (int i=0; i<flightList.size(); i++) {
            assertEquals("Reykjavik", flightList.get(i).getArrivalDestination());
        }
    }
        /**
     * Test of search method, of class FlightsController and getArrivalDestination() method, of class Flight
     * to see searched flights are in the price between 25000-35000 like entered in the filter.
     */
    @Test
    public void testSearchPrice() {
        filters1[0] = "%"; // Flight number
        filters1[1] = "%"; // Departure
        filters1[2] = "%"; // Arrival
        filters1[3] = "2019-05-01"; // Min date
        filters1[4] = "2019-06-01"; // Max date
        filters1[5] = "25000"; // Min price
        filters1[6] = "35000"; // Max price
        fc.search(filters1); // Call search from FlightsController with the filters above
        flightList = fc.returnFlightList(); // Fetch List of Flights that derived from search()
        for (int i=0; i<flightList.size(); i++) {
            assertEquals(30000, flightList.get(i).getPrice(), 5000); // Asserts that all numbers are between 25.000-35.000.
        }
    }
        /**
     * Test of search method, of class FlightsController and getArrivalDestination() method, of class Flight
     * to see there are any flights in the database without a  departure location.
     */
    @Test
    public void testSearchNull() {
        filters1[0] = "%"; // Flight number
        filters1[1] = "NULL"; // Departure
        filters1[2] = "%"; // Arrival
        filters1[3] = "2019-01-01"; // Min date
        filters1[4] = "2020-01-01"; // Max date
        filters1[5] = "0"; // Min price
        filters1[6] = "50000"; // Max price
        fc.search(filters1); // Call search from FlightsController with the filters above
        flightList = fc.returnFlightList(); // Fetch List of Flights that derived from search()
        assertTrue(flightList.isEmpty()); // 
    }
}