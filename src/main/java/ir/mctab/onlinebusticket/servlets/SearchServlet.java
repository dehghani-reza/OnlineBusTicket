package ir.mctab.onlinebusticket.servlets;

import ir.mctab.onlinebusticket.entities.Trip;
import ir.mctab.onlinebusticket.repositories.TripDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "SearchTicket", urlPatterns = "/searchTicket")
public class SearchServlet extends HttpServlet {

    TripDAO tripDAO = new TripDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tripId = Long.valueOf(req.getParameter("trip_id"));
        HttpSession session = req.getSession(true);
        session.setAttribute("trip_id",tripId);
        resp.sendRedirect("buy_form.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String initPoint = req.getParameter("initPoint");
        String destination = req.getParameter("destination");
        List<Trip> trips = tripDAO.load(date, initPoint, destination);
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        table {
                            font-family: arial, sans-serif;
                            border-collapse: collapse;
                            width: 100%;
                        }

                        td, th {
                            border: 1px solid #dddddd;
                            text-align: left;
                            padding: 8px;
                        }

                        tr:nth-child(even) {
                            background-color: #dddddd;
                        }
                    </style>
                </head>
                <body style="background-color: darkcyan">

                <h2>Trip Table</h2>
                <h4>""" + initPoint + " to " + destination + """
                </h4>
                <h4>""" + date + """
                </h4>
                <table>
                    <tr>
                        <th>TripId</th>
                        <th>Start Time</th>
                        <th>capacity</th>
                        <th>Buy</th>
                    </tr>""" +
                htm(trips)
                + """
                </table>

                </body>
                </html>
                """);
    }

    static String htm(List<Trip> tripList) {
        tripList.sort((o1, o2) -> {
            if (LocalTime.parse(o1.getTime()).isBefore(LocalTime.parse(o2.getTime()))) {
                return -1;
            }
            if (LocalTime.parse(o1.getTime()).isAfter(LocalTime.parse(o2.getTime()))) {
                return +1;
            }
            return 0;
        });
        String s = "";
        for (Trip trip : tripList) {
            s = s.concat("""
                    <tr>
                    <td>""" +
                    trip.getId()
                    + """
                    </td>
                    <td>""" +
                    trip.getTime()
                    + """
                    </td>
                    <td>""" +
                    trip.getCapacity()
                    + """
                    </td>
                    <td>""");
            s = s.concat("<a href='searchTicket?action=post&trip_id="+trip.getId()+"'>Buy</a>");
            s = s.concat("""
                    </td>
                    </tr>
                    """);
        }
        return s;
    }
}
