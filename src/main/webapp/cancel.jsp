<%@ page import="ir.mctab.onlinebusticket.repositories.TicketDao" %><%--
  Created by IntelliJ IDEA.
  User: Reza
  Date: 2/4/2020
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cancel ticket</title>
</head>
<body>
<%
    TicketDao ticketDao = new TicketDao();
    Long tripId = Long.valueOf(request.getParameter("trip_id"));
    try {
        ticketDao.deleteById(tripId);
    } catch (Exception e) {
        response.sendError(500);
    }
%>
<h1>ticket cancel successfully</h1>
</body>
</html>
