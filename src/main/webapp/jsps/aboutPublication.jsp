<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Publication information</title>
</head>
<body>
<div>
    <p>Інформація про видання</p>
    <table>
        <tbody>
        <tr>
            <th align="left"><strong>Найменування видання:</strong></th>
            <th><c:out value="${publication.name}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>ISSN номер видання:</strong></th>
            <th align="left"><c:out value="${publication.issnNumber}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Тип видання:</strong></th>
            <th align="left"><c:out value="${publicationType.typeName}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Статус:</strong></th>
            <th align="left"><c:out value="${publicationStatus.statusName}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Тематика:</strong></th>
            <th align="left"><c:out value="${publicationTheme.themeName}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Дата реєстрації:</strong></th>
            <th align="left"><c:out value="${publication.registrationDate}"/></th>
        </tr>
        <tr>
            <th align="left"><strong>Вебсайт:</strong></th>
            <th align="left"><c:out value="${publication.website}"/></th>
        </tr>
        </tbody>
    </table>
    <div>
        <p></p>
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
    </div>

    <form name="backPrevPage" method="post" action="controller">
        <input type="hidden" name="command" value="cancelEditPublication">
        <input type="submit" name="cancel" value="До вікна користувача">
    </form>
</div>

</body>
</html>
