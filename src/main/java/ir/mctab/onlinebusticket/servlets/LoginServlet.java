package ir.mctab.onlinebusticket.servlets;

import ir.mctab.onlinebusticket.entities.User;
import ir.mctab.onlinebusticket.repositories.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = null;
        try {
            user = userDAO.loadByEmailPass(email, password);
        } catch (Exception e) {
            resp.sendRedirect("error.html");
            return;
        }

        if (user != null) {
            out.println("""
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>Hello</title>
                    </head>
                    <body style="background-color: darkcyan">
                    <p>hello</P>
                    """ +
                    user
                    + """
                    </body>
                    </html>
                                    """);
        }
    }
}
