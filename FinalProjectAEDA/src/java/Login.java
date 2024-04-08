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

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username"); 
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaSQL", "root", "Sanjeevseda123!");
            
            // Prepare SQL query to check if user exists
            String sql = "SELECT * FROM users WHERE name=? and password=? and user_type=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2,password);
            pstmt.setString(3, userType);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                if ("consumer".equals(userType)) {
                    response.sendRedirect("Consumers.jsp");
                } else if ("retailer".equals(userType)) {
                    response.sendRedirect("Retailers.jsp");
                } else if ("charitableOrganization".equals(userType)) {
                    response.sendRedirect("CharitableOrganization.jsp");
                } else {
                    response.getWriter().println("Unknown user type");
                }
            } else {
                response.sendRedirect("Retry.jsp");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
