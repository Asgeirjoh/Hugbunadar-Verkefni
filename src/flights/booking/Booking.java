/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.booking;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class Booking {
    private final String name;
    private final int idNumber;
    private final String paymentType;
    private final String flightNumber;
    private final String departureLocation;
    private final String arrivalDestination;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;
    private final LocalDate date;
    private final String airline;
    private final String typeofSeat;
    private final int price;
    
    /**
     * Flight object constructor.
     * All parameters are of type String.
     * 
     * @param n
     * @param idNum
     * @param payType
     * @param fltNum
     * @param depLoc
     * @param dest
     * @param depTime
     * @param arrTime
     * @param fltDate
     * @param airl
     * @param seat
     * @param fltPrice 
     */
    public Booking(String n, String idNum, String payType, String fltNum, String depLoc, String dest, String depTime, String arrTime, String fltDate, String airl, String seat, String fltPrice) {
        name = n;
        idNumber = Integer.parseInt(idNum);
        paymentType = payType;
        flightNumber = fltNum;
        departureLocation = depLoc;
        arrivalDestination = dest;
        departureTime = LocalTime.parse(depTime);
        arrivalTime = LocalTime.parse(arrTime);
        date = LocalDate.parse(fltDate);
        airline = airl;
        typeofSeat = seat;
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

    public String getTypeofSeat() {
        return typeofSeat;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Flight{" + "name=" + name + ", idNumber=" + idNumber + ", paymentType=" + paymentType + ", flightNumber=" + flightNumber + ", departureLocation=" + departureLocation + ", arrivalDestination=" + arrivalDestination + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", date=" + date + ", airline=" + airline + ", typeofSeat=" + typeofSeat + ", price=" + price + '}';
    }
    
}
