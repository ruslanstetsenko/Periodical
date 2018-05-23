<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 14:30
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
    <c:set var="currentPage" value="path.page.adminPage" scope="request"/>
    <title><fmt:message key="adminPage.periodical" bundle="${rb}"/></title>
</head>
<body>

<header>
    <%--<a href="adminPage.jsp">Періодичні видання</a>--%>
    <h3><fmt:message key="head.welcome" bundle="${rb}"/> <c:out value="${currentUser.surname}"/> <c:out
            value="${currentUser.name}"/> <c:out value="${currentUser.lastName}"/></h3>
    <a href="adminPageBills.jsp"><fmt:message key="adminPage.bills" bundle="${rb}"/></a>
    <a href="users.jsp"><fmt:message key="adminPage.users" bundle="${rb}"/></a>
    <c:import url="headUserInfo.jsp"/>
</header>

<div>
    <p><fmt:message key="aboutPublication.periodicalList" bundle="${rb}"/></p>
    <table>
        <thead>
        <tr>
            <th><fmt:message key="aboutPublication.aboutPublication" bundle="${rb}"/></th>
            <th><fmt:message key="aboutPublication.publicationISSN" bundle="${rb}"/></th>
            <th><fmt:message key="aboutPublication.publicationRegDate" bundle="${rb}"/></th>
            <th><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></th>
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
                        <button name="publicationId" value="${publication.id}" type="submit"><fmt:message
                                key="adminPage.periodicalDetails" bundle="${rb}"/></button>
                    </form>
                </td>
                <td valign="bottom ">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="editPublication">
                        <button name="publicationId" value="${publication.id}" type="submit"><fmt:message
                                key="adminPage.periodicalEdit" bundle="${rb}"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><fmt:message key="aboutPublication.periodicalAmount" bundle="${rb}"/>${fn:length(publicationList)}</p>
</div>

<div>
    <form name="publicationType" method="get" action="controller">
        <input type="hidden" name="command" value="selectPublicationsAdminWindow">

        <div>
            <b><fmt:message key="aboutPublication.publicationType" bundle="${rb}"/></b>
            <select size="1" name="currentPubTypeId">
                <option value="0" <c:if test="${currentPubTypeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="type" items="${publicationTypeList}">
                    <option value="${type.id}" <c:if test="${currentPubTypeId == type.id}">SELECTED</c:if>><c:out
                            value="${type.typeName}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <b><fmt:message key="aboutPublication.publicationTheme" bundle="${rb}"/></b>
            <select size="1" name="currentPubThemeId">
                <option value="0" <c:if test="${currentPubThemeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="theme" items="${publicationThemeList}">
                    <option value="${theme.id}" <c:if test="${currentPubThemeId == theme.id}">SELECTED</c:if>><c:out
                            value="${theme.themeName}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <b><fmt:message key="aboutPublication.publicationStatus" bundle="${rb}"/></b>
            <select size="1" name="currentPubStatusId">
                <option value="0" <c:if test="${currentPubStatusId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="status" items="${publicationStatusList}">
                    <option value="${status.id}" <c:if test="${currentPubStatusId == status.id}">SELECTED</c:if>><c:out
                            value="${status.statusName}"/></option>
                </c:forEach>
            </select>

        </div>

        <br>
        <input type="submit" name="useFilters" value="<fmt:message key="button.useFilter" bundle="${rb}"/>">
    </form>

    <form name="addpublication" method="post" action="add_publication">
        <input type="hidden" name="command" value="createPublication">
        <input type="submit" name="createPublication"
               value="<fmt:message key="adminPage.periodicalAdd" bundle="${rb}"/>">
    </form>

</div>
</body>
</html>
