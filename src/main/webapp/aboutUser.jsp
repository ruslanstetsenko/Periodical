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
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <c:if test="${currentUser.userRoleId == 1}">
        <style>
            <%@include file='css/about_user.css' %>
        </style>
    </c:if>
    <c:if test="${currentUser.userRoleId == 2}">
        <style>
            <%@include file='css/about_user1.css' %>
        </style>
    </c:if>



</head>
<body>

<header>
    <div class="header_info">
        <h3><fmt:message key="head.welcome" bundle="${rb}"/>
            <c:out value="${currentUser.surname}"/>
            <c:out value="${currentUser.name}"/>
            <c:out value="${currentUser.lastName}"/>
        </h3>
        <h3 class="import_header"><c:import url="headUserInfo.jsp"/></h3>
    </div>
    <div class="links_user" <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>
        <a class="references" href="userPageSubsc.jsp"><fmt:message key="userPage.subscriptions" bundle="${rb}"/></a>
        <a class="references" href="userPageBills.jsp"><fmt:message key="userPage.billsTitle" bundle="${rb}"/></a>
        <a class="references" href="controller?command=editUser"><fmt:message key="aboutUser.editUser" bundle="${rb}"/></a>
    </div>
    <div class="links_admin" <c:if test="${currentUser.userRoleId == 2}">HIDDEN</c:if>>
        <a class="references" href="users.jsp">
            <fmt:message key="aboutUser.goToUsers" bundle="${rb}"/></a>
    <%--</div>--%>
    <%--<div class="links_user" <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>--%>
    </div>
    <h3 class="header_title"><fmt:message key="aboutUser.user" bundle="${rb}"/></h3>
</header>

<article>
    <div class="about_user_block">
        <div class="personal_info">
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

        <div class="passport_info">
            <p class="blocks_title"><fmt:message key="aboutUser.passportData" bundle="${rb}"/></p>
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

        <div class="address_info">
            <p class="blocks_title"><fmt:message key="aboutUser.address" bundle="${rb}"/></p>
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

        <div class="contact_info">
            <p class="blocks_title"><fmt:message key="aboutUser.contacts" bundle="${rb}"/></p>
            <table>
                <tbody>
                <tr>
                    <%--<th align="left"><fmt:message key="aboutUser.phone" bundle="${rb}"/></th>--%>
                    <td><c:out value="${userContactInfo.phone}"/></td>
                </tr>
                <tr>
                    <%--<th align="left"><fmt:message key="aboutUser.email" bundle="${rb}"/></th>--%>
                    <td><c:out value="${userContactInfo.email}"/></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="autorization_info" <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>
            <p class="blocks_title"><fmt:message key="aboutUser.authorizationOptions" bundle="${rb}"/></p>
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
    </div>
</article>
<footer>

</footer>
</body>
</html>