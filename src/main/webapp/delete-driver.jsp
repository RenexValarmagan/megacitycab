<%@ page import="com.bsc.megacitycab.dao.DriverDAO" %>
<%@ page session="true" %>

<%
    int driverId = Integer.parseInt(request.getParameter("driverId"));
    DriverDAO.deleteDriver(driverId);
    response.sendRedirect("admin-dashboard.jsp"); // Redirect back to the admin dashboard after deletion
%>
