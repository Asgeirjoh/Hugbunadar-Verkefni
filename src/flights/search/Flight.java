/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.time.LocalDate;

/**
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class Flight {
    private String flightNumber;
    private String departureLocation;
    private String arrivalDestination;
    //private Date departureTime;
    //private Date arrivalTime;
    private LocalDate date;
    private String Airline;
    private int aisleSeats;
    private int windowSeats;
    private int price;
    

    public Flight(String fltNum) {
        flightNumber = fltNum;
    }
    
    public void bookFlight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
      public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getArrivalDestination() {
        return arrivalDestination;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAirline() {
        return Airline;
    }

    public int getAisleSeats() {
        return aisleSeats;
    }

    public int getWindowSeats() {
        return windowSeats;
    }

    public int getPrice() {
        return price;
    }
}
