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
            String fNumber = "NY112";
            String firstDate = "2019-06-06";
            String secondDate = "2019-06-08";
            String fdepartureLocation = "Reykjavik";
            String farrivalLocation = "Akureyri";
            String minPrice = "0";
            String maxPrice = "60000";
            String stmt1 = "SELECT * FROM flights WHERE flightNumber = ? AND "
                    + "(date BETWEEN ? AND ?) AND departureLocation = ? AND arrivalDestination = ? "
                    + "AND price BETWEEN ? AND ?";
            PreparedStatement p = connection.prepareStatement(stmt1);
            p.clearParameters();
            p.setString(1,fNumber);
            p.setString(2,firstDate);
            p.setString(3,secondDate);
            p.setString(4,fdepartureLocation);
            p.setString(5,farrivalLocation);
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
                System.out.println("Flight number: " + rs.getString("flightNumber") + "   Date: "+ rs.getString("date") +  "   DepTime: "+ rs.getString("departureTime"));
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
