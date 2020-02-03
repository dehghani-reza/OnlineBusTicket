package ir.mctab.onlinebusticket.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "showTicket", urlPatterns = "/secure/show_ticket")
public class showTicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String gender = req.getParameter("gender");
        if(gender.equals("Man")){
            out.println("Mr.");
        }else {
            out.println("Ms.");
        }

        out.println(req.getParameter("name")+"Your ticket purchase was successful.");
        String ticket = String.valueOf(req.getAttribute("ticket"));
        out.println("ticket id="+ticket);
    }
}
