/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaCode;

/**
 *
 * @author Armando Mavova Bazeydio
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class InventoryDAO {

    public static List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        try {
            // Get the connection instance from DatabaseConnection singleton
            Connection conn = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT * FROM inventory";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        InventoryItem item = new InventoryItem();
                        item.setItemId(rs.getInt("item_id"));
                        item.setItemName(rs.getString("item_name"));
                        item.setQuantity(rs.getInt("quantity"));
                        item.setExpirationDate(rs.getString("expiration_date"));
                        inventoryItems.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryItems;
    }
    
   public static boolean addInventoryItem(InventoryItem item, Connection connection) throws SQLException {
    // Define your SQL INSERT statement
    String sql = "INSERT INTO inventory (item_name, quantity, expiration_date) VALUES (?, ?, ?)";
    
    // Prepare the statement
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        // Set the parameters
        statement.setString(1, item.getItemName());
        statement.setInt(2, item.getQuantity());
        statement.setString(3, item.getExpirationDate());
        
        // Execute the statement
        int rowsInserted = statement.executeUpdate();
        
        // Check if the insertion was successful
        return rowsInserted > 0;
    } catch (SQLException e) {
        // Handle any SQL exceptions
        e.printStackTrace();
        throw e; // Rethrow the exception to the caller
    }
}

    public static boolean updateInventoryItem(InventoryItem item, Connection connection) throws SQLException {
    try {
        // Define your SQL UPDATE statement
        String sql = "UPDATE inventory SET quantity=?, expiration_date=? WHERE item_name=?";
        
        // Prepare the statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the parameters
            statement.setInt(1, item.getQuantity());
            statement.setString(2, item.getExpirationDate());
            statement.setString(3, item.getItemName());
            
            // Execute the statement
            int rowsUpdated = statement.executeUpdate();
            
            // Check if the update was successful
            return rowsUpdated > 0;
        }
    } catch (SQLException e) {
        // Handle any SQL exceptions
        e.printStackTrace();
        throw e; // Rethrow the exception to the caller
    }
}

public static boolean deleteInventoryItem(String itemName, Connection connection) {
    try {
        String sql = "DELETE FROM inventory WHERE item_name=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemName);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}


