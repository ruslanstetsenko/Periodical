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
    <c:set var="currentPage" value="path.page.userPageSubsc" scope="request"/>
    <title><fmt:message key="userPage.subsTitle" bundle="${rb}"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/user_page_subs.css' %>
    </style>
</head>
<body>

<header>
    <div class="header_info">
        <h3 class="about_user">
            <fmt:message key="head.welcome" bundle="${rb}"/>
            <c:out value="${currentUser.surname}"/>
            <c:out value="${currentUser.name}"/>
            <c:out value="${currentUser.lastName}"/>
        </h3>
        <p><c:import url="headUserInfo.jsp"/></p>
    </div>
    <div class="links_user">
        <a class="references" href="userPageBills.jsp"><fmt:message key="userPage.billsTitle" bundle="${rb}"/></a>
        <a class="references" href="aboutUser.jsp"><fmt:message key="userPage.aboutUser" bundle="${rb}"/></a>
    </div>
    <h3 class="header_title"><fmt:message key="aboutSubscription.subsList" bundle="${rb}"/></h3>
</header>

<aside>
    <form name="createSubscription" method="post" action="controller">
        <input type="hidden" name="command" value="createSubscription">
        <input class="add_button" type="submit" name="create" value="<fmt:message key="aboutSubscription.createSubs" bundle="${rb}"/>">
    </form>

    <form name="selectSubscriptions" method="get" action="subscriptions">
        <input type="hidden" name="command" value="selectSubsUserWindow">
        <input class="select_button" type="submit" name="useFilters" value="<fmt:message key="button.useFilter" bundle="${rb}"/>">
        <div>
            <p class="filler_name"><fmt:message key="aboutSubscription.subscrStatus" bundle="${rb}"/></p>
            <select class="select_option" size="1" name="currentSubStatusId">
                <option value="0" <c:if test="${currentSubStatusId == 0}">SELECTED</c:if>><fmt:message key="aboutSubscription.showAll" bundle="${rb}"/></option>
                <c:forEach var="subStatus" items="${subsStatusList}">
                    <option value="${subStatus.id}" <c:if test="${currentSubStatusId == subStatus.id}">SELECTED</c:if>><c:out
                            value="${subStatus.statusName}"/></option>
                </c:forEach>
            </select>
        </div>
    </form>
</aside>

<article>
    <div class="subscription_table_block">
        <table>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th><fmt:message key="aboutPublication.publicationName" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutSubscription.registerDate" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutSubscription.subscrCost" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutSubscription.subscrStatus" bundle="${rb}"/></th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <tbody>
            <c:forEach var="subscription" items="${mapPubNameSubscription}">
                <tr>
                    <td><c:out value="${subscription.key}"/></td>
                    <td align="right"><c:out value="${subscription.value.subscriptionDate}"/></td>
                    <td align="right"><c:out value="${subscription.value.subscriptionCost}"/></td>
                    <td align="right">
                        <c:forEach var="paid" items="${subsStatusList}">
                            <c:if test="${paid.id == subscription.value.subscriptionStatusId}"><c:out value="${paid.statusName}"/></c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <form name="showSubscriptionIngo" action="controller" method="get">
                            <input type="hidden" name="command" value="showAboutSubscription">
                            <button class="table_button" name="currentSubsId" value="${subscription.value.id}" type="submit"><fmt:message key="aboutSubscription.details" bundle="${rb}"/></button>
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
        <fmt:message key="aboutSubscription.subsAmount" bundle="${rb}"/>: ${fn:length(mapPubNameSubscription)}
    </p>
</footer>


</body>
</html>
