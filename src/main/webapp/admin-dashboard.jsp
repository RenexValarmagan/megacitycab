        <%@ page import="com.bsc.megacitycab.models.Driver" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bsc.megacitycab.dao.DriverDAO" %>
<%@ page import="com.bsc.megacitycab.models.Vehicle" %>
<%@ page import="com.bsc.megacitycab.dao.VehiclesDAO" %>
<%@ page import="com.bsc.megacitycab.dao.BookingDAO" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.bsc.megacitycab.utils.DBConnection" %>
<%@ page import="com.bsc.megacitycab.models.Booking" %>
<%@ page import="java.util.ArrayList" %>
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
    List<Booking> bookings;
    BookingDAO bookingDAO = null;
    try (Connection connection = DBConnection.getConnection()) {
        bookingDAO = new BookingDAO(connection);
        bookings = bookingDAO.getAllBookings();
    } catch (Exception e) {
        bookings = new ArrayList<>(); // Handle errors gracefully
        e.printStackTrace();
    }

%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f4f7fc;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    h2, h3 {
      color: #333;
    }

    a {
      text-decoration: none;
      color: #007BFF;
    }

    .container {
      margin-top: 30px;
    }

    .section-header {
      font-size: 1.5rem;
      font-weight: bold;
      margin-top: 30px;
      margin-bottom: 20px;
    }

    .card {
      border: none;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    }

    .card-body {
      text-align: center;
    }

    .card-title {
      font-size: 1.25rem;
      font-weight: bold;
    }

    table {
      width: 100%;
      margin-bottom: 20px;
      background-color: white;
      border-collapse: collapse;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    th, td {
      padding: 10px;
      border: 1px solid #ddd;
      text-align: center;
    }

    th {
      background-color: #007BFF;
      color: white;
    }

    td a {
      color: #007BFF;
    }

    .table-container {
      overflow-x: auto;
    }

    .form-container {
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      margin-bottom: 30px;
    }

    .form-container label {
      font-weight: bold;
    }

    .form-container select,
    .form-container input {
      margin-bottom: 15px;
      width: 100%;
      padding: 10px;
      border-radius: 4px;
      border: 1px solid #ddd;
    }

    .form-container input[type="submit"] {
      background-color: #007BFF;
      color: white;
      border: none;
      cursor: pointer;
    }

    .form-container input[type="submit"]:hover {
      background-color: #0056b3;
    }

    /* Modal styling */
    .modal-content {
      padding: 20px;
    }
  </style>
</head>
<body>

<div class="container">

  <!-- Top logout button -->
  <div class="text-center">
    <a href="logout" class="btn btn-danger">Logout</a>
  </div>

  <h2 class="text-center">Admin Dashboard</h2>
  <p class="text-center">Manage system users, bookings, and vehicles.</p>

  <!-- Navigation menu -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Mega City Cab</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#bookings-section">Bookings</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#drivers-section">Drivers</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#vehicles-section">Vehicles</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Dashboard Overview Cards -->
  <div class="row mt-4">
    <div class="col-md-4">
      <div class="card bg-primary text-white">
        <div class="card-body">
          <h5 class="card-title">Total Drivers</h5>
          <p class="card-text"><%=drivers.size()%></p>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card bg-success text-white">
        <div class="card-body">
          <h5 class="card-title">Total Bookings</h5>
          <p class="card-text"><%=bookings.size()%></p>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card bg-warning text-white">
        <div class="card-body">
          <h5 class="card-title">Total Vehicles</h5>
          <p class="card-text"><%=vehicles.size()%></p>
        </div>
      </div>
    </div>
  </div>

  <!-- Manage Drivers Section -->
  <div id="drivers-section" class="section-header">Manage Drivers</div>
  <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addDriverModal">Add New Driver</button>

  <!-- Add Driver Modal -->
  <div class="modal fade" id="addDriverModal" tabindex="-1" aria-labelledby="addDriverModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addDriverModalLabel">Add New Driver</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form action="DriverServlet" method="post">
            <input type="hidden" name="action" value="add">
            <%--@declare id="vehicle"--%><%--@declare id="phone"--%>
            <%--@declare id="name"--%>
              <label for="name">Name:</label><br>
            <input type="text" name="name" required><br>
            <label for="phone">Phone:</label><br>
            <input type="text" name="phone" required><br>
            <label for="status">Status:</label><br>
            <label>
              <select name="status">
                <option value="Available">Available</option>
                <option value="On Duty">On Duty</option>
              </select>
            </label><br>
            <label for="vehicle">Vehicle:</label><br>
            <label>
              <select name="vehicle_id">
                <option value="0">Select Vehicle</option>
                <% for (Vehicle vehicle : vehicles) { %>
                <option value="<%=vehicle.getId()%>"><%=vehicle.getType()%></option>
                <% } %>
              </select>
            </label><br>
            <input type="submit" value="Add Driver">
          </form>
        </div>
      </div>
    </div>
  </div>

  <!-- View Drivers -->
  <div class="section-header">Driver List</div>
  <div class="table-container">
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
        <td><%=driver.getId()%></td>
        <td><%=driver.getName()%></td>
        <td><%=driver.getPhone()%></td>
        <td><%=driver.getVehicleId() == 0 ? "No Vehicle" : driver.getVehicleId()%></td>
        <td><%=driver.getStatus()%></td>
        <td>
          <a href="edit-driver.jsp?driverId=<%= driver.getId() %>">Edit</a> |
          <a href="delete-driver.jsp?driverId=<%=driver.getId()%>">Delete</a>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>

  <!-- Manage Vehicles Section -->
  <div id="vehicles-section" class="section-header">Manage Vehicles</div>
  <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addVehicleModal">Add New Vehicle</button>

  <!-- Add Vehicle Modal -->
  <div class="modal fade" id="addVehicleModal" tabindex="-1" aria-labelledby="addVehicleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addVehicleModalLabel">Add New Vehicle</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form action="VehicleServlet" method="post">
            <input type="hidden" name="action" value="add">
            <%--@declare id="status"--%><%--@declare id="type"--%>
            <label for="type">Vehicle Type:</label><br>
            <input type="text" name="type" required><br>
            <label for="status">Status:</label><br>
            <select name="status">
              <option value="Available">Available</option>
              <option value="Unavailable">Unavailable</option>
            </select><br>
            <input type="submit" value="Add Vehicle">
          </form>
        </div>
      </div>
    </div>
  </div>

  <!-- View Vehicles -->
  <div class="section-header">Vehicle List</div>
  <div class="table-container">
    <table>
      <thead>
      <tr>
        <th>Vehicle ID</th>
        <th>Type</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <% for (Vehicle vehicle : vehicles) { %>
      <tr>
        <td><%=vehicle.getId()%></td>
        <td><%=vehicle.getType()%></td>
        <td><%=vehicle.getStatus()%></td>
        <td>
          <a href="edit-vehicle.jsp?vehicleId=<%=vehicle.getId()%>">Edit</a> |
          <a href="delete-vehicle.jsp?vehicleId=<%=vehicle.getId()%>">Delete</a>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>

  <!-- View All Bookings Section -->
  <div id="bookings-section" class="section-header">View All Bookings</div>
  <div class="table-container">
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
        <td><%=booking.getOrderNumber()%></td>
        <td><%=booking.getCustomerName()%></td>
        <td><%=booking.getPickupLocationName()%></td>
        <td><%=booking.getDropLocationName()%></td>
        <td><%=booking.getVehicleName()%></td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>

  <!-- Bottom logout button -->
  <div class="text-center">
    <a href="logout" class="btn btn-danger">Logout</a>
  </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
