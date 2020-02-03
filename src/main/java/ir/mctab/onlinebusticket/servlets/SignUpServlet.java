package ir.mctab.onlinebusticket.servlets;

import ir.mctab.onlinebusticket.entities.Gender;
import ir.mctab.onlinebusticket.entities.User;
import ir.mctab.onlinebusticket.repositories.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "SignUp", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Long nationalId = Long.valueOf(req.getParameter("nationalId"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNationalId(nationalId);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(Gender.valueOf(gender));
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.save(user);
        } catch (Exception e) {
            resp.sendRedirect("error.html");
            return;
        }
        PrintWriter out = resp.getWriter();
        resp.sendRedirect("index.html");

    }
}
