<%@ page import="com.bsc.megacitycab.models.Customer" %>
<%@ page session="true" %>
<%
    // Retrieve the logged-in customer from the session
    Customer loggedInCustomer = (Customer) session.getAttribute("customer");

    if (loggedInCustomer == null) {
        response.sendRedirect("index.jsp");  // Redirect if not logged in
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
</head>
<body>
<h2>Welcome, <%= loggedInCustomer.getUsername() %>!</h2>
<p>Need a Cab? <a href="<%= request.getContextPath() %>/booking.jsp">Book Your Cab</a></p>
<a href="<%= request.getContextPath() %>/logout">Logout</a>
</body>
</html>
