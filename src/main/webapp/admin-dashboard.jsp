<%@ page session="true" %>
<%
  String role = (String) session.getAttribute("role");
  if (role == null || !"admin".equals(role)) {
    response.sendRedirect("index.jsp?error=unauthorized");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard</title>
</head>
<body>
<h2>Welcome, Admin!</h2>
<p>Manage system users, bookings, and cabs.</p>
<a href="logout">Logout</a>
</body>
</html>
