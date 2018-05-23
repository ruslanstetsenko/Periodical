<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 17:55
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
    <c:set var="currentPage" value="path.page.createSubscription" scope="request"/>
    <title><fmt:message key="aboutSubscription.createTitle" bundle="${rb}"/></title>
</head>
<body>
<div>
    <form name="createSubscription" method="post" action="controller">
        <input type="hidden" name="command" value="okCreateSubscription">

        <p><fmt:message key="aboutPublication.periodicalList" bundle="${rb}"/></p>
        <table>
            <thead>
            <tr>
                <%--<td></td>--%>
                <th><fmt:message key="aboutPublication.publicationName" bundle="${rb}"/></th>
                <th><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></th>
                <th><fmt:message key="aboutPublication.publicationCost" bundle="${rb}"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="publWithCost" items="${publicationListWithCost}">
                <%--<form name="createSubscription" method="get" action="controller">--%>
                <input type="hidden" name="command" value="addPublicationToSubscription">
                <tr>
                        <%--<td valign="top"><input type="checkbox" name="curentPubid" value="${publWithCost.key.id}"></td>--%>
                    <td valign="center"><c:out value="${publWithCost.key.name}"/></td>
                    <td valign="center" align="right"><c:out value="${publWithCost.key.website}"/></td>
                        <%--<td>--%>
                        <%--<br>--%>
                        <%--<c:forEach var="periodicyCost" items="${publWithCost.value}">--%>
                        <%--<input type="checkbox" name="curentCostid" value="${periodicyCost.id}"><c:out--%>
                        <%--value="${periodicyCost.cost}"/><br/>--%>
                        <%--</c:forEach>--%>
                        <%--</td>--%>


                    <td valign="center">

                        <c:forEach var="subscription" items="${mapAllPubNameSubscription}">
                            <c:if test="${publWithCost.key.id == subscription.value.publicationId && subscription.value.subscriptionStatusId != 2}">
                                <c:set var="subscriptionExist" value="true"/>
                            </c:if>
                        </c:forEach>
                        <br>

                        <select size="1" name="curentCostId"
                                <c:if test="${subscriptionExist}">DISABLED</c:if>>

                            <option></option>
                            <c:forEach var="periodicyCost" items="${publWithCost.value}">
                                <option value="${periodicyCost.id}"><c:out value="${periodicyCost.cost}"/> <fmt:message key="aboutPublication.subsCostTail" bundle="${rb}"/> <c:out value="${periodicyCost.timesPerYear}"/> <fmt:message key="aboutPublication.subsCostHead" bundle="${rb}"/>
                                </option>
                                <c:set var="subscriptionExist" value="false"/>
                            </c:forEach>
                        </select>
                    </td>
                        <%--<td>--%>
                        <%--<input type="submit" name="addPubToSubs" value="Додати підписку" <c:if test="${costValue == 0}">disabled</c:if>>--%>
                        <%--</td>--%>
                </tr>
                <%--</form>--%>
            </c:forEach>
            </tbody>
        </table>

        <p><fmt:message key="aboutPublication.periodicalAmount" bundle="${rb}"/> ${fn:length(publicationListWithCost)}</p>
        <input type="submit" name="createSubscription" value="<fmt:message key="aboutSubscription.createSubs" bundle="${rb}"/>">

    </form>

    <form>
        <input type="hidden" name="command" value="selectPublicationsCreateSubsWindow">

        <div>
            <p><b><fmt:message key="aboutPublication.publicationType" bundle="${rb}"/></b></p>
            <select size="1" name="currentPubTypeId">
                <option value="0" <c:if test="${currentPubTypeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="type" items="${publicationTypeList}">
                    <option value="${type.id}" <c:if test="${currentPubTypeId == type.id}">SELECTED</c:if>><c:out
                            value="${type.typeName}"/></option>
                </c:forEach>
            </select>
        </div>

        <div>
            <p><b><fmt:message key="aboutPublication.publicationTheme" bundle="${rb}"/></b></p>
            <select size="1" name="currentPubThemeId">
                <option value="0" <c:if test="${currentPubThemeId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="theme" items="${publicationThemeList}">
                    <option value="${theme.id}" <c:if test="${currentPubThemeId == theme.id}">SELECTED</c:if>><c:out
                            value="${theme.themeName}"/></option>
                </c:forEach>
            </select>
        </div>
        <br>
        <input type="submit" name="useFilters" value="<fmt:message key="button.useFilter" bundle="${rb}"/>">
    </form>

</div>

<div id="publicationsList">
    <form name="cancelSubscription" method="get" action="controller">
        <input type="hidden" name="command" value="cancelCreateSubscription">
        <input type="submit" name="showInfo" value="<fmt:message key="button.cancel" bundle="${rb}"/>">
    </form>
</div>

</body>
</html>
