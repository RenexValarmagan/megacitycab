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
            color: #333;
            text-align: center;
            margin-top: 20px;
        }

        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
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
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            padding: 15px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .form-container input[type="submit"]:hover {
            background-color: #45a049;
        }

        .form-container select {
            background-color: #f9f9f9;
        }

        .form-container input:focus,
        .form-container select:focus {
            outline: none;
            border-color: #4CAF50;
        }

        /* Responsive Design */
        @media screen and (max-width: 768px) {
            .form-container {
                padding: 15px;
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

<h2>Edit Driver</h2>

<div class="form-container">
    <form action="DriverServlet" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="driverId" value="<%= driver.getId() %>">

        <label for="name">Name:</label>
        <input type="text" name="name" value="<%= driver.getName() %>" required>

        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="<%= driver.getPhone() %>" required>

        <label for="status">Status:</label>
        <select name="status">
            <option value="Active" <%= "Available".equals(driver.getStatus()) ? "selected" : "" %>>Available</option>
            <option value="Inactive" <%= "On Duty".equals(driver.getStatus()) ? "selected" : "" %>>On Duty</option>
        </select>

        <label for="vehicle">Vehicle:</label>
        <select name="vehicle_id">
            <option value="0" <%= driver.getVehicleId() == 0 ? "selected" : "" %>>No Vehicle</option>
            <% for (Vehicle vehicle : vehicles) { %>
            <option value="<%= vehicle.getId() %>" <%= vehicle.getId() == driver.getVehicleId() ? "selected" : "" %>><%= vehicle.getType() %></option>
            <% } %>
        </select>

        <input type="submit" value="Update Driver">
    </form>
</div>

</body>
</html>
