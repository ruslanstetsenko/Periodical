<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>About publication</title>
</head>
<body>
<div>
    <p>Інформація про видання</p>
    <table>
        <tbody>
        <tr>
            <th align="left"><strong>Найменування видання:</strong></th>
            <th><c:out value=""/></th>
        </tr>
        <tr>
            <th align="left"><strong>ISSN номер видання:</strong></th>
            <th><c:out value=""/></th>
        </tr>
        <tr>
            <th align="left"><strong>Тип видання:</strong></th>
            <th><c:out value=""/></th>
        </tr>
        <tr>
            <th align="left"><strong>Статус:</strong></th>
            <th><c:out value=""/></th>
        </tr>
        <tr>
            <th align="left"><strong>Тематика:</strong></th>
            <th><c:out value=""/></th>
        </tr>
        <tr>
            <th align="left"><strong>Дата реєстрації:</strong></th>
            <th><c:out value=""/></th>
        </tr>
        <tr>
            <th align="left"><strong>Вебсайт:</strong></th>
            <th><c:out value=""/></th>
        </tr>
        </tbody>
    </table>
    <div>
        <p>варіанти підписки:</p>
        <table>
            <tbody>
                <tr>
                    <td align="right">на 1 місяць: <c:out value=""/></td>
                    <td align="right">на 3 місяці: <c:out value=""/></td>
                    <td align="right">на 6 місяців: <c:out value=""/></td>
                    <td align="right">на 12 місяців: <c:out value=""/></td>
                </tr>
            </tbody>
        </table>
    </div>


</div>

</body>
</html>
