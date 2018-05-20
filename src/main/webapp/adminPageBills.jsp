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
    <title>Bills</title>
</head>
<body>

<header>
    <a href="adminPage.jsp">Періодичні видання</a>
    <%--<a href="">Рахунки</a>--%>
    <a href="users.jsp">Користувачі</a>
    <c:import url="headUserInfo.jsp"/>
</header>

<div>
    <p>Перелік рахунків</p>
    <table>
        <thead>
        <tr>
            <th>Номер рахунку</th>
            <th>Дата виставлення рахунку</th>
            <th>Загальна вартість, грн</th>
            <th>Статус</th>
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
                            новий
                        </c:when>
                        <c:when test="${bill.paid == 2}">
                            оплачений
                        </c:when>
                        <c:otherwise>
                            не оплачений
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="controller" method="get">
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
    <form name="selectBills" method="get" action="bills">
        <input type="hidden" name="command" value="selectBills">

        <div>
            <p><b>статус рахунку</b></p>
            <select size="1" name="currentBillPaidId">
                <option value="0" <c:if test="${currentBillPaidId == 0}">SELECTED</c:if>>показати всі</option>
                <option value="1" <c:if test="${currentBillPaidId == 1}">SELECTED</c:if>>новий</option>
                <option value="2" <c:if test="${currentBillPaidId == 2}">SELECTED</c:if>>оплачений</option>
                <option value="3" <c:if test="${currentBillPaidId == 3}">SELECTED</c:if>>не оплачений</option>
            </select>
        </div>
        <br>
        <input type="submit" name="useFilters" value="Задіяти фільтри">
    </form>

</div>

<div>
    <%--<form name="showBills" method="get" action="bills">--%>
        <%--<input type="hidden" name="command" value="resetBillList">--%>
        <%--<input type="submit" value="До головного вікна"/>--%>
    <%--</form>--%>
    <a href="adminPage.jsp">До головного вікна</a>
</div>

</body>
</html>
