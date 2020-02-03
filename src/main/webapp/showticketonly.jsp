<%@ page import="ir.mctab.onlinebusticket.repositories.TicketDao" %>
<%@ page import="ir.mctab.onlinebusticket.entities.Ticket" %><%--
  Created by IntelliJ IDEA.
  User: Reza
  Date: 2/3/2020
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ticket info</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%
    Long tripId = Long.valueOf(request.getParameter("trip_id"));
    TicketDao ticketDao = new TicketDao();
    Ticket ticket = ticketDao.loadById(tripId);
%>
<table>
    <tr>
        <th colspan="2">
            bus Ticket
        </th>
    </tr>
    <tr>
        <td>ticket id</td>
        <td><%=ticket.getTicketId()%></td>
    </tr>
    <tr>
        <td>name</td>
        <td><%=ticket.getBuyerName()%></td>
    </tr>
    <tr>
        <td>Gender</td>
        <td><%=ticket.getBuyerGender()%></td>
    </tr>
    <tr>
        <td>initial point</td>
        <td><%=ticket.getTrip().getInitialPoint()%></td>
    </tr>
    <tr>
        <td>destination</td>
        <td><%=ticket.getTrip().getDestination()%></td>
    </tr>
    <tr>
        <td>date</td>
        <td><%=ticket.getTrip().getDate()%></td>
    </tr>
    <tr>
        <td>time</td>
        <td><%=ticket.getTrip().getTime()%></td>
    </tr>
    <tr>
        <td>trip id</td>
        <td><%=ticket.getTrip().getId()%></td>
    </tr>
    <tr>
        <td colspan="2"><a href='cancel.jsp?trip_id=<%=ticket.getTicketId()%>'>cancel</a></td>
    </tr>
</table>
</body>
</html>
