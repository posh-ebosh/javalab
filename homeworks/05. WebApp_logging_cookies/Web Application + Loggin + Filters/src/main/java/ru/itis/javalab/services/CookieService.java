package ru.itis.javalab.services;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.UUID;

public interface CookieService {
    boolean toCookie(String value);
}
