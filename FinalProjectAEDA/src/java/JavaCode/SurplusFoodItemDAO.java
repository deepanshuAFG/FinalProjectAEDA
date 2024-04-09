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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurplusFoodItemDAO {
    // Method to retrieve all surplus food items from the database
    public static List<SurplusFoodItem> getAllSurplusFoodItems() throws SQLException {
        List<SurplusFoodItem> surplusFoodItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT * FROM surplus_food";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SurplusFoodItem item = new SurplusFoodItem();
                item.setSurplusId(rs.getInt("surplus_id"));
                item.setRetailerId(rs.getInt("retailer_id"));
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
                item.setDonation(rs.getBoolean("is_donation"));
                item.setDiscountedPrice(rs.getBigDecimal("discounted_price"));
                surplusFoodItems.add(item);
            }
        } finally {
            // Close resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return surplusFoodItems;
    }

}

