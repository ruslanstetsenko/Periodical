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
</head>
<body>

<header>
    <c:import url="headUserInfo.jsp"/>
</header>
<div>
    <form name="loginForm" method="post" action="1234">
        <%--<input type="hidden" name="command" value="okLogin">--%>
        <fmt:message key="login.login" bundle="${rb}"/>
        <input type="text" name="login" placeholder="login"><c:if test="${incorectLogin}"><fmt:message
            key="validation.login" bundle="${validation}"/></c:if><br/>
        <fmt:message key="login.password" bundle="${rb}"/>
        <input type="password" name="password" placeholder="password"><c:if test="${incorectPassword}"><fmt:message
            key="validation.password" bundle="${validation}"/></c:if><br/>
        <c:if test="${errorLoginMessage}"><fmt:message key="message.errorLogin" bundle="${validation}"/></c:if>
        <c:if test="${dublicateAccount}"><fmt:message key="message.dublicateAccount" bundle="${validation}"/></c:if>
        <c:if test="${nullPage}"><fmt:message key="message.nullPage" bundle="${validation}"/></c:if>

        <div>
            <button type="submit" name="command" value="okLogin"><fmt:message key="login.okLogin"
                                                                              bundle="${rb}"/></button>
            <button type="submit" name="command" value="createUser"><fmt:message key="aboutUser.createUser"
                                                                                 bundle="${rb}"/></button>
        </div>

        <%--<input type="submit" value="<fmt:message key="login.okLogin" bundle="${rb}"/>">--%>
    </form>
</div>

<%--<c:set var="login1" value="${login}"/>--%>
<%--<c:set var="password11" value="${password}"/>--%>

<%--<form name="loginForm1" method="get" action="controller">--%>
<%--<input type="hidden" name="command" value="cancelLogin">--%>
<%--<input type="submit" value="<fmt:message key="button.cancel" bundle="${rb}"/>">--%>
<%--</form>--%>

<%--<form name="createUser" method="post" action="create user">--%>
<%--<input type="hidden" name="command" value="createUser">--%>
<%--<input type="submit" value="<fmt:message key="aboutUser.createUser" bundle="${rb}"/>">--%>
<%--</form>--%>

<hr/>
</body>
</html>
