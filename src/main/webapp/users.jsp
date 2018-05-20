<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 18.05.2018
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>User list</title>
</head>
<body>

<header>
    <c:import url="headUserInfo.jsp"/>
    <a href="adminPage.jsp">Періодичні видання</a>
    <a href="adminPageBills.jsp">Рахунки</a>
    <%--<a href="users.jsp">Користувачі</a>--%>
</header>

<div>
    <p>Перелік користувачів</p>
    <table>
        <thead>
        <tr>
            <th>Прізвище</th>
            <th>Імя</th>
            <th>По-батькові</th>
            <th>Дата народження</th>
            <th>Зареєстрований в системі</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td align="right"><c:out value="${user.surname}"/></td>
                <td align="right"><c:out value="${user.name}"/></td>
                <td align="right"><c:out value="${user.lastName}"/></td>
                <td align="right"><c:out value="${user.birthday}"/></td>
                <td align="right"><c:out value="${user.registrationDate}"/></td>
                <td valign="bottom ">
                    <form name="showAboutUser" action="about_user" method="get">
                        <input type="hidden" name="command" value="showAboutUser">
                        <button name="currentUserId" value="${user.id}" type="submit">Докладно</button>
                    </form>
                </td>
                <td valign="bottom ">
                    <form name="editAboutUser" action="edit_user" method="post">
                        <input type="hidden" name="command" value="editUser">
                        <button name="currentUserId" value="${user.id}" type="submit">Редагувати</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<form name="createUser" method="post" action="create_user">
    <input type="hidden" name="command" value="createUser">
    <input type="submit" name="createUser" value="Створити користувача">
</form>

<div>
    <a href="adminPage.jsp">До головного вікна</a>
</div>

</body>
</html>
