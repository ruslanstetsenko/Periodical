<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<c:if test="${locale == 1}"><fmt:setLocale value="en_US" scope="session"/></c:if>
<c:if test="${locale == 2}"><fmt:setLocale value="uk_UA" scope="session"/></c:if>
<fmt:setBundle basename="pagecontent" var="rb"/>

<html>
<head>
    <c:set var="currentPage" value="path.page.login" scope="request"/>
    <title><fmt:message key="login.title" bundle="${rb}"/></title>
</head>
<body>
    <form name="loginForm" method="post" action="${loginFormAction}">
        <input type="hidden" name="command" value="okLogin">
        <fmt:message key="login.login" bundle="${rb}"/>
        <input type="text" name="login" placeholder="login"><br/>
        <fmt:message key="login.password" bundle="${rb}"/>
        <input type="password" name="password" placeholder="password"><br/>
        ${errorLoginMessage}<br/>
        ${incorrectAction}<br/>
        ${nullPage}<br/>
        <input type="submit" value="<fmt:message key="login.okLogin" bundle="${rb}"/>">
    </form>

    <form name="loginForm1" method="get" action="controller">
        <input type="hidden" name="command" value="cancelLogin">
        <input type="submit" value="<fmt:message key="button.cancel" bundle="${rb}"/>">
    </form>
    <hr/>
</body>
</html>
