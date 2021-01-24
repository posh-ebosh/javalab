package ru.itis.javalab.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.AuthService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;
    private PasswordEncoder passwordEncoder;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Assert.notNull(login, "Login is empty");
        Assert.notNull(password, "Password is empty");

        try {
            // ищем пользователя
            User user = authService.login(login);
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("Incorrect password");
            };
            // пользователь нашёлся - кладём в сессию
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);

            resp.sendRedirect("/profile");
        } catch (IllegalArgumentException e) {
            // пользователь не нашёлся с таким логином и паролем
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.authService = (AuthService) config.getServletContext()
                .getAttribute("authService");
        this.passwordEncoder = (PasswordEncoder) config.getServletContext()
                .getAttribute("passwordEncoder");
    }
}
