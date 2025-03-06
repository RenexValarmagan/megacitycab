<%@ page import="java.sql.*, com.bsc.megacitycab.utils.DBConnection" %>
<%@ page import="com.bsc.megacitycab.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sessionObj = request.getSession();
    User loggedInUser = (User) sessionObj.getAttribute("user");

    if (loggedInUser == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book a Ride</title>
</head>
<body>
<h2>Book a Ride</h2>

<form action="BookingServlet" method="post">
    <!-- Pickup Location -->
    <label for="pickupLocation">Pickup Location:</label>
    <select name="pickupLocation" id="pickupLocation" required>
        <option value="">Select Pickup Location</option>
        <%
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT id, name FROM locations");
                while (rs.next()) {
        %>
        <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("<p>Error fetching locations. Please try again later.</p>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
    </select>
    <br>

    <!-- Drop Location -->
    <label for="dropLocation">Drop Location:</label>
    <select name="dropLocation" id="dropLocation" required>
        <option value="">Select Drop Location</option>
        <%
            try {
                // Re-initialize the ResultSet for drop location dropdown
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT id, name FROM locations");
                while (rs.next()) {
        %>
        <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("<p>Error fetching locations. Please try again later.</p>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
    </select>
    <br>

    <!-- Vehicle Type -->
    <label for="vehicleType">Vehicle Type:</label>
    <select name="vehicleType" id="vehicleType" required>
        <option value="">Select Vehicle Type</option>
        <option value="Bike">Bike</option>
        <option value="Tuk Tuk">Tuk Tuk</option>
        <option value="Van">Van</option>
    </select>
    <br>

    <!-- Customer Address -->
    <label for="address">Your Address:</label>
    <input type="text" id="address" name="address" placeholder="Enter your address" required>
    <br>

    <!-- Customer Phone Number -->
    <label for="phoneNumber">Your Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Enter your phone number" required>
    <br>

    <!-- Submit Button -->
    <input type="submit" value="Book Now">
</form>

</body>
</html>
