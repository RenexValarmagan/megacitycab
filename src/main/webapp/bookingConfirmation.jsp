<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmation</title>
    <style>
        /* General styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h1, h2 {
            color: #3c3c3c;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 10px;
        }

        h2 {
            font-size: 22px;
            margin-bottom: 20px;
        }

        /* Table styling */
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 20px;
            text-align: left;
            font-size: 16px;
        }

        th {
            background-color: #5c6bc0;
            color: white;
        }

        td {
            background-color: #ffffff;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* Button styling */
        button {
            background-color: #3f51b5;
            color: white;
            padding: 12px 18px;
            font-size: 16px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #5c6bc0;
        }

        /* Centering content */
        .container {
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
        }

        /* Responsive Design */
        @media screen and (max-width: 768px) {
            table {
                font-size: 14px;
            }

            .container {
                padding: 10px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Booking Confirmation</h1>

    <h2>Booking Details</h2>
    <table>
        <tr>
            <th>Order Number</th>
            <td>${orderNumber}</td>
        </tr>
        <tr>
            <th>Customer Name</th>
            <td>${customerName}</td>
        </tr>
        <tr>
            <th>Customer Phone</th>
            <td>${customerPhone}</td>
        </tr>
        <tr>
            <th>Pickup Location ID</th>
            <td>${pickupLocation}</td>
        </tr>
        <tr>
            <th>Drop Location ID</th>
            <td>${dropLocation}</td>
        </tr>
        <tr>
            <th>Vehicle ID</th>
            <td>${vehicleId}</td>
        </tr>
        <tr>
            <th>Fare</th>
            <td>${fare}</td>
        </tr>
    </table>

    <h2>Thank you for your booking!</h2>

    <!-- Button to go back to the customer dashboard -->
    <a href="customer-dashboard.jsp">
        <button>Back to Dashboard</button>
    </a>
</div>

</body>
</html>
