<%@ page import="com.bsc.megacitycab.dao.VehiclesDAO" %>
<%@ page session="true" %>

<%
    int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
    VehiclesDAO.deleteVehicle(vehicleId);
    response.sendRedirect("admin-dashboard.jsp"); // Redirect back to the admin dashboard after deletion
%>
