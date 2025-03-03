<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Registration</title>
</head>
<body>
<h2>Customer Registration</h2>

<% if (request.getParameter("error") != null) { %>
<p style="color:red;">Registration failed. Please try again.</p>
<% } %>

<% if (request.getParameter("success") != null) { %>
<p style="color:green;">Registration successful! You can now <a href="index.jsp">login</a>.</p>
<% } %>

<form action="register" method="post">
    <label>Username:</label>
    <input type="text" name="username" required><br>

    <label>Password:</label>
    <input type="password" name="password" required><br>

    <input type="hidden" name="role" value="customer">  <%-- Auto-assign "customer" role --%>

    <input type="submit" value="Register">
</form>
</body>
</html>
