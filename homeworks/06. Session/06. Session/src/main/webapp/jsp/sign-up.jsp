<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 24.01.2021
  Time: 0:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Registration
    </title>
</head>
<body>
<form method="post" action="/sign-up">
    <label for="login">Login</label>
    <input id="login" name="login" type="text">

    <label for="password">Password</label>
    <input id="password" name="password" type="password">

    <input type="submit" value="SignUp">
</form>
</body>
</html>
