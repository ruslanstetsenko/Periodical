<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <p>Інформація по рахунку</p>
    <table>
        <tbody>
        <tr>
            <th align="left"><strong>Номер рахунку:</strong></th>
            <th><c:out value="${bill.billNumber}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Дата складання:</strong></th>
            <th><c:out value="${bill.billSetDay}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Загальна вартість:</strong></th>
            <th><c:out value="${bill.totalCost}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Статус оплати:</strong></th>
            <th><c:out value="${bill.paid}"/></th>
        </tr>
        </tbody>
    </table>
    <div>
        <p>перелік підписок:</p>
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
    <input type="submit" name="cancel" value="До вікна користувача" onclick="history.back()">
</div>
</body>
</html>
