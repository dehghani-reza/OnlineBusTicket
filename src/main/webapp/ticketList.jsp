<%@ page import="ir.mctab.onlinebusticket.repositories.TicketDao" %>
<%@ page import="ir.mctab.onlinebusticket.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ir.mctab.onlinebusticket.entities.Ticket" %><%--
  Created by IntelliJ IDEA.
  User: Reza
  Date: 2/3/2020
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket List</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%
    User user = (User) session.getAttribute("security");
    TicketDao ticketDao = new TicketDao();
    List<Ticket> tickets = ticketDao.findByUser(user);
%>
<table>
    <tr>
        <th>date</th>
        <th>ticketId</th>
        <th>choose</th>
    </tr>
    <%
        for (Ticket ticket : tickets) { %>
    <tr>
        <td><%=ticket.getTrip().getDate()%>
        </td>
        <td><%=ticket.getTicketId()%>
        </td>
        <td><a href='showticketonly.jsp?trip_id=<%=ticket.getTicketId()%>'>show more</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
