/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaCode;
/**
 *
 * @author UsEr
 */
import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.SQLException;

  public class DatabaseConnection {
  	
  	
  	private static DatabaseConnection dbConnectionSingleton;
  	
  	
  	private static Connection connection = null;
  	private String serverUrl = "jdbc:mysql://localhost:3306/fwrp";
  	private String userString = "root";
  	private String passwordString = "Manoarmando1";
  	private String driverString = "com.mysql.cj.jdbc.Driver";
  	
  	DatabaseConnection() throws SQLException {
  		
  		try {
  			Class.forName(driverString);
  			DatabaseConnection.connection = DriverManager.getConnection(serverUrl, userString, passwordString);
  			
  		} catch (ClassNotFoundException ex) {
  			System.out.println("Connection was not successful: " + ex.getMessage());
  		}
  		
  	}
  	
    /**
     * Returns the singleton instance of the DBConnection class.
     * If the instance doesn't exist, creates a new one.
     * 
     * @return The singleton instance of DBConnection.
     * @throws SQLException if the connection cannot be established.
     */
  	public static DatabaseConnection getInstance() throws SQLException {
  		if (dbConnectionSingleton == null) {
  			dbConnectionSingleton = new DatabaseConnection();
  		}
  		return dbConnectionSingleton;
  	}


  	public static Connection getConnection() {
  		return connection;
  	}

        
         // Main method to test the DatabaseConnection class
   /* public static void main(String[] args) {
        try {
            // Create an instance of DatabaseConnection
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            // Get the connection
            Connection connection = dbConnection.getConnection();
            // Check if the connection is not null
            if (connection != null) {
                System.out.println("Connection to the database established successfully!");
            } else {
                System.out.println("Failed to establish connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    
  }
