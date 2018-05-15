<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Create subscription</title>
</head>
<body>
<div>
    <form name="createSubscription" method="get" action="controller">
        <input type="hidden" name="command" value="okCreateSubscription">

        <p>Перелік періодичних видань</p>
        <table>
            <thead>
            <tr>
                <td></td>
                <th>Найменування</th>
                <th>Вебсайт</th>
                <th>Варіанти підписки</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="publWithCost" items="${publicationListWithCost}">
                <tr>
                    <td valign="top"><input type="checkbox" name="curentPubid" value="${publWithCost.key.id}"></td>
                    <td valign="top"><c:out value="${publWithCost.key.name}"/></td>
                    <td valign="top" align="right"><c:out value="${publWithCost.key.website}"/></td>
                        <%--<td>--%>
                        <%--<br>--%>
                        <%--<c:forEach var="periodicyCost" items="${publWithCost.value}">--%>
                        <%--<input type="checkbox" name="curentCostid" value="${periodicyCost.id}"><c:out--%>
                        <%--value="${periodicyCost.cost}"/><br/>--%>
                        <%--</c:forEach>--%>
                        <%--</td>--%>

                    <td>
                        <br>
                        <select size="1" name="curentCostid">
                            <option></option>
                            <c:forEach var="periodicyCost" items="${publWithCost.value}">
                                <option value="${periodicyCost.id}"><c:out value="${periodicyCost.cost}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <p>кількість видань: ${fn:length(publicationListWithCost)}</p>
        <input type="submit" name="createSubscription" value="Оформити підписку">

    </form>
</div>

<div id="publicationsList">
    <form name="cancelSubscription" method="get" action="controller">
        <input type="hidden" name="command" value="cancelEditSubscription">
        <input type="submit" name="showInfo" value="Відмінити оформлення">
    </form>
</div>

</body>
</html>
