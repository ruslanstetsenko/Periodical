<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 14.05.2018
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <p><b>Про підписку</b></p>
    <table>
        <tbody>
        <tr>
            <th align="left">Найменування видання:</th>
            <td align="left"><c:out value="${publicationName}"/></td>
        </tr>
        <tr>
            <th align="left">Дата реєстрації:</th>
            <td align="left"><c:out value="${subscriptionDate}"/></td>
        </tr>
        <tr>
            <th align="left">Вартість підписки:</th>
            <td align="left"><c:out value="${subscriptionCost}"/></td>
        </tr>
        <tr>
            <th align="left">Статус:</th>
            <td align="left"><c:out value="${subscriptionStatusId}"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <p><b>Про видання</b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><strong>Найменування видання:</strong></th>
            <td><c:out value="${publication.name}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>ISSN номер видання:</strong></th>
            <td align="left"><c:out value="${publication.issnNumber}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Тип видання:</strong></th>
            <td align="left"><c:out value="${publicationType.typeName}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Статус:</strong></th>
            <td align="left"><c:out value="${publicationStatus.statusName}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Тематика:</strong></th>
            <td align="left"><c:out value="${publicationTheme.themeName}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Дата реєстрації:</strong></th>
            <td align="left"><c:out value="${publication.registrationDate}"/></td>
        </tr>
        <tr>
            <th align="left"><strong>Вебсайт:</strong></th>
            <td align="left"><c:out value="${publication.website}"/></td>
        </tr>
        </tbody>
    </table>

    <table>
        <tbody>
        <tr>
            <th valign="top">варіанти підписки:</th>
            <td>
                <c:forEach var="cost" items="${publicationPeriodicityCostList}">
                    на <c:out value="${cost.timesPerYear}"/> міс/місяців: <c:out value="${cost.cost}"/> грн.<br>
                </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>

    <form name="backPrevPage" method="post" action="controller">
        <input type="hidden" name="command" value="cancelEditSubscription">
        <input type="submit" name="cancel" value="До вікна користувача">
    </form>
</div>


</body>
</html>
