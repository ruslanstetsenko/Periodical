<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create publication</title>
</head>
<body>
<div>
    <form name="createPublication" method="post" action="controller">
        <input type="hidden" name="command" value="createPublication">
        <input type="text" name="name" placeholder="name">
        <input type="radio" name="periodicalType">
        <input type="radio" name="periodicalTheme">
        <input type="text" name="ISSN" placeholder="ISSN">
        <input type="date" name="periodicalRegister">
        <input type="text" name="website" placeholder="website">
        <hr>
        <p>Periodicy cost</p>
        <p>per 1 months</p><input type="text" name="cost1month">
        <p>per 3 months</p><input type="text" name="cost3month">
        <p>per 6 months</p><input type="text" name="cost6month">
        <p>per 12 months</p><input type="text" name="cost12month">
    </form>
</div>


<form name="cancelCreatePub" method="get" action="controller">
    <input type="hidden" name="command" value="cancelCreatePublication">
</form>
</body>
</html>
