package ru.itis.javalab.repositories;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.UUID;

public interface AuthRepositories<C> extends CrudRepository<Cookie>{
    List<Cookie> findByCookieValue (String value);
}
