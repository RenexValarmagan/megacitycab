<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>MegaCityCab - Welcome</title>
  <style>
    /* Reset default browser styles */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    /* Basic Body Styling */
    body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f7fa;
      color: #333;
      display: flex;
      flex-direction: column;
      height: 100%;
      overflow-x: hidden;
    }

    /* Navigation Bar Styling */
    .navbar {
      background-color: #333;
      color: white;
      padding: 15px 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      position: fixed;
      width: 100%;
      top: 0;
      z-index: 100;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    .navbar a {
      color: white;
      text-decoration: none;
      font-size: 16px;
      font-weight: bold;
      padding: 10px 15px;
      border-radius: 4px;
      transition: background-color 0.3s;
    }

    .navbar a:hover {
      background-color: #4CAF50;
    }

    /* Container for Left Image + Right Content */
    .main-container {
      display: flex;
      height: 100%;
      overflow: auto; /* Make the container scrollable */
    }

    /* Left Side - Image Section */
    .image-container {
      flex: 1;
      background-image: url('source/landingpage.jpg'); /* Add your image path */
      background-size: cover;
      background-position: center;
      height: 100vh;
    }

    /* Right Side - Landing Page Content */
    .landing-container {
      flex: 1;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 0 20px;
      text-align: center;
      height: 100vh; /* Full viewport height */
      overflow: auto; /* Ensure scrolling within this section */
    }

    .landing-content {
      width: 100%;
      max-width: 500px;
    }

    .landing-container h1 {
      font-size: 36px;
      margin-bottom: 20px;
      font-weight: bold;
    }

    .landing-container p {
      font-size: 18px;
      margin-bottom: 20px;
    }

    .contact-details {
      font-size: 16px;
      color: #555;
    }

    /* Login Section Styling */
    .login-section {
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #f4f7fa;
      padding: 50px 20px;
      flex-direction: column;
      margin-top: 20px;
    }

    .login-container {
      background-color: #ffffff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
    }

    .login-container h2 {
      text-align: center;
      margin-bottom: 20px;
      font-size: 24px;
    }

    .login-container input {
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 14px;
      outline: none;
      transition: border 0.3s ease;
    }

    .login-container input[type="text"]:focus,
    .login-container input[type="password"]:focus {
      border-color: #4CAF50;
    }

    .login-container input[type="submit"] {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 12px;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .login-container input[type="submit"]:hover {
      background-color: #45a049;
    }

    .login-container p {
      text-align: center;
      margin-top: 15px;
      font-size: 14px;
    }

    .login-container a {
      color: #4CAF50;
      text-decoration: none;
      font-weight: bold;
    }

    .login-container a:hover {
      text-decoration: underline;
    }

    .error-message {
      color: red;
      text-align: center;
      margin-bottom: 15px;
    }

    /* Responsive Design */
    @media (max-width: 768px) {
      .navbar {
        flex-direction: column;
        text-align: center;
      }

      .main-container {
        flex-direction: column;
        height: auto;
      }

      .image-container {
        display: none;
      }

      .landing-container {
        padding-top: 20px;
      }

      .login-section {
        padding: 20px;
      }
    }

    /* Smooth Scroll */
    html {
      scroll-behavior: smooth;
    }
  </style>
</head>
<body>

<!-- Navigation Bar -->
<div class="navbar">
  <a href="#landing-page">Home</a>
  <a href="#login-section">Login</a> <!-- Links to the Login Section -->
  <a href="help.jsp">Help</a> <!-- Takes the user to help.jsp -->
</div>

<!-- Main Container (Left Image + Right Content) -->
<div class="main-container">
  <!-- Left Side - Image Section -->
  <div class="image-container"></div>

  <!-- Right Side - Landing Page Content -->
  <div class="landing-container" id="landing-page">
    <div class="landing-content">
      <h1>Welcome to MegaCityCab</h1>
      <p>Your fast and reliable transportation solution in Colombo City!</p>
      <p>We provide safe and affordable rides with various vehicle options for your convenience. Whether you're looking for a quick ride to your destination or a comfortable journey, we've got you covered.</p>
      <div class="contact-details">
        <p><strong>Contact Us:</strong></p>
        <p>Phone: +94 123 456 789</p>
        <p>Email: support@megacitycab.com</p>
        <p>Address: 123 MegaCity Road, Colombo, Sri Lanka</p>
      </div>
    </div>
  </div>
</div>

<!-- Login Section -->
<div class="login-section" id="login-section">
  <div class="login-container">
    <h2>Welcome to MegaCityCab Please Log in</h2>

    <% if (request.getParameter("error") != null) { %>
    <p class="error-message">Invalid username or password. Please try again.</p>
    <% } %>

    <form action="login" method="post">
      <label>Username:</label>
      <input type="text" name="username" required><br>

      <label>Password:</label>
      <input type="password" name="password" required><br>

      <input type="submit" value="Login">
    </form>

    <p>New Customer? <a href="register.jsp">Register Here</a></p>
  </div>
</div>

</body>
</html>
