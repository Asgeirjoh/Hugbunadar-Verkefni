/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.database;

import flights.booking.Booking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import flights.search.Flight;

/**
 *
 * @author Bjarki Páll Hafþórsson, bph6@hi.is
 */
public class DatabaseManager {
    
    private String queryString;
    
    /**
     * insert booking into bookeFlights table
     * @param booking 
     */
    public void setBooking(Booking booking) {
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            
            String n = booking.getName();
            int idNum = booking.getIdNumber();
            String payType = booking.getPaymentType();
            String fNumber = booking.getFlightNumber();
            String fDepartureLocation = booking.getDepartureLocation();
            String fArrivalLocation = booking.getArrivalDestination();
            String dTime = booking.getDepartureTime().toString();
            String aTime = booking.getArrivalTime().toString();
            String dte = booking.getDate().toString();
            String airln = booking.getAirline();
            String typeSeat = booking.getTypeofSeat();
            int prc = booking.getPrice();
            
            String stmt1 = "INSERT INTO bookedFlights (name,idNumber,"
                    + "paymentType,flightNumber,departureLocation,arrivalDestination,departureTime,arrivalTime,"
                    + "date,airline,typeofSeat,price) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement p = connection.prepareStatement(stmt1);
            p.clearParameters();
            p.setString(1,n);
            p.setInt(2,idNum);
            p.setString(3,payType);
            p.setString(4,fNumber);
            p.setString(5,fDepartureLocation);
            p.setString(6,fArrivalLocation);
            p.setString(7,dTime);
            p.setString(8,aTime);
            p.setString(9,dte);
            p.setString(10,airln);
            p.setString(11,typeSeat);
            p.setInt(12,prc);
            p.executeUpdate();
            
            connection.close();
        } catch (SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
    
    /**
     * Returns the contents of table bookedFlights
     * @return ArrayList<Booking> of booked flights
     */
    public ArrayList<Booking> getBookings() {
        //Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            
            String stmt1 = "SELECT * FROM bookedFlights";
            PreparedStatement p = connection.prepareStatement(stmt1);
            ResultSet rs = p.executeQuery();
            
            ArrayList<Booking> results = new ArrayList<>();
            while (rs.next()) { 
                results.add(
                        new Booking(
                                rs.getString("name"), 
                                rs.getString("idNumber"), 
                                rs.getString("paymentType"), 
                                rs.getString("flightNumber"), 
                                rs.getString("departureLocation"), 
                                rs.getString("arrivalDestination"), 
                                rs.getString("departureTime"), 
                                rs.getString("arrivalTime"), 
                                rs.getString("date"), 
                                rs.getString("airline"), 
                                rs.getString("typeofSeat"),  
                                rs.getString("price")
                        )
                );
            }
            rs.close();
            connection.close();
            return results;
        } catch (SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
        return null;
    }
    
    /**
     * Removes a booking from the bookedFlights table
     * @param booking 
     */
    public void cancelBooking(Booking booking){
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            
            String n = booking.getName();
            int idNum = booking.getIdNumber();
            String payType = booking.getPaymentType();
            String fNumber = booking.getFlightNumber();
            String fDepartureLocation = booking.getDepartureLocation();
            String fArrivalLocation = booking.getArrivalDestination();
            String dTime = booking.getDepartureTime().toString();
            String aTime = booking.getArrivalTime().toString();
            String dte = booking.getDate().toString();
            String airln = booking.getAirline();
            String typeSeat = booking.getTypeofSeat();
            int prc = booking.getPrice();
            
            String stmt1 = "DELETE FROM bookedFlights WHERE name = ? "
                    + "AND idNumber = ? "
                    + "AND paymentType = ? "
                    + "AND flightNumber = ? "
                    + "AND departureLocation = ? "
                    + "AND arrivalDestination = ? "
                    + "AND departureTime = ? "
                    + "AND arrivalTime = ? "
                    + "AND date = ? "
                    + "AND airline = ? "
                    + "AND typeofSeat = ? "
                    + "AND price = ?";
            PreparedStatement p = connection.prepareStatement(stmt1);
            p.clearParameters();
            p.setString(1,n);
            p.setInt(2,idNum);
            p.setString(3,payType);
            p.setString(4,fNumber);
            p.setString(5,fDepartureLocation);
            p.setString(6,fArrivalLocation);
            p.setString(7,dTime);
            p.setString(8,aTime);
            p.setString(9,dte);
            p.setString(10,airln);
            p.setString(11,typeSeat);
            p.setInt(12,prc);
            
            p.executeUpdate();         
            connection.close();
        } catch (SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
    
    /**
     * Returns a list of airports that flights involve
     * @return ArrayList<String>  of airports
     */
    public ArrayList<String> fetchAirports() {
        //Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            
            
            String stmt1 = "SELECT DISTINCT arrivalDestination FROM flights ORDER BY arrivalDestination";
            PreparedStatement p = connection.prepareStatement(stmt1);
            ResultSet rs = p.executeQuery();
            
            ArrayList<String> airports = new ArrayList<>();
            while (rs.next()) { 
                airports.add(rs.getString(1));
            }
            rs.close();
            connection.close();
            return airports;
        } catch (SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
        return null;
    }
    /**
     * Returns a list of flights from table flights based on parameters
     * @param parameters a string array that specifies the filters used
     * @return ArrayList of flights that fit the parameters specified
     */
    public ArrayList<Flight> filterDB(String[] parameters) {

        //Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            
            
            // preparedstatement functionality that uses the parameters to filter the results.
            String fNumber = parameters[0];
            String firstDate = parameters[1];
            String secondDate = parameters[2];
            String fdepartureLocation = parameters[3];
            String farrivalLocation = parameters[4];
            String minPrice = parameters[5];
            String maxPrice = parameters[6];
            String stmt1 = "SELECT * FROM flights WHERE flightNumber LIKE ? "
                    + "AND departureLocation LIKE ? "
                    + "AND arrivalDestination LIKE ? "
                    + "AND (date BETWEEN ? AND ?) "
                    + "AND price BETWEEN ? AND ?"
                    + "AND (aisleSeats != 0 OR windowSeats != 0)";
            PreparedStatement p = connection.prepareStatement(stmt1);
            p.clearParameters();
            p.setString(1,fNumber);
            p.setString(2,fdepartureLocation);
            p.setString(3,farrivalLocation);
            p.setString(4,firstDate);
            p.setString(5,secondDate);
            p.setString(6,minPrice);
            p.setString(7,maxPrice);
            ResultSet rs = p.executeQuery();
            
            ArrayList<Flight> results = new ArrayList<>();
            while (rs.next()) { 
                results.add(
                        new Flight(
                                rs.getString("flightNumber"), 
                                rs.getString("departureLocation"), 
                                rs.getString("arrivalDestination"), 
                                rs.getString("departureTime"), 
                                rs.getString("arrivalTime"), 
                                rs.getString("date"), 
                                rs.getString("airline"), 
                                rs.getString("aisleSeats"), 
                                rs.getString("windowSeats"), 
                                rs.getString("price")
                        )
                );
            }
            rs.close();
            connection.close();
            return results;
        } catch (SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
        return null;
    }
    
    /**
     * decrement available seats by one in database table flights
     * @param flight
     * @param seat 
     */
    public void decrementSeats(Flight flight, String seat) {
        
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            String stmt1 = null;
            String fNumber = flight.getFlightNumber();
            String dte = flight.getDate().toString();
            if ("Window Seat".equals(seat)) {
                stmt1 = "UPDATE flights " +
                        "SET windowSeats = windowSeats - 1 " +
                        "WHERE flightNumber = ? " +
                        "AND date = ?";
            }
            else if ("Aisle Seat".equals(seat)) {
                stmt1 = "UPDATE flights " +
                        "SET aisleSeats = aisleSeats - 1 " +
                        "WHERE flightNumber = ? " +
                        "AND date = ?";
            }

            PreparedStatement p = connection.prepareStatement(stmt1);
            p.clearParameters();
            p.setString(1,fNumber);
            p.setString(2,dte);
            
            p.executeUpdate();         
            connection.close();
        } catch (SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
    
    /**
     * increment available seats by one in database table flights
     * @param flight
     * @param seat 
     */
     public void incrementSeats(Booking booking) {
        
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            String stmt1 = null;
            String fNumber = booking.getFlightNumber();
            String dte = booking.getDate().toString();
            if ("Window Seat".equals(booking.getTypeofSeat())) {
                stmt1 = "UPDATE flights " +
                        "SET windowSeats = windowSeats + 1 " +
                        "WHERE flightNumber = ? " +
                        "AND date = ?";
            }
            else if ("Aisle Seat".equals(booking.getTypeofSeat())) {
                stmt1 = "UPDATE flights " +
                        "SET aisleSeats = aisleSeats + 1 " +
                        "WHERE flightNumber = ? " +
                        "AND date = ?";
            }

            PreparedStatement p = connection.prepareStatement(stmt1);
            p.clearParameters();
            p.setString(1,fNumber);
            p.setString(2,dte);
            
            p.executeUpdate();         
            connection.close();
        } catch (SQLException e) {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
