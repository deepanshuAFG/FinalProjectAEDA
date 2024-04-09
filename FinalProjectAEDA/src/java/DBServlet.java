import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/DBConnServlet")
public class DBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fwrp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Aaniq**143**3675";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        try {
        	
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Retrieve records from the users table
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Display retrieved records
            out.println("<html><body>");
            out.println("<h2>User Records:</h2>");
            out.println("<ul>");

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String userType = resultSet.getString("user_type");

                out.println("<li>User ID: " + userId + ", Name: " + name + ", Email: " + email + ", User Type: " + userType + "</li>");
            }

            out.println("</ul>");
            out.println("</body></html>");

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
        doGet(request, response);
    }
}
