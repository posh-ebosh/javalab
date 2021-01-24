package ru.itis.javalab.servlets;

import org.springframework.util.Assert;
import ru.itis.javalab.services.AuthService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up.ftl")
public class SignUpServlet extends HttpServlet {

    private AuthService authService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/sign-up.ftl.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Assert.notNull(login, "Login is empty");
        Assert.notNull(password, "Password is empty");

        authService.signUp(login, password);
        resp.sendRedirect("/login");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.authService = (AuthService) config.getServletContext().getAttribute("authService");
    }
}
