<%@ page session="true" %>
<%@ page import="com.bsc.megacitycab.models.User" %>

<%
    User loggedInUser = (User) session.getAttribute("user");
    if (loggedInUser == null || !"customer".equals(loggedInUser.getRole())) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
<h2>Welcome, <%= loggedInUser.getUsername() %>!</h2>

<p>Your Role: <%= loggedInUser.getRole() %></p>

<!-- Button to Book a Cab -->
<form action="booking.jsp">
    <button type="submit">Book a Cab</button>
</form>

<br>

<a href="logout">Logout</a>
</body>
</html>
