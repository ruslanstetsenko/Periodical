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
<fmt:setBundle basename="pagecontent" var="rb"/>


<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/header.css' %>
    </style>
</head>
<body>

<div class="main_header">
    <form name="setLocaleEN" method="get" action="controller">
        <input type="hidden" name="command" value="setLocale">
        <c:set var="currentPage" value="${currentPage}" scope="request"/>
        <input type="hidden" name="currentPage" value="${currentPage}">
        <button class="change_locale" type="submit" name="locale" value="1">English</button>
        <button class="change_locale" type="submit" name="locale" value="2">Українська</button>
    </form>

    <form name="logout" method="get" action="logout">
        <input type="hidden" name="command" value="logout">
        <button class="change_locale" type="submit">
            <fmt:message key="button.logout" bundle="${rb}"/>
        </button>
    </form>
</div>

</body>
</html>
