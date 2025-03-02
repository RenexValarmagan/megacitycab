<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !"customer".equals(role)) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard - MegaCityCab</title>
</head>
<body>
<h2>Welcome, Customer!</h2>
<p>You are logged in as a customer.</p>

<ul>
    <li><a href="book-ride.jsp">Book a Ride</a></li>
    <li><a href="view-bookings.jsp">View My Bookings</a></li>
    <li><a href="logout">Logout</a></li>
</ul>
</body>
</html>
