<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Bill information</title>
</head>
<body>
<div>
    <p><b>Інформація по рахунку</b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><strong>Номер рахунку:</strong></th>
            <td><c:out value="${selectedBill.billNumber}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Дата складання:</strong></th>
            <td><c:out value="${selectedBill.billSetDay}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Загальна вартість:</strong></th>
            <td><c:out value="${selectedBill.totalCost}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Статус оплати:</strong></th>
            <td><c:out value="${selectedBill.paid}"/></td>
        </tr>
        </tbody>
    </table>
</div>
<div>
    <p><b>перелік підписок:</b></p>
    <table>
        <thead>
        <tr>
            <th>Назва видання</th>
            <th>Вартість підписки</th>
            <th>Дата оформлення підписки</th>
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
<form>
    <input type="hidden" name="command" value="cancelWievAboutBill">
    <input type="submit" name="cancel" value="До вікна користувача">
</form>

</body>
</html>
