<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<c:if test="${locale == 1}"><fmt:setLocale value="en_US" scope="session"/></c:if>
<c:if test="${locale == 2}"><fmt:setLocale value="uk_UA" scope="session"/></c:if>
<fmt:setBundle basename="pagecontent" var="rb"/>
<fmt:setBundle basename="message" var="validation"/>

<html>
<head>
    <c:set var="currentPage" value="path.page.login" scope="request"/>
    <title><fmt:message key="login.title" bundle="${rb}"/></title>
    <%--<link rel="stylesheet" type="text/css" href="css/login.css"/>--%>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/login.css' %>
    </style>
</head>
<body class="login_body">
<header class="header">
    <c:import url="headUserInfo.jsp"/>
</header>

<article class="login_form">
    <form name="loginForm" method="post" action="create_user">
        <div class="login_form_element">
            <p class="input_elem_text">
                <fmt:message key="login.login" bundle="${rb}"/>
            </p>
            <input class="login_input" type="text" name="login" placeholder="login">
        </div>

        <div class="login_form_element">
            <p class="input_elem_text">
                <fmt:message key="login.password" bundle="${rb}"/>
            </p>
            <input class="login_input" type="password" name="password" placeholder="password">
        </div>

        <div class="login_form_element">
            <button class="login_button" type="submit" name="command" value="okLogin">
                <fmt:message key="login.okLogin" bundle="${rb}"/>
            </button>
            <button class="login_button" type="submit" name="command" value="createUser">
                <fmt:message key="aboutUser.createUser" bundle="${rb}"/>
            </button>
        </div>

        <div class="login_errors">
            <c:if test="${errorLoginMessage}">
                <fmt:message key="message.errorLogin" bundle="${validation}"/>
            </c:if>
            <c:if test="${dublicateAccount}">
                <fmt:message key="message.dublicateAccount" bundle="${validation}"/>
            </c:if>
            <c:if test="${nullPage}">
                <fmt:message key="message.nullPage" bundle="${validation}"/>
            </c:if>
            <c:if test="${incorectLogin}">
                <fmt:message key="validation.login" bundle="${validation}"/>
            </c:if><br/>
            <c:if test="${incorectPassword}">
                <fmt:message key="validation.password" bundle="${validation}"/>
            </c:if>

        </div>
    </form>

</article>
<%--<footer>--%>
    <%--<a href="error.jsp">error</a>--%>
<%--</footer>--%>

</body>
</html>
