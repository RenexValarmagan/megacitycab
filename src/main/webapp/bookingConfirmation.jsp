<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmation</title>
</head>
<body>
<h1>Booking Confirmation</h1>

<h2>Booking Details</h2>
<table border="1">
    <tr>
        <td>Order Number:</td>
        <td>${orderNumber}</td>
    </tr>
    <tr>
        <td>Customer Name:</td>
        <td>${customerName}</td>
    </tr>
    <tr>
        <td>Customer Phone:</td>
        <td>${customerPhone}</td>
    </tr>
    <tr>
        <td>Pickup Location ID:</td>
        <td>${pickupLocation}</td>
    </tr>
    <tr>
        <td>Drop Location ID:</td>
        <td>${dropLocation}</td>
    </tr>
    <tr>
        <td>Vehicle ID:</td>
        <td>${vehicleId}</td>
    </tr>
    <tr>
        <td>Fare:</td>
        <td>${fare}</td>
    </tr>
</table>

<h2>Thank you for your booking!</h2>

<!-- Button to go back to the customer dashboard -->
<a href="customer-dashboard.jsp">
    <button>Back to Dashboard</button>
</a>

</body>
</html>
