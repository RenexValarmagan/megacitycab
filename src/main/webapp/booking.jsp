<%@ page import="com.bsc.megacitycab.dao.LocationsDAO" %>
<%@ page import="com.bsc.megacitycab.dao.VehiclesDAO" %>
<%@ page import="com.bsc.megacitycab.models.Customer" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Book Your Cab</title>
  <style>
    /* Reset default browser styles */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f7fa;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      padding: 0 20px;
      flex-direction: column;
    }

    .booking-container {
      background-color: #ffffff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 600px;
      margin-top: 20px;
    }

    h2 {
      text-align: center;
      margin-bottom: 20px;
      font-size: 24px;
      color: #333;
    }

    form {
      display: flex;
      flex-direction: column;
    }

    label {
      margin-bottom: 8px;
      font-size: 14px;
      color: #555;
    }

    input[type="text"], select {
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 14px;
      color: #333;
      outline: none;
      transition: border 0.3s ease;
    }

    input[type="text"]:focus, select:focus {
      border-color: #4CAF50;
    }

    button[type="submit"] {
      background-color: #4CAF50;
      color: #fff;
      border: none;
      padding: 12px;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    button[type="submit"]:hover {
      background-color: #45a049;
    }

    /* Error and success message styles */
    .error-message, .success-message {
      text-align: center;
      margin-bottom: 15px;
    }

    .error-message {
      color: red;
    }

    .success-message {
      color: green;
    }
  </style>
</head>
<body>
<div class="booking-container">
  <h2>Book Your Cab</h2>

  <% if (request.getParameter("error") != null) { %>
  <p class="error-message">Booking failed. Please try again.</p>
  <% } %>

  <% if (request.getParameter("success") != null) { %>
  <p class="success-message">Booking successful! Your order number is: <%= request.getAttribute("orderNumber") %></p>
  <% } %>

  <form action="BookingServlet" method="post">
    <!-- Customer Name: auto-filled from session or database -->
    <label for="customerName">Name:</label>
    <input type="text" id="customerName" name="customerName" value="${customer.name}" readonly /> <!-- Auto-filled name -->

    <!-- Customer Phone -->
    <label for="customerPhone">Phone:</label>
    <input type="text" id="customerPhone" name="customerPhone" required /> <!-- Customer phone to be entered -->

    <!-- Customer ID: Assuming it is stored in session -->
    <input type="hidden" name="customerId" value="${customer.id}" /> <!-- Hidden customerId -->

    <%@ page import="com.bsc.megacitycab.dao.LocationsDAO" %>
    <%@ page import="com.bsc.megacitycab.models.Location" %>
    <%@ page import="java.util.List" %>
    <%@ page import="com.bsc.megacitycab.models.Vehicle" %>

    <%
      List<Location> locations = LocationsDAO.getAllLocations();
    %>

    <label for="pickupLocationId">Pickup Location:</label>
    <select name="pickupLocationId" id="pickupLocationId" required>
      <option value="">Select Pickup Location</option>
      <% for (Location location : locations) { %>
      <option value="<%= location.getId() %>"><%= location.getName() %></option>
      <% } %>
    </select>

    <label for="dropLocationId">Drop Location:</label>
    <select name="dropLocationId" id="dropLocationId" required>
      <option value="">Select Drop Location</option>
      <% for (Location location : locations) { %>
      <option value="<%= location.getId() %>"><%= location.getName() %></option>
      <% } %>
    </select>


    <%
      List<Vehicle> vehicles = VehiclesDAO.getAllVehicles();
    %>

    <label for="vehicleId">Select Vehicle:</label>
    <select name="vehicleId" id="vehicleId" required>
      <option value="">Select Vehicle</option>
      <% for (Vehicle vehicle : vehicles) { %>
      <option value="<%= vehicle.getId() %>"><%= vehicle.getType() %></option>
      <% } %>
    </select>

    <!-- Auto-generated Order Number -->
    <input type="hidden" name="orderNumber" value="<%= UUID.randomUUID().toString() %>" />
    <input type="hidden" name="customerId" value="${customer != null ? customer.id : ''}" />


    <!-- Submit Button -->
    <button type="submit">Book Your Cab</button>
  </form>
</div>

</body>
</html>
