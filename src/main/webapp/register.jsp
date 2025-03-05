<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration</title>
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
            flex-direction: row;
        }

        .register-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            margin-left: auto;
            margin-right: 20px;
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

        input[type="text"], input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            color: #333;
            outline: none;
            transition: border 0.3s ease;
        }

        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #4CAF50;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            padding: 12px;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        p {
            text-align: center;
            margin-top: 15px;
            font-size: 14px;
            color: #555;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
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

        /* Style for the image container */
        .image-container {
            flex: 1;
            background-image: url('source/register.jpg');
            background-size: cover;
            background-position: center;
            height: 100vh;
            border-radius: 8px 0 0 8px;
        }

        /* Make the layout responsive */
        @media (max-width: 768px) {
            body {
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }

            .register-container {
                margin-left: 0;
                margin-top: 20px;
            }

            .image-container {
                display: none;
            }
        }
    </style>
</head>
<body>
<div class="image-container"></div>

<div class="register-container">
    <h2>Customer Registration</h2>

    <% if (request.getParameter("error") != null) { %>
    <p class="error-message">Registration failed. Please try again.</p>
    <% } %>

    <% if (request.getParameter("success") != null) { %>
    <p class="success-message">Registration successful! You can now <a href="index.jsp">login</a>.</p>
    <% } %>

    <form action="register" method="post">
        <label>Username:</label>
        <input type="text" name="username" required><br>

        <label>Password:</label>
        <input type="password" name="password" required><br>

        <input type="hidden" name="role" value="customer">  <%-- Auto-assign "customer" role --%>

        <input type="submit" value="Register">
    </form>

    <p>Already have an account? <a href="index.jsp">Login Here</a></p>
</div>
</body>
</html>
