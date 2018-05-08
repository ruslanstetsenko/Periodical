<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form name="loginForm" method="get" action="controller">
        <input type="hidden" name="command" value="okLogin">
        Login:
        <input type="text" name="login" placeholder="login"><br/>
        Password:
        <input type="password" name="password" placeholder="password"><br/>
        ${errorLoginMessage}<br/>
        ${incorrectActio}<br/>
        ${nullPage}<br/>
        <input type="submit" value="Log in">
    </form>

    <form name="loginForm1" method="get" action="controller">
        <input type="hidden" name="command" value="cancelLogin">
        <input type="submit" value="Cancel">
    </form>
    <hr/>
</body>
</html>
