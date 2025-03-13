<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Help - MegaCityCab</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fa;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1, h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        h3 {
            margin-top: 20px;
        }

        p {
            font-size: 16px;
            line-height: 1.6;
            color: #555;
        }

        .steps {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        .steps li {
            background-color: #eaf2f1;
            border-radius: 6px;
            padding: 15px;
            margin-bottom: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .steps img {
            width: 100%;
            max-width: 500px;
            border-radius: 6px;
            margin-top: 10px;
        }

        .steps ol {
            padding-left: 20px;
        }

        .back-button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            margin-top: 20px;
            transition: background-color 0.3s;
        }

        .back-button:hover {
            background-color: #45a049;
        }

        .steps a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        .steps a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Help Guide</h1>
    <h2>Step-by-Step Guide to Using MegaCityCab</h2>

    <h3>Welcome to MegaCityCab's Help Page!</h3>
    <p>This guide will walk you through the process of registering, logging in, and making a booking. Follow these simple steps, and you'll be all set to use our service!</p>

    <h3>Step 1: Register an Account</h3>
    <p>To begin using MegaCityCab, you'll need to register for an account. Follow the steps below:</p>
    <div class="steps">
        <ol>
            <li>Visit the <a href="register.jsp">registration page</a>.</li>
            <li>Fill out the registration form with your details, including your name, email, and phone number.</li>
            <li>Click on the "Register" button to create your account.</li>
        </ol>
        <p>Example Screenshot:</p>
        <img src="source/cr.png" alt="Registration Step Screenshot">
    </div>

    <h3>Step 2: Log in to Your Account</h3>
    <p>After registering, you can log in to your MegaCityCab account:</p>
    <div class="steps">
        <ol>
            <li>Go to the <a href="login.jsp">login page</a>.</li>
            <li>Enter your username and password that you registered with.</li>
            <li>Click the "Login" button to access your account.</li>
        </ol>
        <p>Example Screenshot:</p>
        <img src="source/loginu.png" alt="Login Step Screenshot">
    </div>

    <h3>Step 3: Make a Booking</h3>
    <p>Once you're logged in, it's time to make a booking! Follow these steps:</p>
    <div class="steps">
        <ol>
            <li>Click on the "Book a Ride" button from your dashboard.</li>
            <li>Choose your pickup location and drop-off location on the map.</li>
            <li>Select the vehicle type (e.g., car, tuk-tuk, van) for your ride.</li>
            <li>Review your booking details and click "Confirm" to book your ride.</li>
            <li>You'll receive an order confirmation with details about your ride.</li>
        </ol>
        <p>Example Screenshot:</p>
        <img src="source/bkpc.png" alt="Booking Step Screenshot">
        <img src="source/bkp.png" alt="Booking Step Screenshot">
    </div>

    <h3>Need More Help?</h3>
    <p>If you encounter any issues or need further assistance, don't hesitate to contact us!</p>
    <p>You can reach us via email at <strong>support@megacitycab.com</strong> or call us at <strong>+94 123 456 789</strong>.</p>

    <a href="index.jsp" class="back-button">Back to Home</a>
</div>
</body>
</html>
