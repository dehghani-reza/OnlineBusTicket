package ir.mctab.onlinebusticket.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName = "securityFilter" , urlPatterns = "/secure/*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        if(req.getSession(false)!=null&&req.getSession(false).getAttribute("security")!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    @Override
    public void destroy() {

    }
}
