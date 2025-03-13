<%@ page import="com.bsc.megacitycab.models.Driver" %>
<%@ page import="com.bsc.megacitycab.dao.DriverDAO" %>
<%@ page import="com.bsc.megacitycab.models.Vehicle" %>
<%@ page import="com.bsc.megacitycab.dao.VehiclesDAO" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

<%
    String driverIdParam = request.getParameter("driverId");

    // Debugging logs
    System.out.println("Received driverId: " + driverIdParam);
    System.out.println("<p>Debug: Received driverId = " + driverIdParam + "</p>");

    if (driverIdParam == null || driverIdParam.trim().isEmpty()) {
        throw new IllegalArgumentException("Driver ID is required.");
    }

    int driverId = Integer.parseInt(driverIdParam);
    Driver driver = DriverDAO.getDriverById(driverId);

    if (driver == null) {
        throw new IllegalArgumentException("Driver not found for ID: " + driverId);
    }

    List<Vehicle> vehicles = VehiclesDAO.getAllVehicles();
%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Driver</title>
</head>
<body>
<h2>Edit Driver</h2>
<form action="DriverServlet" method="post">
    <%--@declare id="vehicle"--%><%--@declare id="status"--%><%--@declare id="phone"--%><%--@declare id="name"--%>
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="driverId" value="<%= driver.getId() %>">

    <label for="name">Name:</label><br>
    <input type="text" name="name" value="<%= driver.getName() %>" required><br>

    <label for="phone">Phone:</label><br>
    <input type="text" name="phone" value="<%= driver.getPhone() %>" required><br>

    <label for="status">Status:</label><br>
    <select name="status">
        <option value="Active" <%= "Available".equals(driver.getStatus()) ? "selected" : "" %>>Available</option>
        <option value="Inactive" <%= "On Duty".equals(driver.getStatus()) ? "selected" : "" %>>On Duty</option>
    </select><br>

    <label for="vehicle">Vehicle:</label><br>
    <select name="vehicle_id">
        <option value="0" <%= driver.getVehicleId() == 0 ? "selected" : "" %>>No Vehicle</option>
        <% for (Vehicle vehicle : vehicles) { %>
        <option value="<%= vehicle.getId() %>" <%= vehicle.getId() == driver.getVehicleId() ? "selected" : "" %>><%= vehicle.getType() %></option>
        <% } %>
    </select><br>

    <input type="submit" value="Update Driver">
</form>

</body>
</html>
