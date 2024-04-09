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

@WebServlet("/ConsumerServlet")
public class CharitableOrganizationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fwrp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Salvation@7";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM surplus_food";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            out.println("<html><head><title>Food Management System</title><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head><body>");
            out.println("<h1>Welcome to Food Management System</h1>");
            out.println("<h2>Items for Claim:</h2>");
            out.println("<table>");
            out.println("<tr><th>Retailer ID</th><th>Item Name</th><th>Quantity</th><th>Expiration Date</th><th>Action</th></tr>");

            while (resultSet.next()) {
                out.println("<tr><td>" + resultSet.getInt("retailer_id") + "</td><td>" + resultSet.getString("item_name") +
                        "</td><td>" + resultSet.getInt("quantity") + "</td><td>" + resultSet.getString("expiration_date") +
                        "</td><td><form action=\"ConsumerServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"action\" value=\"claim\">" + // Hidden field to differentiate action
                        "<input type=\"hidden\" name=\"surplus_id\" value=\"" + resultSet.getInt("surplus_id") + "\">" +
                        "<input type=\"submit\" value=\"Claim\">" +
                        "</form><form action=\"ConsumerServlet\" method=\"post\">" + // Add form for drop action
                        "<input type=\"hidden\" name=\"action\" value=\"drop\">" + // Hidden field for drop action
                        "<input type=\"hidden\" name=\"surplus_id\" value=\"" + resultSet.getInt("surplus_id") + "\">" +
                        "<input type=\"submit\" value=\"Drop\"></form></td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h2>Connection Status:</h2><p>Connection Unsuccessful!</p></body></html>");
        } finally {
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
        String action = request.getParameter("action");
        String surplusIdStr = request.getParameter("surplus_id");

        if (surplusIdStr == null || action == null) {
            response.sendRedirect(request.getContextPath() + "/ConsumerServlet");
            return;
        }

        int surplusId = Integer.parseInt(surplusIdStr);

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String updateQuery = "";
            if ("claim".equals(action)) {
                // Decrease quantity for claim action
                updateQuery = "UPDATE surplus_food SET quantity = quantity - 1 WHERE surplus_id = ? AND quantity > 0";
            } else if ("drop".equals(action)) {
                // Increase quantity for drop action
                updateQuery = "UPDATE surplus_food SET quantity = quantity + 1 WHERE surplus_id = ?";
            }

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, surplusId);
            int rowsAffected = updateStatement.executeUpdate();

            // Redirect based on the action outcome
            if (rowsAffected > 0) {
                response.sendRedirect(request.getContextPath() + "/ConsumerServlet?success=" + action + "_successful");
            } else {
                // For claim action, if no rows are affected, it means no quantity is available to claim
                if ("claim".equals(action)) {
                    response.sendRedirect(request.getContextPath() + "/ConsumerServlet?error=no_quantity");
                } else {
                    response.sendRedirect(request.getContextPath() + "/ConsumerServlet");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
