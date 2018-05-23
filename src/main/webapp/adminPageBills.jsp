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
    <c:set var="currentPage" value="path.page.adminPageBills" scope="request"/>
    <title><fmt:message key="adminPage.bills" bundle="${rb}"/></title>
</head>
<body>

<header>
    <h3><fmt:message key="head.welcome" bundle="${rb}"/> <c:out value="${currentUser.surname}"/> <c:out value="${currentUser.name}"/> <c:out value="${currentUser.lastName}"/></h3>
    <a href="adminPage.jsp"><fmt:message key="adminPage.periodical" bundle="${rb}"/></a>
    <%--<a href="">Рахунки</a>--%>
    <a href="users.jsp"><fmt:message key="adminPage.users" bundle="${rb}"/></a>
    <c:import url="headUserInfo.jsp"/>
</header>

<div>
    <p><fmt:message key="aboutBill.billsList" bundle="${rb}"/></p>
    <table>
        <thead>
        <tr>
            <th><fmt:message key="aboutBill.billNumber" bundle="${rb}"/></th>
            <th><fmt:message key="aboutBill.billingDate" bundle="${rb}"/></th>
            <th><fmt:message key="aboutBill.billingCost" bundle="${rb}"/></th>
            <th><fmt:message key="aboutBill.billiPayStatus" bundle="${rb}"/></th>
        </tr>
        </thead>
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
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="showAboutBill">
                        <button name="currentBillPaidId" value="${bill.id}" type="submit"><fmt:message key="aboutBill.billDetails" bundle="${rb}"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><fmt:message key="aboutBill.billAmount" bundle="${rb}"/> ${fn:length(subscriptionBillList)}</p>
</div>

<div>
    <form name="selectBills" method="get" action="bills">
        <input type="hidden" name="command" value="selectBills">

        <div>
            <p><b><fmt:message key="aboutBill.billiPayStatus" bundle="${rb}"/></b></p>
            <select size="1" name="currentBillPaidId">
                <option value="0" <c:if test="${currentBillPaidId == 0}">SELECTED</c:if>><fmt:message key="aboutBill.billiPayStatusAll" bundle="${rb}"/></option>
                <option value="1" <c:if test="${currentBillPaidId == 1}">SELECTED</c:if>><fmt:message key="aboutBill.billiPayStatusNameNew" bundle="${rb}"/></option>
                <option value="2" <c:if test="${currentBillPaidId == 2}">SELECTED</c:if>><fmt:message key="aboutBill.billiPayStatusNamePaid" bundle="${rb}"/></option>
                <option value="3" <c:if test="${currentBillPaidId == 3}">SELECTED</c:if>><fmt:message key="aboutBill.billiPayStatusNotPaid" bundle="${rb}"/></option>
            </select>
        </div>
        <br>
        <input type="submit" name="useFilters" value="<fmt:message key="button.useFilter" bundle="${rb}"/>">
    </form>

</div>

<div>
    <%--<form name="showBills" method="get" action="bills">--%>
        <%--<input type="hidden" name="command" value="resetBillList">--%>
        <%--<input type="submit" value="До головного вікна"/>--%>
    <%--</form>--%>
    <%--<a href="adminPage.jsp"><fmt:message key="button.goMainPage" bundle="${rb}"/></a>--%>
</div>

</body>
</html>
