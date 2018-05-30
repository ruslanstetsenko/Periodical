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
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/admin_page.css' %>
    </style>
</head>
<body>

<header class="header_admin">
    <div class="header_info">
        <%--<div class="about_user_block">--%>
        <h3 class="about_user"><fmt:message key="head.welcome" bundle="${rb}"/>
            <c:out value="${currentUser.surname}"/>
            <c:out value="${currentUser.name}"/>
            <c:out value="${currentUser.lastName}"/>
        </h3>
        <p class="import_header"><c:import url="headUserInfo.jsp"/></p>
    </div>
    <div class="links_admin">
        <a class="references" href="adminPageBills.jsp"><fmt:message key="adminPage.bills" bundle="${rb}"/></a>
        <a class="references" href="users.jsp"><fmt:message key="adminPage.users" bundle="${rb}"/></a>
    </div>
    <%--</div>--%>
    <h3 class="header_title"><fmt:message key="aboutPublication.periodicalList" bundle="${rb}"/></h3>
</header>

<aside class="select_publications">
    <form name="addpublication" method="post" action="add_publication">
        <input type="hidden" name="command" value="createPublication">
        <input class="add_button" type="submit" name="createPublication"
               value="<fmt:message key="adminPage.periodicalAdd" bundle="${rb}"/>">
    </form>

    <form class="filler_input" name="publicationType" method="get" action="controller">
        <input type="hidden" name="command" value="selectPublicationsAdminWindow">
        <input class="select_button" type="submit" name="useFilters"
               value="<fmt:message key="button.useFilter" bundle="${rb}"/>">
        <div>
            <p class="filler_name"><fmt:message key="aboutPublication.publicationType" bundle="${rb}"/></p>
            <select class="select_option" size="1" name="currentPubTypeId">
                <option value="0" <c:if test="${currentPubTypeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="type" items="${publicationTypeList}">
                    <option value="${type.id}" <c:if test="${currentPubTypeId == type.id}">SELECTED</c:if>><c:out
                            value="${type.typeName}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <p class="filler_name"><fmt:message key="aboutPublication.publicationTheme" bundle="${rb}"/></p>
            <select class="select_option" size="1" name="currentPubThemeId">
                <option value="0" <c:if test="${currentPubThemeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="theme" items="${publicationThemeList}">
                    <option value="${theme.id}" <c:if test="${currentPubThemeId == theme.id}">SELECTED</c:if>><c:out
                            value="${theme.themeName}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <p class="filler_name"><fmt:message key="aboutPublication.publicationStatus" bundle="${rb}"/></p>
            <select class="select_option" size="1" name="currentPubStatusId">
                <option value="0" <c:if test="${currentPubStatusId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="status" items="${publicationStatusList}">
                    <option value="${status.id}" <c:if test="${currentPubStatusId == status.id}">SELECTED</c:if>>
                        <c:out
                                value="${status.statusName}"/></option>
                </c:forEach>
            </select>
        </div>
    </form>
</aside>

<article>
    <div class="publications_table_block">
        <table class="table">
            <%--<thead>--%>
            <%--<tr>--%>
            <%--<th><fmt:message key="aboutPublication.aboutPublication" bundle="${rb}"/></th>--%>
            <%--<th><fmt:message key="aboutPublication.publicationISSN" bundle="${rb}"/></th>--%>
            <%--<th><fmt:message key="aboutPublication.publicationRegDate" bundle="${rb}"/></th>--%>
            <%--<th><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></th>--%>
            <%--<th></th>--%>
            <%--<th></th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
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
                            <button class="table_button" name="publicationId" value="${publication.id}" type="submit">
                                <fmt:message
                                        key="adminPage.periodicalDetails" bundle="${rb}"/></button>
                        </form>
                    </td>
                    <td valign="bottom">
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="editPublication">
                            <button class="table_button" name="publicationId" value="${publication.id}" type="submit">
                                <fmt:message
                                        key="adminPage.periodicalEdit" bundle="${rb}"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</article>

<footer>
    <p class="publication_amount">
        <fmt:message key="aboutPublication.periodicalAmount" bundle="${rb}"/>: ${fn:length(publicationList)}
    </p>
</footer>

</body>
</html>
