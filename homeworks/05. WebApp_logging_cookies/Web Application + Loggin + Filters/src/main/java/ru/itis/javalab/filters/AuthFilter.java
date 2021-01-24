package ru.itis.javalab.filters;

import ru.itis.javalab.services.CookieService;
import ru.itis.javalab.services.CookieServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    private CookieService cookieService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.cookieService = (CookieService) servletContext.getAttribute("cookieService");


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();

        //посмотреть есть ли в реквесте куки с именем auth
        // взять куки из реквеста
        //взять куки из бд
        // если есть совпадение то пропукаем
        // иначе на логин
        Cookie cookieAuth = null;
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("auth")){
                cookieAuth = cookie;
            }
        }
        if (cookieAuth != null && cookieService.toCookie(cookieAuth.getValue())){
            response.sendRedirect("/profile");
        }else {
            response.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }
}
