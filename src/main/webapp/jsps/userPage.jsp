<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true"%>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість підписок: ${fn:length(mapPubNameSubscription)}</p>
</div>

<div>
    <p>статус підписки:</p>
    <form name="subscriptionType" method="get" action="controller">
        <input type="hidden" name="command" value="selectSubscriptionsByStatus">
        <p><select name="select" size="1">
            <option value="s1">активна</option>
            <option value="s2">закінчена</option>
        </select>
    </form>

    <form name="showSubscriptionIngo" method="get" action="controller">
        <input type="hidden" name="command" value="showAboutSubscription">
        <input type="submit" name="showInfo" value="Детальна інформація">
    </form>

    <form name="createSubscription" method="post" action="controller">
        <input type="hidden" name="command" value="Оформити підписку">
        <input type="submit" name="create" value="Create">
    </form>
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
        <c:forEach var="bill" items="${subscriptionBillListUser}">
            <tr>
                <td><c:out value="${bill.billNumber}"/></td>
                <td align="right"><c:out value="${bill.billSetDay}"/></td>
                <td align="right"><c:out value="${bill.totalCost}"/></td>
                <td align="right"><c:out value="${bill.paid}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість рахунків: ${fn:length(subscriptionBillListUser)}</p>
</div>

<div>
    <p>статус рахунку:</p>
    <form name="billType" method="get" action="controller">
        <input type="hidden" name="command" value="selectBillsByStatusByUser">
        <p><select name="select" size="1">
            <option value="s1">оплачено</option>
            <option value="s2">не оплачено</option>
        </select>
    </form>

    <form name="showBillInfo" method="get" action="controller">
        <input type="hidden" name="command" value="showAboutBill">
        <input type="submit" name="showInfo" value="Детальна інформація">
    </form>
</div>

</body>
</html>
