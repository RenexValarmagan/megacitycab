<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%
  String role = (String) session.getAttribute("role");
  if (role == null || !"admin".equals(role)) {
    response.sendRedirect("index.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard - MegaCityCab</title>
</head>
<body>
<h2>Welcome, Admin!</h2>
<p>You are logged in as an administrator.</p>

<ul>
  <li><a href="manage-users.jsp">Manage Users</a></li>
  <li><a href="manage-bookings.jsp">Manage Bookings</a></li>
  <li><a href="manage-cabs.jsp">Manage Cabs</a></li>
  <li><a href="logout">Logout</a></li>
</ul>
</body>
</html>
