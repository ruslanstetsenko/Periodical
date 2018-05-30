<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 18.05.2018
  Time: 19:01
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

<html>
<head>
    <c:set var="currentPage" value="path.page.users" scope="request"/>
    <title><fmt:message key="aboutUser.userList" bundle="${rb}"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/users.css' %>
    </style>
</head>
<body>

<header class="header_admin">
    <div class="header_info">
        <h3><fmt:message key="head.welcome" bundle="${rb}"/>
            <c:out value="${currentUser.surname}"/>
            <c:out value="${currentUser.name}"/>
            <c:out value="${currentUser.lastName}"/>
        </h3>
        <p class="import_header"><c:import url="headUserInfo.jsp"/></p>
    </div>
    <div class="links_admin">
        <a class="references" href="adminPage.jsp">
            <fmt:message key="adminPage.periodical" bundle="${rb}"/>
        </a>
        <a class="references" href="adminPageBills.jsp">
            <fmt:message key="adminPage.bills" bundle="${rb}"/>
        </a>
    </div>
    <h3 class="header_title"><fmt:message key="aboutUser.userList" bundle="${rb}"/></h3>
<%--<a href="users.jsp">Користувачі</a>--%>
</header>

<%--<aside class="select_users">--%>

<%--</aside>--%>

<article>
    <div class="pusers_table_block">
        <table class="table">
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th><fmt:message key="aboutUser.surname" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutUser.name" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutUser.lastName" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutUser.birthday" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutUser.regDate" bundle="${rb}"/></th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.surname}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.birthday}"/></td>
                    <td><c:out value="${user.registrationDate}"/></td>
                    <td valign="bottom ">
                        <form name="showAboutUser" action="about_user" method="get">
                            <input type="hidden" name="command" value="showAboutUser">
                            <button class="table_button" name="currentUserId" value="${user.id}" type="submit"><fmt:message key="aboutUser.details" bundle="${rb}"/></button>
                        </form>
                    </td>
                    <td valign="bottom ">
                        <form name="editAboutUser" action="edit_user" method="post">
                            <input type="hidden" name="command" value="editUser">
                            <button class="table_button" name="currentUserId" value="${user.id}" type="submit"><fmt:message key="aboutUser.edit" bundle="${rb}"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</article>

<footer>
    <p class="users_amount">
        <fmt:message key="aboutUser.userAmount" bundle="${rb}"/>: ${fn:length(userList)}
    </p>
</footer>



<%--<form name="createUser" method="post" action="create_user">--%>
    <%--<input type="hidden" name="command" value="createUser">--%>
    <%--<input type="submit" name="createUser" value="<fmt:message key="aboutUser.createUser" bundle="${rb}"/>">--%>
<%--</form>--%>

<%--<div>--%>
    <%--<a href="adminPage.jsp"><fmt:message key="button.goMainPage" bundle="${rb}"/></a>--%>
<%--</div>--%>

</body>
</html>
