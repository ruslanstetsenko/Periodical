<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 12:53
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
    <c:set var="currentPage" value="path.page.aboutPublication" scope="request"/>
    <title><fmt:message key="aboutPublication.publicationInfo" bundle="${rb}"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/about_publication.css' %>
    </style>
</head>
<body>
<article>
    <div class="about_publication">
        <h3 class="about_publication_title"><fmt:message key="aboutPublication.aboutPublication" bundle="${rb}"/></h3>
        <table class="about_table1">
            <tbody>
            <tr>
                <th class="parameneter_title" align="left"><fmt:message key="aboutPublication.publicationName" bundle="${rb}"/></th>
                <td class="parameter_value"><c:out value="${publication.name}"/></td>
            </tr>
            <tr>
                <th class="parameneter_title" align="left"><fmt:message key="aboutPublication.publicationISSN" bundle="${rb}"/></th>
                <td class="parameter_value" align="left"><c:out value="${publication.issnNumber}"/></td>
            </tr>
            <tr>
                <th class="parameneter_title" align="left"><fmt:message key="aboutPublication.publicationType" bundle="${rb}"/></th>
                <td class="parameter_value" align="left"><c:out value="${publicationType.typeName}"/></td>
            </tr>
            <tr>
                <th class="parameneter_title" align="left"><fmt:message key="aboutPublication.publicationStatus" bundle="${rb}"/></th>
                <td class="parameter_value" align="left"><c:out value="${publicationStatus.statusName}"/></td>
            </tr>
            <tr>
                <th class="parameneter_title" align="left"><fmt:message key="aboutPublication.publicationTheme" bundle="${rb}"/></th>
                <td class="parameter_value" align="left"><c:out value="${publicationTheme.themeName}"/></td>
            </tr>
            <tr>
                <th class="parameneter_title" align="left"><fmt:message key="aboutPublication.publicationRegDate" bundle="${rb}"/></th>
                <td class="parameter_value" align="left"><c:out value="${publication.registrationDate}"/></td>
            </tr>
            <tr>
                <th class="parameneter_title" align="left"><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></th>
                <td class="parameter_value" align="left"><c:out value="${publication.website}"/></td>
            </tr>
            </tbody>
        </table>

        <table class="about_table2">
            <tbody>
            <tr>
                <th valign="top"><fmt:message key="aboutPublication.publicationCost" bundle="${rb}"/></th>
                <td>
                    <c:forEach var="cost" items="${publicationPeriodicityCostList}">
                         <c:out value="${cost.timesPerYear}"/> <fmt:message key="aboutPublication.subsCostHead" bundle="${rb}"/> <c:out value="${cost.cost}"/> <fmt:message key="aboutPublication.subsCostTail" bundle="${rb}"/><br>
                    </c:forEach>
                </td>
            </tr>
            </tbody>
        </table>

        <form name="backPrevPage" method="post" action="controller">
            <input type="hidden" name="command" value="cancelEditPublication">
            <input class="button_back" type="submit" name="cancel" value="<fmt:message key="button.backToUserWindow" bundle="${rb}"/>">
        </form>
    </div>
</article>


</body>
</html>
