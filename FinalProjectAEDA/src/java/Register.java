import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Register")
public class Register extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("reg_username");
        String email = request.getParameter("reg_email");
        String password = request.getParameter("reg_password");
        String userType = request.getParameter("userType");

        Connection conn = null;
        PreparedStatement pstmt = null;

        // Debugging statement before loading JDBC driver
        out.println("<p>Before loading MySQL JDBC Driver</p>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaSQL", "root", "Sanjeevseda123!");
            pstmt = conn.prepareStatement("INSERT INTO users (name, email, password, user_type) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, userType);
            int rowsInserted = pstmt.executeUpdate();
            out.println("<p>MySQL JDBC Driver loaded successfully</p>");
            if (rowsInserted > 0) {
                // Redirect user based on userType
                if ("consumer".equals(userType)) {
                    response.sendRedirect("Consumer.jsp");
                } else if ("retailer".equals(userType)) {
                    response.sendRedirect("Retailer.jsp");
                } else if ("charitableOrganization".equals(userType)) {
                    response.sendRedirect("CharitableOrganization.jsp");
                } else {
                    response.getWriter().println("Unknown user type");
                }
            } else {
                response.getWriter().println("Failed to register user.");
            }
        } catch (ClassNotFoundException e) {
            // Debugging statement in case of exception
            out.println("<p>Failed to load MySQL JDBC Driver: " + e.getMessage() + "</p>");
            e.printStackTrace();
         }
        catch (SQLException e) {
                out.println("Failed to close database connection: " + e.getMessage());
                e.printStackTrace();
            }
    }
    

}
