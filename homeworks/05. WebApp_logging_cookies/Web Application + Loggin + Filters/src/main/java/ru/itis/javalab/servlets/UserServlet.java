package ru.itis.javalab.servlets;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;
import ru.itis.javalab.repositories.UserRepositoryJdbcImpl;
import ru.itis.javalab.services.UserService;
import ru.itis.javalab.services.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.userService  = (UserService) servletContext.getAttribute("userService");


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .id(1L)
                .firstName("Марсель")
                .lastName("Сидиков")
                .age(26)
                .build());
        users.add(User.builder()
                .id(2L)
                .firstName("Расим")
                .lastName("Гарипов")
                .age(19)
                .build());
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookieAuth = new Cookie("auth", "a1709ed2-5c2e-11eb-ae93-0242ac130002" );
        response.addCookie(cookieAuth);



    }


}



