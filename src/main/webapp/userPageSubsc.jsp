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

<header>
    <c:import url="headUserInfo.jsp"/>
    <a href="userPageBills.jsp">Рахунки</a>
    <a href="aboutUser.jsp">Інформація про читача</a>
</header>

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
                <td align="right">
                    <c:forEach var="paid" items="${subsStatusList}">
                        <c:if test="${paid.id == subscription.value.subscriptionStatusId}"><c:out value="${paid.statusName}"/></c:if>
                    </c:forEach>
                    </td>
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
    <form name="selectSubscriptions" method="get" action="subscriptions">
        <input type="hidden" name="command" value="selectSubsUserWindow">
        <div>
            <b>статус підписки: </b>
            <%--<input type="radio" name="currentSubStatusId" value="0"--%>
                   <%--<c:if test="${currentSubStatusId == 0}">CHECKED</c:if>/>показати всі--%>
            <%--<c:forEach var="subStatus" items="${subsStatusList}">--%>
                <%--<p align="left">--%>
                    <%--<input type="radio" name="currentSubStatusId" value="${subStatus.id}"--%>
                           <%--<c:if test="${currentSubStatusId == subStatus.id}">CHECKED</c:if>/><c:out--%>
                        <%--value="${subStatus.statusName}"/>--%>
                <%--</p>--%>
            <%--</c:forEach>--%>

            <select size="1" name="currentSubStatusId">
                <option value="0" <c:if test="${currentSubStatusId == 0}">SELECTED</c:if>>показати всі</option>
                <c:forEach var="subStatus" items="${subsStatusList}">
                    <option value="${subStatus.id}" <c:if test="${currentSubStatusId == subStatus.id}">SELECTED</c:if>><c:out
                            value="${subStatus.statusName}"/></option>
                </c:forEach>
            </select>
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
