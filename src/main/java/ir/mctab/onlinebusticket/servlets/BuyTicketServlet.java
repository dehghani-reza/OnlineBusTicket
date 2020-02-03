package ir.mctab.onlinebusticket.servlets;

import ir.mctab.onlinebusticket.entities.Gender;
import ir.mctab.onlinebusticket.entities.Ticket;
import ir.mctab.onlinebusticket.entities.Trip;
import ir.mctab.onlinebusticket.entities.User;
import ir.mctab.onlinebusticket.repositories.TicketDao;
import ir.mctab.onlinebusticket.repositories.TripDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "buyServlet" , urlPatterns = "/secure/buy_servlet")
public class BuyTicketServlet extends HttpServlet {
    TripDAO tripDAO = new TripDAO();
    TicketDao ticketDao = new TicketDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        HttpSession session = req.getSession(false);
        Long trip_id = Long.valueOf(String.valueOf(session.getAttribute("trip_id")));
        User user = (User) session.getAttribute("security");
        Ticket ticket = new Ticket();
        Trip trip = tripDAO.loadById(trip_id);
        ticket.setTrip(trip);
        ticket.setUser(user);
        ticket.setBuyerName(name);
        Gender ticketGender = Gender.Man;
        if (gender.equals("Woman")){
            ticketGender = Gender.Woman;
        }
        ticket.setBuyerGender(ticketGender);
        ticketDao.save(ticket);
        Ticket ticket1 = ticketDao.findByBuyer(name,user,trip_id);
        req.setAttribute("ticket",ticket1.getTicketId());
        req.getRequestDispatcher("show_ticket").forward(req, resp);
    }
}
