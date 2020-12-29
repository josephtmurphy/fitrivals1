<%-- 
    Document   : login
    Created on : 29-Dec-2020, 11:44:06
    Author     : josep
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>FitRivals Login</title>
</head>
<body>
    <div style="text-align: center">
        <h1>Login</h1>
        <form action="UserLoginServlet" method="post">
            <label for="email">Email:</label>
            <input name="email" size="30" />
            <br><br>
            <label for="password">Password:</label>
            <input type="password" name="password" size="30" />
            <br>${message}
            <br><br>           
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>