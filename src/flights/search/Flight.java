/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.search;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class Flight {
    private final String flightNumber;
    private final String departureLocation;
    private final String arrivalDestination;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;
    private final LocalDate date;
    private final String airline;
    private final int aisleSeats;
    private final int windowSeats;
    private final int price;
    
    /**
     * Flight object constructor.
     * All parameters are of type String.
     * 
     * @param fltNum
     * @param depLoc
     * @param dest
     * @param depTime
     * @param arrTime
     * @param fltDate
     * @param airl
     * @param aisle
     * @param window
     * @param fltPrice 
     */
    public Flight(String fltNum, String depLoc, String dest, String depTime, String arrTime, String fltDate, String airl, String aisle, String window, String fltPrice) {
        flightNumber = fltNum;
        departureLocation = depLoc;
        arrivalDestination = dest;
        departureTime = LocalTime.parse(depTime);
        arrivalTime = LocalTime.parse(arrTime);
        date = LocalDate.parse(fltDate);
        airline = airl;
        aisleSeats = Integer.parseInt(aisle);
        windowSeats = Integer.parseInt(window);
        price = Integer.parseInt(fltPrice);
        
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAirline() {
        return airline;
    }

    public int getAisleSeats() {
        return aisleSeats;
    }

    public int getWindowSeats() {
        return windowSeats;
    }
    
    public int getSeating() {
        return windowSeats+aisleSeats;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Flight{" + "flightNumber=" + flightNumber + ", departureLocation=" + departureLocation + ", arrivalDestination=" + arrivalDestination + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", date=" + date + ", airline=" + airline + ", aisleSeats=" + aisleSeats + ", windowSeats=" + windowSeats + ", price=" + price + '}';
    }
}
