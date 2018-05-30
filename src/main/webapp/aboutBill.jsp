<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 17:02
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
    <c:set var="currentPage" value="path.page.aboutBill" scope="request"/>
    <title><fmt:message key="aboutBill.billInfo" bundle="${rb}"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/about_bill.css' %>
    </style>
</head>
<body>
<article>
    <div class="global_div">
        <div class="about_bill">
            <h3><fmt:message key="aboutBill.aboutBill" bundle="${rb}"/></h3>
            <table class="about_bill_table">
                <tbody>
                <tr>
                    <th class="parameneter_title" align="left">
                        <fmt:message key="aboutBill.billNumber" bundle="${rb}"/>
                    </th>
                    <td class="parameter_value"><c:out value="${selectedBill.billNumber}"/></td>
                </tr>
                <tr>
                    <th class="parameneter_title" align="left">
                        <fmt:message key="aboutBill.billingDate" bundle="${rb}"/>
                    </th>
                    <td class="parameter_value"><c:out value="${selectedBill.billSetDay}"/></td>
                </tr>
                <tr>
                    <th class="parameneter_title" align="left">
                        <fmt:message key="aboutBill.billingCost" bundle="${rb}"/>
                    </th>
                    <td class="parameter_value"><c:out value="${selectedBill.totalCost}"/></td>
                </tr>
                <tr>
                    <th class="parameneter_title" align="left">
                        <fmt:message key="aboutBill.billiPayStatus" bundle="${rb}"/>
                    </th>
                    <td class="parameter_value">
                        <c:choose>
                            <c:when test="${selectedBill.paid == 1}">
                                <fmt:message key="aboutBill.billiPayStatusNameNew" bundle="${rb}"/>
                            </c:when>
                            <c:when test="${selectedBill.paid == 2}">
                                <fmt:message key="aboutBill.billiPayStatusNamePaid" bundle="${rb}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="aboutBill.billiPayStatusNotPaid" bundle="${rb}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="about_subscriptions">
            <h4><fmt:message key="aboutBill.subscriptionList" bundle="${rb}"/></h4>
            <table class="about_subscriptions_table">
                <thead>
                <tr>
                    <th><fmt:message key="aboutSubscription.subscriptionTitle" bundle="${rb}"/></th>
                    <th><fmt:message key="aboutSubscription.subscrCost" bundle="${rb}"/></th>
                    <th><fmt:message key="aboutBill.subscriptionRegistration" bundle="${rb}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="subscription" items="${mapSubscriptions}">
                    <tr>
                        <td><c:out value="${subscription.key}"/></td>
                        <td align="right"><c:out value="${subscription.value.subscriptionCost}"/></td>
                        <td align="right"><c:out value="${subscription.value.subscriptionDate}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <form class="form">
            <input type="hidden" name="command" value="cancelWievAboutBill">
            <input class="button" type="submit" name="cancel" value="<fmt:message key="button.backToUserWindow" bundle="${rb}"/>">
        </form>
    </div>

</article>



</body>
</html>
