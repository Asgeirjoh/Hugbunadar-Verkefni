/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flights.database;

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
     * @param args the command line arguments
     */

    // load the sqlite-JDBC driver using the current class loader
    public void getDB() {

        //Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM flights");
            while (rs.next()) {
                
            }
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
    public ArrayList<Flight> filterDB(String[] parameters) {

        //Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:.\\src\\flights\\database\\FlightDB.db");
            Statement statement = connection.createStatement();
            
            
            //TODO: rewrite query, add preparedstatement functionality that uses the parameters to filter the results.
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
                    + "AND price BETWEEN ? AND ?";
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
}
