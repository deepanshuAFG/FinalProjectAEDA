/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Java;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author ELISHA KALYAN
 */
@WebServlet(name = "ClaimServlet", urlPatterns = {"/ClaimServlet"})
public class ClaimServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fwrp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Salvation@7";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Retrieve discounted items from the inventory
            String query = "SELECT * FROM surplus_food WHERE discounted_price IS NOT NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Start HTML document
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Food Management System</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">"); // Link to CSS file
            out.println("</head>");
            out.println("<body>");

            // Page header
            out.println("<h1>Welcome to Food Management System</h1>");

            // Table for discounted items
            out.println("<h2>Items for Claim:</h2>");
            out.println("<table>");
            out.println("<tr><th>Retailer ID</th><th>Item Name</th><th>Quantity</th><th>Expiration Date</th><th>Action</th></tr>");

            while (resultSet.next()) {
                int retailerId = resultSet.getInt("retailer_id");
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                String expirationDate = resultSet.getString("expiration_date");

                out.println("<tr><td>" + retailerId + "</td><td>" + itemName + "</td><td>" + quantity +
                        "</td><td>" + expirationDate + "</td>" +
                        "<td><form action=\"DiscountedItemsServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"surplus_id\" value=\"" + resultSet.getInt("surplus_id") + "\">" +
                        "<input type=\"number\" name=\"quantity\" value=\"1\" min=\"1\" max=\"" + quantity + "\">" +
                        "<input type=\"submit\" value=\"Claim\"></form></td></tr>");
            }

            out.println("</table>");

            // End HTML document
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Print connection error message
            out.println("<html><body>");
            out.println("<h2>Connection Status:</h2>");
            out.println("<p>Connection Unsuccessful!</p>");
            out.println("</body></html>");
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surplusIdStr = request.getParameter("surplus_id");
        String quantityStr = request.getParameter("quantity");

        if (surplusIdStr == null || quantityStr == null) {
            response.sendRedirect(request.getContextPath() + "/ClaimServlet");
            return;
        }

        int surplusId = Integer.parseInt(surplusIdStr);
        int quantity = Integer.parseInt(quantityStr);

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Update quantity in the database
            String updateQuery = "UPDATE surplus_food SET quantity = quantity - ? WHERE surplus_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, quantity);
            updateStatement.setInt(2, surplusId);
            updateStatement.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/DiscountedItemsServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}