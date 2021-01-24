package ru.itis.javalab.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javalab.repositories.AuthRepositories;
import ru.itis.javalab.repositories.AuthRepositoriesImpl;
import ru.itis.javalab.repositories.UserRepository;
import ru.itis.javalab.repositories.UserRepositoryJdbcImpl;
import ru.itis.javalab.services.CookieService;
import ru.itis.javalab.services.CookieServiceImpl;
import ru.itis.javalab.services.UserService;
import ru.itis.javalab.services.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CustomServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        servletContext.setAttribute("dataSource", dataSource);

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserServiceImpl(userRepository);
        servletContext.setAttribute("userService", userService);

        AuthRepositories authRepositories= new AuthRepositoriesImpl(dataSource);
        CookieService cookieService = new CookieServiceImpl(authRepositories);
        servletContext.setAttribute("cookieService", cookieService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
