<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 18.05.2018
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<c:if test="${locale == 1}"><fmt:setLocale value="en_US" scope="session"/></c:if>
<c:if test="${locale == 2}"><fmt:setLocale value="uk_UA" scope="session"/></c:if>
<fmt:setBundle basename="pagecontent" var="rb"/>

<html>
<head>
    <c:set var="currentPage" value="path.page.aboutUser" scope="request"/>
    <title><fmt:message key="aboutUser.title" bundle="${rb}"/></title>
</head>
<body>

<header>
    <h3><fmt:message key="head.welcome" bundle="${rb}"/> <c:out value="${currentUser.surname}"/> <c:out value="${currentUser.name}"/> <c:out value="${currentUser.lastName}"/></h3>
    <div <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>
        <a href="userPageSubsc.jsp"><fmt:message key="userPage.subscriptions" bundle="${rb}"/></a>
        <a href="userPageBills.jsp"><fmt:message key="userPage.billsTitle" bundle="${rb}"/></a>
    </div>
    <div <c:if test="${currentUser.userRoleId == 2}">HIDDEN</c:if>>
        <a href="users.jsp"><fmt:message key="aboutUser.goToUsers" bundle="${rb}"/></a>
    </div>
    <div <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>
        <a href="controller?command=editUser"><fmt:message key="aboutUser.editUser" bundle="${rb}"/></a><br>
        <%--<a href="userPageSubsc.jsp"><fmt:message key="aboutUser.backToSubs" bundle="${rb}"/></a>--%>
    </div>
    <c:import url="headUserInfo.jsp"/>
</header>

<div>
    <p><b><fmt:message key="aboutUser.user" bundle="${rb}"/></b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><fmt:message key="aboutUser.surname" bundle="${rb}"/></th>
            <td><c:out value="${user.surname}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.name" bundle="${rb}"/></th>
            <td><c:out value="${user.name}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.lastName" bundle="${rb}"/></th>
            <td><c:out value="${user.lastName}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.birthday" bundle="${rb}"/></th>
            <td><c:out value="${user.birthday}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.regDate" bundle="${rb}"/></th>
            <td><c:out value="${user.registrationDate}"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <p><b><fmt:message key="aboutUser.passportData" bundle="${rb}"/></b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><fmt:message key="aboutUser.serialAndNumber" bundle="${rb}"/></th>
            <td><c:out value="${userPassportIdNumb.serial}"/> <c:out value="${userPassportIdNumb.number}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.dateOfIssue" bundle="${rb}"/></th>
            <td><c:out value="${userPassportIdNumb.dateOfIssue}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.issuedBy" bundle="${rb}"/></th>
            <td><c:out value="${userPassportIdNumb.issuedBy}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.identNumber" bundle="${rb}"/></th>
            <td><c:out value="${userPassportIdNumb.idNumber}"/></td>
        </tr>


        </tbody>
    </table>
</div>

<div>
    <p><b><fmt:message key="aboutUser.address" bundle="${rb}"/></b></p>
    <table>
        <tbody>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.region}"/> <fmt:message key="aboutUser.region" bundle="${rb}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.district}"/> <fmt:message key="aboutUser.district" bundle="${rb}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.city}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><c:out value="${userLivingAddress.street}"/>,  <c:out value="${userLivingAddress.building}"/> <fmt:message key="aboutUser.appartment" bundle="${rb}"/> <c:out value="${userLivingAddress.appartment}"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <p><b><fmt:message key="aboutUser.contacts" bundle="${rb}"/></b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><fmt:message key="aboutUser.phone" bundle="${rb}"/></th>
            <td><c:out value="${userContactInfo.phone}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.email" bundle="${rb}"/></th>
            <td><c:out value="${userContactInfo.email}"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>
    <p><b><fmt:message key="aboutUser.authorizationOptions" bundle="${rb}"/></b></p>
    <table>
        <tbody>
        <tr>
            <th align="left"><fmt:message key="aboutUser.login" bundle="${rb}"/></th>
            <td><c:out value="${userAccount.login}"/></td>
        </tr>
        <tr>
            <th align="left"><fmt:message key="aboutUser.password" bundle="${rb}"/></th>
            <td><c:out value="${userAccount.password}"/></td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>