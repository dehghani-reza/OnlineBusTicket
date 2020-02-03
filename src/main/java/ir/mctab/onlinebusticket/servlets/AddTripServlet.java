package ir.mctab.onlinebusticket.servlets;

import ir.mctab.onlinebusticket.entities.Trip;
import ir.mctab.onlinebusticket.repositories.TripDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addTrip", urlPatterns = "/secure/addtrip")
public class AddTripServlet extends HttpServlet {
    TripDAO tripDAO = new TripDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String initPoint = req.getParameter("initPint");
        String destination = req.getParameter("destination");
        String time = req.getParameter("time");
        Long capacity = Long.valueOf(req.getParameter("capacity"));

        Trip trip = new Trip();
        trip.setDate(date);
        trip.setDestination(destination);
        trip.setInitialPoint(initPoint);
        trip.setCapacity(capacity);
        trip.setTime(time);
        tripDAO.save(trip);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("successful");
        resp.sendRedirect("SearchTicket.html");

    }
}
