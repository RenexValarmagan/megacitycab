<%@ page import="java.sql.*, com.bsc.megacitycab.utils.DBConnection" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String orderNumber = request.getParameter("order");
  if (orderNumber == null || orderNumber.trim().isEmpty()) {
    response.sendRedirect("customer-dashboard.jsp?error=invalid_order");
    return;
  }

  Connection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  try {
    conn = DBConnection.getConnection();
    String sql = "SELECT b.order_number, u.username, l1.location_name AS pickup, l2.location_name AS dropoff, " +
            "v.type AS vehicle, d.name AS driver, b.fare, b.created_at " +
            "FROM bookings b " +
            "JOIN users u ON b.customer_id = u.id " +
            "JOIN locations l1 ON b.pickup_location_id = l1.id " +
            "JOIN locations l2 ON b.drop_location_id = l2.id " +
            "JOIN vehicles v ON b.vehicle_id = v.id " +
            "JOIN drivers d ON b.driver_id = d.id " +
            "WHERE b.order_number = ?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, orderNumber);
    rs = pstmt.executeQuery();

    if (rs.next()) {
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Booking Confirmation</title>
</head>
<body>
<h2>Booking Confirmation</h2>
<p><strong>Order Number:</strong> <%= rs.getString("order_number") %></p>
<p><strong>Customer Name:</strong> <%= rs.getString("username") %></p>
<p><strong>Pickup Location:</strong> <%= rs.getString("pickup") %></p>
<p><strong>Drop-off Location:</strong> <%= rs.getString("dropoff") %></p>
<p><strong>Vehicle Type:</strong> <%= rs.getString("vehicle") %></p>
<p><strong>Assigned Driver:</strong> <%= rs.getString("driver") %></p>
<p><strong>Fare:</strong> Rs. <%= rs.getDouble("fare") %></p>
<p><strong>Booking Time:</strong> <%= sdf.format(rs.getTimestamp("created_at")) %></p>
<br>
<a href="customer-dashboard.jsp">Back to Dashboard</a> |
<a href="downloadBill.jsp?order=<%= orderNumber %>">Download Bill</a>
</body>
</html>
<%
    } else {
      response.sendRedirect("customer-dashboard.jsp?error=not_found");
    }
  } catch (SQLException e) {
    e.printStackTrace();
    response.sendRedirect("customer-dashboard.jsp?error=server_error");
  } finally {
    try {
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    }
    catch (SQLException e) { e.printStackTrace(); }
  }
%>
