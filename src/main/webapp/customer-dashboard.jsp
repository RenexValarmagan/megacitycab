<%@ page session="true" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !"customer".equals(role)) {
        response.sendRedirect("index.jsp?error=unauthorized");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
<h2>Welcome, Customer!</h2>
<p>Book and view your rides here.</p>
<a href="logout">Logout</a>
</body>
</html>
