<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 23.01.2021
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/login">
    <label for="login">Login: </label>
    <input id="login" type="text" name="login">

    <label for="password">Password: </label>
    <input id="password" name="password" type="password">

    <input type="submit" value="LogIn">
</form>
</body>
</html>
