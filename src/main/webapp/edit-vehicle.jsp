<%@ page import="com.bsc.megacitycab.models.Vehicle" %>
<%@ page import="com.bsc.megacitycab.dao.VehiclesDAO" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

<%
  String vehicleIdParam = request.getParameter("vehicleId");

  // Debugging logs
  System.out.println("Received vehicleId: " + vehicleIdParam);
  System.out.println("<p>Debug: Received vehicleId = " + vehicleIdParam + "</p>");

  if (vehicleIdParam == null || vehicleIdParam.trim().isEmpty()) {
    throw new IllegalArgumentException("Vehicle ID is required.");
  }

  int vehicleId = Integer.parseInt(vehicleIdParam);
  Vehicle vehicle = VehiclesDAO.getVehicleById(vehicleId);

  if (vehicle == null) {
    throw new IllegalArgumentException("Vehicle not found for ID: " + vehicleId);
  }

  List<Vehicle> vehicles = VehiclesDAO.getAllVehicles(); // You can replace this with another relevant list if needed
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Vehicle</title>
  <style>
    /* General Reset and Layout */
    body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f4f9;
      margin: 0;
      padding: 0;
      color: #333;
    }

    h2 {
      text-align: center;
      color: #333;
      margin-top: 20px;
    }

    .form-container {
      max-width: 600px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      margin-top: 40px;
    }

    .form-container label {
      display: block;
      margin-bottom: 8px;
      font-size: 16px;
      color: #333;
    }

    .form-container input,
    .form-container select {
      width: 100%;
      padding: 12px;
      margin-bottom: 20px;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 14px;
    }

    .form-container input[type="submit"] {
      background-color: #007BFF;
      color: white;
      border: none;
      cursor: pointer;
      font-size: 16px;
      padding: 15px;
      border-radius: 4px;
      transition: background-color 0.3s ease;
    }

    .form-container input[type="submit"]:hover {
      background-color: #0056b3;
    }

    .form-container select {
      background-color: #f9f9f9;
    }

    .form-container input:focus,
    .form-container select:focus {
      outline: none;
      border-color: #007BFF;
    }

    /* Responsive Design */
    @media screen and (max-width: 768px) {
      .form-container {
        padding: 20px;
      }

      .form-container input,
      .form-container select {
        font-size: 14px;
        padding: 10px;
      }

      .form-container input[type="submit"] {
        font-size: 14px;
        padding: 12px;
      }
    }
  </style>
</head>
<body>

<h2>Edit Vehicle</h2>

<div class="form-container">
  <form action="VehicleServlet" method="post">
    <%--@declare id="status"--%><%--@declare id="type"--%>
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="vehicleId" value="<%= vehicle.getId() %>">

    <label for="type">Vehicle Type:</label>
    <input type="text" name="type" value="<%= vehicle.getType() %>" required>

    <label for="status">Status:</label>
    <select name="status">
      <option value="Available" <%= "Available".equals(vehicle.getStatus()) ? "selected" : "" %>>Available</option>
      <option value="Unavailable" <%= "Unavailable".equals(vehicle.getStatus()) ? "selected" : "" %>>Unavailable</option>
    </select>

    <input type="submit" value="Update Vehicle">
  </form>
</div>

</body>
</html>
