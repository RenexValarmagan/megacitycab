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
    <style>
        /* General styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h2 {
            color: #3c3c3c;
            font-size: 24px;
            margin-bottom: 10px;
        }

        h3 {
            color: #5c6bc0;
            font-size: 20px;
            margin-bottom: 20px;
        }

        /* Navbar styling */
        nav {
            background-color: #3f51b5;
            padding: 10px;
            text-align: center;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            margin: 0 15px;
            padding: 8px 20px;
            background-color: #5c6bc0;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        nav a:hover {
            background-color: #3f51b5;
        }

        /* Table styling */
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px 20px;
            text-align: left;
        }

        th {
            background-color: #5c6bc0;
            color: white;
            font-size: 16px;
        }

        td {
            background-color: #ffffff;
            font-size: 14px;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* Button styling */
        .btn {
            background-color: #3f51b5;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
            transition: background-color 0.3s ease;
            display: inline-block;
            margin-top: 20px;
        }

        .btn:hover {
            background-color: #5c6bc0;
        }

        /* Container styling */
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        /* Responsive Design */
        @media screen and (max-width: 768px) {
            table {
                font-size: 12px;
            }

            .container {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
<nav>
    <a href="<%= request.getContextPath() %>/booking.jsp">Book Your Cab</a>
    <a href="<%= request.getContextPath() %>/logout">Logout</a>
</nav>

<div class="container">
    <h2>Welcome, <%= loggedInCustomer.getUsername() %>!</h2>
    <h3>Your Bookings</h3>
    <table>
        <thead>
        <tr>
            <th>Order Number</th>
            <th>Pickup Location</th>
            <th>Drop Location</th>
            <th>Vehicle Type</th>
            <th>Fare</th>
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
            <!--<td><a class="btn" href="<%= request.getContextPath() %>/bookingDetails.jsp?orderNumber=<%= booking.getOrderNumber() %>">View Details</a></td> -->
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

    <!-- Button to go back to the customer dashboard and logout -->
    <a class="btn" href="<%= request.getContextPath() %>/booking.jsp">Book Your Cab</a>
    <a class="btn" href="<%= request.getContextPath() %>/logout">Logout</a>
</div>
</body>
</html>
