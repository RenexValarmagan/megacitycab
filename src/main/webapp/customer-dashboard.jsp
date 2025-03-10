<%@ page import="com.bsc.megacitycab.models.Customer" %>
<%@ page import="com.bsc.megacitycab.models.Booking" %>
<%@ page import="com.bsc.megacitycab.dao.BookingDAO" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.bsc.megacitycab.utils.DBConnection" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%
    // Retrieve the logged-in customer from the session
    Customer loggedInCustomer = (Customer) session.getAttribute("customer");

    if (loggedInCustomer == null) {
        response.sendRedirect("index.jsp");  // Redirect if not logged in
        return;
    }

    // Get database connection
    Connection connection = DBConnection.getConnection();

    // Initialize BookingDAO with the connection
    BookingDAO bookingDAO = new BookingDAO(connection);

    // Retrieve bookings for the logged-in customer
    List<Booking> bookings = bookingDAO.getBookingsByCustomerId(loggedInCustomer.getId());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
</head>
<body>
<h2>Welcome, <%= loggedInCustomer.getUsername() %>!</h2>
<p>Need a Cab? <a href="<%= request.getContextPath() %>/booking.jsp">Book Your Cab</a></p>
<a href="<%= request.getContextPath() %>/logout">Logout</a>

<h3>Your Bookings</h3>
<table border="1">
    <thead>
    <tr>
        <th>Order Number</th>
        <th>Pickup Location</th>
        <th>Drop Location</th>
        <th>Vehicle Type</th>
        <th>Fare</th>
        <th>Details</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (bookings != null && !bookings.isEmpty()) {
            for (Booking booking : bookings) {
    %>
    <tr>
        <td><%= booking.getOrderNumber() %></td>
        <td><%= booking.getPickupLocationName() %></td> <!-- Showing Location Name -->
        <td><%= booking.getDropLocationName() %></td>   <!-- Showing Location Name -->
        <td><%= booking.getVehicleName() %></td>        <!-- Showing Vehicle Name -->
        <td><%= booking.getFare() %></td>
        <td><a href="<%= request.getContextPath() %>/bookingDetails.jsp?orderNumber=<%= booking.getOrderNumber() %>">View Details</a></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">No bookings found.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
