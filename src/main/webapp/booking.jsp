<%@ page session="true" %>
<%@ page import="com.bsc.megacitycab.models.User, java.sql.*" %>

<%
    // Get logged-in user details from session
    User loggedInUser = (User) session.getAttribute("user");
    if (loggedInUser == null) {
        response.sendRedirect("index.jsp"); // Redirect if not logged in
        return;
    }
%>

<html>
<head>
    <title>Book a Cab</title>
</head>
<body>
<h2>Cab Booking</h2>

<form action="BookingServlet" method="post">
    <label>Name:</label>
    <input type="text" name="name" value="<%= loggedInUser.getUsername() %>" readonly><br>

    <label>Pickup Location:</label>
    <select name="pickupLocation" required>
        <%
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/megacitycab", "root", "password");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id, name FROM locations");
                while (rs.next()) {
        %>
        <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option>
        <%
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </select><br>

    <label>Drop Location:</label>
    <select name="dropLocation" required>
        <%
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/megacitycab", "root", "password");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id, name FROM locations");
                while (rs.next()) {
        %>
        <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option>
        <%
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </select><br>

    <label>Select Vehicle:</label><br>
    <input type="radio" name="vehicleType" value="Bike" required> Bike<br>
    <input type="radio" name="vehicleType" value="Tuk Tuk" required> Tuk Tuk<br>
    <input type="radio" name="vehicleType" value="Van" required> Van<br>

    <button type="submit">Book Now</button>
</form>
</body>
</html>
