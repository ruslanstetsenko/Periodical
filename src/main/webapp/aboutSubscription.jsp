<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 14.05.2018
  Time: 15:12
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
    <c:set var="currentPage" value="path.page.aboutSubscription" scope="request"/>
    <title><fmt:message key="aboutSubscription.title" bundle="${rb}"/></title>
</head>
<body>

<div>
    <p><b><fmt:message key="aboutSubscription.aboutSubscription" bundle="${rb}"/></b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><fmt:message key="aboutSubscription.subscriptionTitle" bundle="${rb}"/></th>
            <td align="left"><c:out value="${publicationName}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutSubscription.registerDate" bundle="${rb}"/></th>
            <td align="left"><c:out value="${subscriptionDate}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutSubscription.subscrCost" bundle="${rb}"/></th>
            <td align="left"><c:out value="${subscriptionCost}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutSubscription.subscrStatus" bundle="${rb}"/></th>
            <td align="left">
                <c:forEach var="subsStatus" items="${subsStatusList}">
                    <c:if test="${subsStatus.id == subscriptionStatusId}"><c:out value="${subsStatus.statusName}"/> </c:if>
                </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <p><b><fmt:message key="aboutSubscription.aboutPublication" bundle="${rb}"/></b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><strong><fmt:message key="aboutPublication.publicationName" bundle="${rb}"/></strong></th>
            <td><c:out value="${publication.name}"/></td>
        </tr>
        <tr>
            <th align="left"><strong><fmt:message key="aboutPublication.publicationISSN" bundle="${rb}"/></strong></th>
            <td align="left"><c:out value="${publication.issnNumber}"/></td>
        </tr>
        <tr>
            <th align="left"><strong><fmt:message key="aboutPublication.publicationType" bundle="${rb}"/></strong></th>
            <td align="left"><c:out value="${publicationType.typeName}"/></td>
        </tr>
        <tr>
            <th align="left"><strong><fmt:message key="aboutPublication.publicationStatus" bundle="${rb}"/></strong></th>
            <td align="left"><c:out value="${publicationStatus.statusName}"/></td>
        </tr>
        <tr>
            <th align="left"><strong><fmt:message key="aboutPublication.publicationTheme" bundle="${rb}"/></strong></th>
            <td align="left"><c:out value="${publicationTheme.themeName}"/></td>
        </tr>
        <tr>
            <th align="left"><strong><fmt:message key="aboutPublication.publicationRegDate" bundle="${rb}"/></strong></th>
            <td align="left"><c:out value="${publication.registrationDate}"/></td>
        </tr>
        <tr>
            <th align="left"><strong><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></strong></th>
            <td align="left"><c:out value="${publication.website}"/></td>
        </tr>
        </tbody>
    </table>

    <table>
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
        <input type="hidden" name="command" value="cancelEditSubscription">
        <input type="submit" name="cancel" value="<fmt:message key="button.backToUserWindow" bundle="${rb}"/>">
    </form>
</div>


</body>
</html>
