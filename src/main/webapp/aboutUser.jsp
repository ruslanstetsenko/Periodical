<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 18.05.2018
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>About user</title>
</head>
<body>

<div>
    <p><b>Користувач</b></p>
    <table>
        <tbody>
        <tr>
            <th align="left">Прізвище</th>
            <td><c:out value="${user.name}"/></td>
        </tr>
        <tr>
            <th align="left">Імя</th>
            <td><c:out value="${user.surname}"/></td>
        </tr>
        <tr>
            <th align="left">По-батькові</th>
            <td><c:out value="${user.lastName}"/></td>
        </tr>
        <tr>
            <th align="left">Дата народження</th>
            <td><c:out value="${user.birthday}"/></td>
        </tr>
        <tr>
            <th align="left">Реєстрація в системі</th>
            <td><c:out value="${user.registrationDate}"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <p><b>Паспортні дані</b></p>
    <table>
        <tbody>
        <tr>
            <th align="left">Серія та номе</th>
            <td><c:out value="${userPassportIdNumb.serial}"/> <c:out value="${userPassportIdNumb.number}"/></td>
        </tr>
        <tr>
            <th align="left">Дата видачі</th>
            <td><c:out value="${userPassportIdNumb.dateOfIssue}"/></td>
        </tr>
        <tr>
            <th align="left">Ким виданий</th>
            <td><c:out value="${userPassportIdNumb.issuedBy}"/></td>
        </tr>
        <tr>
            <th align="left">Ідентифікаційний номер</th>
            <td><c:out value="${userPassportIdNumb.idNumber}"/></td>
        </tr>


        </tbody>
    </table>
</div>

<div>
    <p><b>Адреса проживання</b></p>
    <table>
        <tbody>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.region}"/> область</td>
        </tr>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.district}"/> район</td>
        </tr>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.city}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.street}"/>,  <c:out value="${userLivingAddress.building}"/> кв <c:out value="${userLivingAddress.appartment}"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <p><b>Контати</b></p>
    <table>
        <tbody>
        <tr>
            <th align="left">Телефон</th>
            <td><c:out value="${userContactInfo.phone}"/></td>
        </tr>
        <tr>
            <th align="left">Електронна адреса</th>
            <td><c:out value="${userContactInfo.email}"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <a href="users.jsp">До переліку користувачів</a>
</div>



</body>
</html>
