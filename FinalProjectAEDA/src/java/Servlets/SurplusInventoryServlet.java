/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
import java.sql.SQLException;

public class SurplusInventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve surplus inventory data from the database
            List<SurplusFoodItem> surplusInventoryItems = SurplusFoodItemDAO.getAllSurplusFoodItems();

            // Set the surplus inventory data as a request attribute
            request.setAttribute("surplusInventoryItems", surplusInventoryItems);

            // Forward the request to the SurplusInventory.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/SurplusInventory.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            
            // Handle any exceptions gracefully
            request.setAttribute("errorMessage", "An error occurred while retrieving surplus inventory data.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "SurplusInventoryServlet";
    }
}
