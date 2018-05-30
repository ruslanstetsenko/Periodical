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
    <c:set var="currentPage" value="path.page.userPageBills" scope="request"/>
    <title><fmt:message key="userPage.billsTitle" bundle="${rb}"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/bills.css' %>
    </style>
</head>
<body>

<header class="bill_header">
    <div class="header_info">
        <h3 class="about_user"><fmt:message key="head.welcome" bundle="${rb}"/>
            <c:out value="${currentUser.surname}"/>
            <c:out value="${currentUser.name}"/>
            <c:out value="${currentUser.lastName}"/>
        </h3>
        <p class="import_header"><c:import url="headUserInfo.jsp"/></p>
    </div>
    <div class="links_admin">
        <a class="references" href="userPageSubsc.jsp"><fmt:message key="userPage.subscriptions" bundle="${rb}"/></a>
        <a class="references" href="aboutUser.jsp"><fmt:message key="userPage.aboutUser" bundle="${rb}"/></a>
    </div>
    <h3><fmt:message key="aboutBill.billsList" bundle="${rb}"/></h3>
</header>

<aside>
    <form class="filter_input" name="selectBills" method="get" action="bills">
        <input type="hidden" name="command" value="selectBills">
        <input class="select_button" type="submit" name="useFilters" value="<fmt:message key="button.useFilter" bundle="${rb}"/>">
        <div>
            <p class="filler_name"><fmt:message key="aboutBill.billiPayStatus" bundle="${rb}"/></p>
            <select class="select_option" size="1" name="currentBillPaidId">
                <option value="0" <c:if test="${currentBillPaidId == 0}">SELECTED</c:if>><fmt:message
                        key="aboutBill.billiPayStatusAll" bundle="${rb}"/></option>
                <option value="1" <c:if test="${currentBillPaidId == 1}">SELECTED</c:if>><fmt:message
                        key="aboutBill.billiPayStatusNameNew" bundle="${rb}"/></option>
                <option value="2" <c:if test="${currentBillPaidId == 2}">SELECTED</c:if>><fmt:message
                        key="aboutBill.billiPayStatusNamePaid" bundle="${rb}"/></option>
                <option value="3" <c:if test="${currentBillPaidId == 3}">SELECTED</c:if>><fmt:message
                        key="aboutBill.billiPayStatusNotPaid" bundle="${rb}"/></option>
            </select>
        </div>
    </form>
</aside>

<article>
    <div class="bill_table_block">
        <table>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th><fmt:message key="aboutBill.billNumber" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutBill.billingDate" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutBill.billingCost" bundle="${rb}"/></th>--%>
                <%--<th><fmt:message key="aboutBill.billiPayStatus" bundle="${rb}"/></th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <tbody>
            <c:forEach var="bill" items="${subscriptionBillList}">
                <tr>
                    <td><c:out value="${bill.billNumber}"/></td>
                    <td align="right"><c:out value="${bill.billSetDay}"/></td>
                    <td align="right"><c:out value="${bill.totalCost}"/></td>
                    <td align="right">
                        <c:choose>
                            <c:when test="${bill.paid == 1}">
                                <fmt:message key="aboutBill.billiPayStatusNameNew" bundle="${rb}"/>
                            </c:when>
                            <c:when test="${bill.paid == 2}">
                                <fmt:message key="aboutBill.billiPayStatusNamePaid" bundle="${rb}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="aboutBill.billiPayStatusNotPaid" bundle="${rb}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form name="showBillInfo" action="controller" method="get">
                            <input type="hidden" name="command" value="showAboutBill">
                            <button class="table_button" name="currentBillPaidId" value="${bill.id}" type="submit"><fmt:message
                                    key="aboutBill.billDetails" bundle="${rb}"/></button>
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
        <fmt:message key="aboutBill.billAmount" bundle="${rb}"/>: ${fn:length(subscriptionBillList)}
    </p>
</footer>
</body>
</html>
