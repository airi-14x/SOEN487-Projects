<%-- 
    Document   : login
    Created on : 2020-03-27, 10:21:39
    Author     : jasminelatendresse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="Login" method="POST">
            <label for="username">Username</label>
            <input type="text" name="username" required />
            <label for="password">Password</label>
            <input type="password" name="password" required />
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
