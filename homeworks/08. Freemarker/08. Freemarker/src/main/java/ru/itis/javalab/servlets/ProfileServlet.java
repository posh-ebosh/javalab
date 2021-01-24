package ru.itis.javalab.servlets;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.javalab.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");

        configuration.setTemplateLoader(new FileTemplateLoader(new File("D:\\Projects\\Simple WebApp\\src\\main\\resource")));
        Template template = configuration.getTemplate("Profile.ftlh");

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("login", userService.getUserByLogin(req.getParameter("login")).getLogin());
        attributes.put("id", userService.getUserByLogin(req.getParameter("login")).getId());
        try {
            template.process(attributes, resp.getWriter());
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
