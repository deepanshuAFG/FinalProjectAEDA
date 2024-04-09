/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import JavaCode.*;
import java.sql.Connection;
import java.sql.SQLException;

public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve inventory data from the database
            List<InventoryItem> inventoryItems = InventoryDAO.getAllInventoryItems();

            // Set the inventory data as a request attribute
            request.setAttribute("inventoryItems", inventoryItems);
            
            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Inventory.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
             ex.printStackTrace();
            
            // Handle any exceptions gracefully
            request.setAttribute("errorMessage", "An error occurred while retrieving inventory data.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    
    if (action != null) {
        switch (action) {
            case "add":
            String itemName = request.getParameter("itemName");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    String expirationDate = request.getParameter("expirationDate");
                    
                    // Create a new InventoryItem object
                    InventoryItem newItem = new InventoryItem();
                    newItem.setItemName(itemName);
                    newItem.setQuantity(quantity);
                    newItem.setExpirationDate(expirationDate);
                    
                    try {
                        // Get the database connection
                        Connection connection = DatabaseConnection.getInstance().getConnection();
                        
                        // Add the item to the database
                        boolean added = InventoryDAO.addInventoryItem(newItem, connection);
                        
                        // Respond to the client accordingly
                        if (added) {
                             response.setHeader("Refresh", "3; URL=Retailers.jsp");
                        response.getWriter().write("Item added successfully! Redirecting...");
                        } else {
                            response.getWriter().write("Failed to add item.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.getWriter().write("Failed to add item.");
                    }
                    break;
            case "update":
           try {
        // Get the connection from DatabaseConnection singleton
        Connection conn = DatabaseConnection.getInstance().getConnection();
        
        // Retrieve parameters from the request
        String updatedItemName = request.getParameter("updatedItemName");
        int updatedQuantity = Integer.parseInt(request.getParameter("updatedQuantity"));
        String updatedExpirationDate = request.getParameter("updatedExpirationDate");

        // Create an InventoryItem object with updated values
        InventoryItem updatedItem = new InventoryItem();
        updatedItem.setItemName(updatedItemName);
        updatedItem.setQuantity(updatedQuantity);
        updatedItem.setExpirationDate(updatedExpirationDate);

        // Update the item in the database
        boolean updated = InventoryDAO.updateInventoryItem(updatedItem, conn);
        
        // Respond to the client accordingly
        if (updated) {
             response.setHeader("Refresh", "3; URL=Retailers.jsp");
             response.getWriter().write("Item updated successfully! Redirecting...");
        } else {
            response.getWriter().write("Failed to update item.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQL exception
        response.getWriter().write("Failed to update item: SQL exception occurred.");
    }
    break;

            case "delete":
    try {
        // Get the connection from DatabaseConnection singleton
        Connection conn = DatabaseConnection.getInstance().getConnection();
        
        // Retrieve the item name from the request
        String itemNameToDelete = request.getParameter("itemName");
        
        // Delete the item from the database
        boolean deleted = InventoryDAO.deleteInventoryItem(itemNameToDelete, conn);
        
        // Respond to the client accordingly
        if (deleted) {
             response.setHeader("Refresh", "3; URL=Retailers.jsp");
             response.getWriter().write("Item deleted successfully! Redirecting...");
        } else {
            response.getWriter().write("Failed to delete item.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQL exception
        response.getWriter().write("Failed to delete item: SQL exception occurred.");
    }
    break;
            case "goBack":
                // Redirect to the previous page
                response.sendRedirect("Retailers.jsp");
                break;
            default:
                // Handle unknown action
                break;
        }
    }
}


    @Override
    public String getServletInfo() {
        return "InventoryServlet";
    }
}
