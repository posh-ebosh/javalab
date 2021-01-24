package ru.itis.javalab.services;

import ru.itis.javalab.repositories.AuthRepositories;


import javax.servlet.http.Cookie;
import java.util.List;
import java.util.UUID;

public class CookieServiceImpl implements CookieService{
    AuthRepositories authRepositories;

    public CookieServiceImpl(AuthRepositories authRepositories){
        this.authRepositories = authRepositories;
    }

    @Override
    public boolean toCookie(String value) {
        List<Cookie> cookies = authRepositories.findByCookieValue(value);
        if(cookies.size()>0){
            return true;
        }else {
            return false;
        }

    }
}
