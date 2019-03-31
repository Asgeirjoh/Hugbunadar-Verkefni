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

            ResultSet rs = statement.executeQuery("SELECT * FROM flights WHERE flightNumber = 'NY112'");
            ArrayList<Flight> results = new ArrayList<>();
            while (rs.next()) {
                results.add(new Flight(rs.getString("flightNumber")));
                System.out.println("Flight number: " + rs.getString("flightNumber") + "   Date: "+ rs.getString("date"));
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
            ResultSet rs = statement.executeQuery("SELECT * FROM flights WHERE flightNumber = 'NY112'");
            ArrayList<Flight> results = new ArrayList<>();
            while (rs.next()) {
                results.add(new Flight(rs.getString("flightNumber")));
                System.out.println("Flight number: " + rs.getString("flightNumber") + "   Date: "+ rs.getString("date"));
            }
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
