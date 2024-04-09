import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.Notification;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notifications")
public class NotificationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Notification> notifications;
        notifications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        String name = request.getParameter("del_username"); 
        String password = request.getParameter("del_password");
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaSQL", "root", "Sanjeevseda123!");
            
            String sql = "SELECT notification_id, usertype, message, created_at, read_status, user_type FROM Notifications";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2,password);
            int rowsAffected = pstmt.executeUpdate();

            // Process the result set
            while (rs.next()) {
                int notificationId = rs.getInt("notification_id");
                String userType = rs.getString("usertype");
                String message = rs.getString("message");
                String createdAt = rs.getString("created_at");
                boolean readStatus = rs.getBoolean("read_status");
                String userType2 = rs.getString("user_type");
                
                // Create Notification object and add to the list
                Notification notification = new Notification(notificationId, userType, message, createdAt, readStatus, userType2);
                notifications.add(notification);
            }

            // Set notifications attribute to be accessed in JSP
            request.setAttribute("notifications", notifications);

            // Forward to JSP to render HTML
            request.getRequestDispatcher("notifications.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
