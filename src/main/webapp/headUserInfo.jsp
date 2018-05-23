<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 20.05.2018
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <%--<title>Title</title>--%>
</head>
<body>
<a href="Controller?command=logout">Logout</a>
<%--<h3>Вітаємо, <c:out value="${currentUser.surname}"/> <c:out value="${currentUser.name}"/> <c:out value="${currentUser.lastName}"/></h3>--%>
<%--<a href="controller?command=setLocale" name="locale">English</a>--%>
<%--<a href="controller?command=setLocale">Українська</a>--%>

<form name="setLocaleEN" method="get" action="123">
    <input type="hidden" name="command" value="setLocale">
    <c:set var="currentPage" value="${currentPage}" scope="request"/>
    <input type="hidden" name="currentPage" value="${currentPage}">
    <button type="submit" name="locale" value="1">EN</button>
</form>

<form name="setLocaleUA" method="get" action="123">
    <input type="hidden" name="command" value="setLocale">
    <c:set var="currentPage" value="${currentPage}" scope="request"/>
    <input type="hidden" name="currentPage" value="${currentPage}">
    <button type="submit" name="locale" value="2">UA</button>
</form>

<%--<select name="locale">--%>
    <%--<option value="en_us"><a href="controller?command=setLocale"></a>English</option>--%>
    <%--<option value="uk_ua"><a href="controller?command=setLocale"></a>Українська</option>--%>
<%--</select>--%>
<%--<form name="logout" method="get" action="login">--%>
<%--<input type="hidden" name="command" value="logout">--%>
<%--<input type="submit" name="cancel" value="Logout">--%>
<%--</form>--%>
</body>
</html>
