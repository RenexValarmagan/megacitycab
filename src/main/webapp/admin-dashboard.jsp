<%@ page import="com.bsc.megacitycab.models.Driver" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bsc.megacitycab.dao.DriverDAO" %>
<%@ page import="com.bsc.megacitycab.models.Vehicle" %>
<%@ page import="com.bsc.megacitycab.models.Booking" %>
<%@ page import="com.bsc.megacitycab.dao.VehiclesDAO" %>
<%@ page import="com.bsc.megacitycab.dao.BookingDAO" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.bsc.megacitycab.utils.DBConnection" %>
<%@ page session="true" %>
<%
  String role = (String) session.getAttribute("role");
  if (role == null || !"admin".equals(role)) {
    response.sendRedirect("index.jsp?error=unauthorized");
    return;
  }

  // Fetching all drivers, vehicles, and bookings for display
  List<Driver> drivers = DriverDAO.getAllDrivers();
  List<Vehicle> vehicles = VehiclesDAO.getAllVehicles();
  // Get database connection
  Connection connection = DBConnection.getConnection();

  // Initialize BookingDAO with the connection
  BookingDAO bookingDAO = new BookingDAO(connection);

  // Retrieve bookings for the logged-in customer
  List<Booking> bookings = bookingDAO.getAllBookings(); // Make sure you have the BookingDAO
%>

<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard</title>
  <style>
    table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
    th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }
    h3 { color: #333; }
    a { text-decoration: none; color: #007BFF; }
  </style>
</head>
<body>
<h2>Welcome, Admin!</h2>
<p>Manage system users, bookings, and vehicles.</p>
<a href="logout">Logout</a>

<!-- Manage Drivers Section -->
<h3>Manage Drivers</h3>

<!-- Add New Driver Form -->
<h4>Add New Driver</h4>
<form action="add-driver" method="post">
  <label>Name:</label><br>
  <input type="text" name="name" required><br><br>
  <label>Phone:</label><br>
  <input type="text" name="phone" required><br><br>
  <label>Status:</label><br>
  <select name="status">
    <option value="Active">Active</option>
    <option value="Inactive">Inactive</option>
  </select><br><br>
  <label>Vehicle:</label><br>
  <select name="vehicle_id">
    <option value="0">Select Vehicle</option>
    <% for (Vehicle vehicle : vehicles) { %>
    <option value="<%= vehicle.getId() %>"><%= vehicle.getType() %> - <%= vehicle.getType() %></option>
    <% } %>
  </select><br><br>
  <input type="submit" value="Add Driver">
</form>

<!-- View and Edit Drivers -->
<h4>Driver List</h4>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Phone</th>
    <th>Vehicle</th>
    <th>Status</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <% for (Driver driver : drivers) { %>
  <tr>
    <td><%= driver.getId() %></td>
    <td><%= driver.getName() %></td>
    <td><%= driver.getPhone() %></td>
    <td><%= driver.getVehicleId() == 0 ? "No Vehicle" : driver.getVehicleId() %></td>
    <td><%= driver.getStatus() %></td>
    <td>
      <a href="edit-driver.jsp?driverId=<%= driver.getId() %>">Edit</a> |
      <a href="delete-driver.jsp?driverId=<%= driver.getId() %>">Delete</a>
    </td>
  </tr>
  <% } %>
  </tbody>
</table>

<!-- Manage Vehicles Section -->
<h3>Manage Vehicles</h3>

<!-- Add New Vehicle Form -->
<h4>Add New Vehicle</h4>
<form action="add-vehicle" method="post">
  <label>Model:</label><br>
  <input type="text" name="model" required><br><br>
  <label>Type:</label><br>
  <select name="type">
    <option value="Car">Car</option>
    <option value="Van">Van</option>
    <option value="Bike">Bike</option>
    <option value="Tuk Tuk">Tuk Tuk</option>
  </select><br><br>
</form>

<!-- View and Edit Vehicles -->
<h4>Vehicle List</h4>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Model</th>
    <th>Type</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <% for (Vehicle vehicle : vehicles) { %>
  <tr>
    <td><%= vehicle.getId() %></td>
    <td><%= vehicle.getType() %></td>
    <td><%= vehicle.getType() %></td>
    <td>
      <a href="edit-vehicle.jsp?vehicleId=<%= vehicle.getId() %>">Edit</a> |
      <a href="delete-vehicle.jsp?vehicleId=<%= vehicle.getId() %>">Delete</a>
    </td>
  </tr>
  <% } %>
  </tbody>
</table>

<!-- View All Bookings Section -->
<h3>View All Bookings</h3>
<table>
  <thead>
  <tr>
    <th>Booking ID</th>
    <th>Customer Name</th>
    <th>Pickup Location</th>
    <th>Drop-off Location</th>
    <th>Vehicle Type</th>
  </tr>
  </thead>
  <tbody>
  <% for (Booking booking : bookings) { %>
  <tr>
    <td><%= booking.getOrderNumber() %></td>
    <td><%= booking.getCustomerName() %></td>
    <td><%= booking.getPickupLocationName() %></td>
    <td><%= booking.getDropLocationName() %></td>
    <td><%= booking.getVehicleName() %></td>
  </tr>
  <% } %>
  </tbody>
</table>

</body>
</html>
