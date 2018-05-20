<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Periodical</title>
</head>
<body>

<header>
    <%--<a href="adminPage.jsp">Періодичні видання</a>--%>
    <a href="adminPageBills.jsp">Рахунки</a>
    <a href="users.jsp">Користувачі</a>
    <c:import url="headUserInfo.jsp"/>

</header>

<div>
    <p>Перелік періодичних видань</p>
    <table>
        <thead>
        <tr>
            <th>Найменування</th>
            <th>ISSN</th>
            <th>Дата реєстрації</th>
            <th>Вебсайт</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="publication" items="${publicationList}">
            <tr>
                <td><c:out value="${publication.name}"/></td>
                <td align="right"><c:out value="${publication.issnNumber}"/></td>
                <td align="right"><c:out value="${publication.registrationDate}"/></td>
                <td align="right"><c:out value="${publication.website}"/></td>
                <td valign="bottom ">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="aboutPublication">
                        <button name="publicationId" value="${publication.id}" type="submit">Докладно</button>
                    </form>
                </td>
                <td valign="bottom ">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="editPublication">
                        <button name="publicationId" value="${publication.id}" type="submit">Редагувати</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість видань: ${fn:length(publicationList)}</p>
</div>

<div>
    <form name="publicationType" method="get" action="controller">
        <input type="hidden" name="command" value="selectPublicationsAdminWindow">

        <div>
            <b>тип видання: </b>
            <select size="1" name="currentPubTypeId">
                <option value="0" <c:if test="${currentPubTypeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="type" items="${publicationTypeList}">
                    <option value="${type.id}" <c:if test="${currentPubTypeId == type.id}">SELECTED</c:if>><c:out
                            value="${type.typeName}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <b>тематика видання: </b>
            <select size="1" name="currentPubThemeId">
                <option value="0" <c:if test="${currentPubThemeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="theme" items="${publicationThemeList}">
                    <option value="${theme.id}" <c:if test="${currentPubThemeId == theme.id}">SELECTED</c:if>><c:out
                            value="${theme.themeName}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <b>статус видання: </b>
            <select size="1" name="currentPubStatusId">
                <option value="0" <c:if test="${currentPubStatusId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="status" items="${publicationStatusList}">
                    <option value="${status.id}" <c:if test="${currentPubStatusId == status.id}">SELECTED</c:if>><c:out
                            value="${status.statusName}"/></option>
                </c:forEach>
            </select>

        </div>

        <br>
        <input type="submit" name="useFilters" value="Задіяти фільтри">
    </form>

    <form name="addpublication" method="post" action="add_publication">
        <input type="hidden" name="command" value="createPublication">
        <input type="submit" name="createPublication" value="Додати нове видання">
    </form>

</div>
</body>
</html>
