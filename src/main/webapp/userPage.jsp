<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>User Window</title>
</head>
<body>

<div>
    <p>Перелік підписок</p>
    <table>
        <thead>
        <tr>
            <th>Найменування видання</th>
            <th>Дата реєстрації</th>
            <th>Вартість підписки</th>
            <th>Статус</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="subscription" items="${mapPubNameSubscription}">
            <tr>
                <td><c:out value="${subscription.key}"/></td>
                <td align="right"><c:out value="${subscription.value.subscriptionDate}"/></td>
                <td align="right"><c:out value="${subscription.value.subscriptionCost}"/></td>
                <td align="right"><c:out value="${subscription.value.subscriptionStatusId}"/></td>
                <td>
                    <form name="showSubscriptionIngo" action="controller" method="get">
                        <input type="hidden" name="command" value="showAboutSubscription">
                        <button name="currentSubsId" value="${subscription.value.id}" type="submit">Докладно</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість підписок: ${fn:length(mapPubNameSubscription)}</p>
</div>

<div>
    <p>Перелік рахунків</p>
    <table>
        <thead>
        <tr>
            <th>Номер рахунку</th>
            <th>Дата виставлення</th>
            <th>Загальна вартість</th>
            <th>Статус</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bill" items="${subscriptionBillList}">
            <tr>
                <td><c:out value="${bill.billNumber}"/></td>
                <td align="right"><c:out value="${bill.billSetDay}"/></td>
                <td align="right"><c:out value="${bill.totalCost}"/></td>
                <td align="right"><c:out value="${bill.paid}"/></td>
                <td>
                    <form name="showBillInfo" action="controller" method="get">
                        <input type="hidden" name="command" value="showAboutBill">
                        <button name="currentBillPaidId" value="${bill.id}" type="submit">Докладно</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість рахунків: ${fn:length(subscriptionBillList)}</p>
</div>

<div>
    <form>
        <input type="hidden" name="command" value="selectSubsBillsUserWindow">
        <div>
            <p><b>статус підписки:</b></p>
            <input type="radio" name="currentSubStatusId" value="0"
                   <c:if test="${currentSubStatusId == 0}">CHECKED</c:if>/>показати всі
            <c:forEach var="subStatus" items="${subsStatusList}">
                <p align="left">
                    <input type="radio" name="currentSubStatusId" value="${subStatus.id}"
                           <c:if test="${currentSubStatusId == subStatus.id}">CHECKED</c:if>/><c:out
                        value="${subStatus.statusName}"/>
                </p>
            </c:forEach>
        </div>

        <div>
            <p><b>статус рахунку</b></p>
            <p><input type="radio" name="currentBillPaidId" value="0"
                      <c:if test="${currentBillPaidId == 0}">CHECKED</c:if>/>показати всі</p>
            <p><input type="radio" name="currentBillPaidId" value="1"
                      <c:if test="${currentBillPaidId == 1}">CHECKED</c:if>/>новий</p>
            <p><input type="radio" name="currentBillPaidId" value="2"
                      <c:if test="${currentBillPaidId == 2}">CHECKED</c:if>/>оплачений</p>
            <p><input type="radio" name="currentBillPaidId" value="3"
                      <c:if test="${currentBillPaidId == 3}">CHECKED</c:if>/>не оплачений</p>
        </div>

        <input type="submit" name="useFilters" value="Задіяти фільтри">
    </form>
</div>

<div>
    <form name="createSubscription" method="post" action="controller">
        <input type="hidden" name="command" value="createSubscription">
        <input type="submit" name="create" value="Оформити підписку">
    </form>
</div>

<div>

</div>

</body>
</html>
