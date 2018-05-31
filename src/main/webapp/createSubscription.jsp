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
<fmt:setBundle basename="message" var="validation"/>

<html>
<head>
    <c:set var="currentPage" value="path.page.createSubscription" scope="request"/>
    <title><fmt:message key="aboutSubscription.createTitle" bundle="${rb}"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/create_subscription.css' %>
    </style>
</head>
<body>
<header>
    <h3><fmt:message key="aboutPublication.periodicalList" bundle="${rb}"/> </h3>
    <h3 class="error_message">
        <c:if test="${errorCreateSubscription}">
            <fmt:message key="message.errorCreateSubscription" bundle="${validation}"/>
        </c:if>
    </h3>
</header>

<aside>
    <form class="filler_input">
        <input type="hidden" name="command" value="selectPublicationsCreateSubsWindow">
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
        <br>
    </form>
</aside>

<article>
    <div class="create_subs_block">
        <form name="createSubscription" method="post" action="controller">
            <table>
                <tbody>
                <c:forEach var="publWithCost" items="${publicationListWithCost}">
                    <tr>
                        <td valign="center"><c:out value="${publWithCost.key.name}"/></td>
                        <td valign="center" align="right"><c:out value="${publWithCost.key.website}"/></td>
                        <td valign="center">

                            <c:forEach var="subscription" items="${mapAllPubNameSubscription}">
                                <c:if test="${publWithCost.key.id == subscription.value.publicationId && subscription.value.subscriptionStatusId != 2}">
                                    <c:set var="subscriptionExist" value="true"/>
                                </c:if>
                            </c:forEach>

                            <select class="table_select" size="1" name="curentCostId"
                                    <c:if test="${subscriptionExist}">DISABLED</c:if>>
                                <option></option>
                                <c:forEach var="periodicyCost" items="${publWithCost.value}">
                                    <option value="${periodicyCost.id}"><c:out value="${periodicyCost.cost}"/>
                                        <fmt:message key="aboutPublication.subsCostTail" bundle="${rb}"/> <c:out
                                                value="${periodicyCost.timesPerYear}"/> <fmt:message
                                                key="aboutPublication.subsCostHead" bundle="${rb}"/>
                                    </option>
                                    <c:set var="subscriptionExist" value="false"/>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="buttons">
                <button class="button" type="submit" name="command" value="okCreateSubscription"
                        <c:if test="${fn:length(publicationListWithCost) == 0}">disabled</c:if>>
                    <fmt:message key="aboutSubscription.createSubs" bundle="${rb}"/>
                </button>
                <%--<input class="button" type="submit" name="createSubscription"--%>
                       <%--value="<fmt:message key="aboutSubscription.createSubs" bundle="${rb}"/>">--%>
                <button class="button" type="submit" name="command" value="cancelCreateSubscription">
                    <fmt:message key="button.cancel" bundle="${rb}"/>
                </button>
            </div>

        </form>
    </div>
</article>

<footer>
    <p class="publication_amount"><fmt:message key="aboutPublication.periodicalAmount"
                    bundle="${rb}"/>: ${fn:length(publicationListWithCost)}</p>
</footer>
<%--<div id="publicationsList">--%>
    <%--<form name="cancelSubscription" method="get" action="controller">--%>
        <%--<input type="hidden" name="command" value="cancelCreateSubscription">--%>
        <%--<input type="submit" name="showInfo" value="<fmt:message key="button.cancel" bundle="${rb}"/>">--%>
    <%--</form>--%>
<%--</div>--%>

</body>
</html>
