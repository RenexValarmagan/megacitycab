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
</head>
<body>
<h2>Edit Vehicle</h2>
<form action="VehicleServlet" method="post">
  <%--@declare id="type"--%><%--@declare id="status"--%>
  <input type="hidden" name="action" value="edit">
  <input type="hidden" name="vehicleId" value="<%= vehicle.getId() %>">

  <label for="type">Vehicle Type:</label><br>
  <input type="text" name="type" value="<%= vehicle.getType() %>" required><br>

  <label for="status">Status:</label><br>
  <select name="status">
    <option value="Available" <%= "Available".equals(vehicle.getStatus()) ? "selected" : "" %>>Available</option>
    <option value="Unavailable" <%= "Unavailable".equals(vehicle.getStatus()) ? "selected" : "" %>>Unavailable</option>
  </select><br>

  <input type="submit" value="Update Vehicle">
</form>

</body>
</html>
