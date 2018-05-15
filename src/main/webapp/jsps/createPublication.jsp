<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Create publication</title>
</head>
<body>
<div>
    <p>Додавання нового видання до каталогу</p>
    <form name="createPublication" method="post" action="controller">
        <input type="hidden" name="command" value="okCreatePublication">
        <table>
            <tbody>
            <tr>
                <th align="left"><strong>Найменування видання:</strong></th>
                <td><input type="text" name="pubName" placeholder="найменування"></td>
            </tr>
            <tr>
                <th align="left"><strong>ISSN номер видання:</strong></th>
                <td><input type="text" name="ISSN" placeholder="ISSN"></td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Тип видання:</strong></th>
                <td>
                    <c:forEach var="type" items="${publicationTypeList}">
                        <p align="left">
                            <input type="radio" name="type" value="${type.id}"/><c:out value="${type.typeName}"/>
                        </p>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Статус:</strong></th>
                <td>
                    <c:forEach var="status" items="${publicationStatusList}">
                        <p align="left">
                            <input type="radio" name="status" value="${status.id}"/><c:out value="${status.statusName}"/>
                        </p>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Тематика:</strong></th>
                <td>
                    <c:forEach var="theme" items="${publicationThemeList}">
                        <p align="left">
                            <input type="radio" name="theme" value="${theme.id}"/><c:out value="${theme.themeName}"/>
                        </p>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th align="left"><strong>Дата реєстрації:</strong></th>
                <td><input type="date" name="setDate"></td>
            </tr>
            <tr>
                <th align="left"><strong>Вебсайт:</strong></th>
                <td><input type="text" name="website"></td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Вартість передплати:</strong></th>
                <td>
                    <p><input type="text" name="cost1Month" size="5" align="right"/> грн. на 1 місяць</p>
                    <p><input type="text" name="cost3Months" size="5" align="right"/> грн. на 3 місяці</p>
                    <p><input type="text" name="cost6Months" size="5" align="right"/> грн. на 6 місяців</p>
                    <p><input type="text" name="cost12Months" size="5" align="right"/> грн. на 12 місяців</p>
                    <%--<c:out--%>
                    <%--value="${cost.timesPerYear}"/> міс/місяців
    <%--<c:forEach var="cost" items="${publicationPeriodicityCostList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="text" name="cost" size="5" align="right" value="${cost.cost}"/> грн. на--%>
                            <%--<c:out--%>
                                    <%--value="${cost.timesPerYear}"/> міс/місяців--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="submit" name="saveNewPub" value="Додати публікацію">

        <input type="reset" name="clearForm" value="Очистити форму">
    </form>

    <form name="cancelEditPublication" method="post" action="controller">
        <input type="hidden" name="command" value="cancelCreatePublication">
        <input type="submit" name="cancel" value="Відміна">
    </form>
</div>
</body>
</html>
