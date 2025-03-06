<%@ page import="com.bsc.megacitycab.models.Customer" %>
<%@ page session="true" %>
<%
    // Retrieve the logged-in customer from the session
    Customer loggedInCustomer = (Customer) session.getAttribute("customer");
    if (loggedInCustomer == null) {
        response.sendRedirect("index.jsp");  // If not logged in, redirect to login page
        return;
    }
%>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
<h2>Welcome, <%= loggedInCustomer.getUsername() %>!</h2>
<p>Your Role: customer</p>
<a href="logout">Logout</a>
</body>
</html>
