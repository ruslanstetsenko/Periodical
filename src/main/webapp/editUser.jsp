<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 18:38
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
    <c:set var="currentPage" value="path.page.editUser" scope="request"/>
    <title><fmt:message key="aboutUser.editUserTitle" bundle="${rb}"/></title>
</head>
<body>
<div>
    <form name="createUser" action="users" method="post">
        <input type="hidden" name="command" value="okEditUser">
        <div>
            <p><b><fmt:message key="aboutUser.user" bundle="${rb}"/></b></p>
            <table>
                <tbody>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.surname" bundle="${rb}"/></th>
                    <td><input type="text" name="userSurName" value="${user.surname}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.name" bundle="${rb}"/></th>
                    <td><input type="text" name="userName" value="${user.name}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.lastName" bundle="${rb}"/></th>
                    <td><input type="text" name="userLastName" value="${user.lastName}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.birthday" bundle="${rb}"/></th>
                    <td><input type="date" name="userBirthDate" value="${user.birthday}" onkeydown="return false"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.regDate" bundle="${rb}"/></th>
                    <td><input type="date" name="userRegistrationDate" value="${user.registrationDate}" onkeydown="return false"></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div>
            <p><b><fmt:message key="aboutUser.passportData" bundle="${rb}"/></b></p>
            <table>
                <tbody>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.passportSerial" bundle="${rb}"/></th>
                    <td><input type="text" name="passportSerial" value="${userPassportIdNumb.serial}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.passportNumber" bundle="${rb}"/></th>
                    <td><input type="text" name="passportNumber" value="${userPassportIdNumb.number}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.dateOfIssue" bundle="${rb}"/></th>
                    <td><input type="date" name="passportDateOfIssue" value="${userPassportIdNumb.dateOfIssue}" onkeydown="return false"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.issuedBy" bundle="${rb}"/></th>
                    <td><input type="text" name="passportIssuedBy" value="${userPassportIdNumb.issuedBy}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.identNumber" bundle="${rb}"/></th>
                    <td><input type="text" name="identNuber" value="${userPassportIdNumb.idNumber}"></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div>
            <p><b><fmt:message key="aboutUser.address" bundle="${rb}"/></b></p>
            <table>
                <tbody>
                <tr>
                    <td align="left"><fmt:message key="aboutUser.region" bundle="${rb}"/></td>
                    <td><input type="text" name="region" value="${userLivingAddress.region}"></td>
                </tr>
                <tr>
                    <td align="left"><fmt:message key="aboutUser.district" bundle="${rb}"/></td>
                    <td><input type="text" name="district" value="${userLivingAddress.district}"></td>
                </tr>
                <tr>
                    <td align="left"><fmt:message key="aboutUser.city" bundle="${rb}"/></td>
                    <td><input type="text" name="city" value="${userLivingAddress.city}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="aboutUser.street" bundle="${rb}"/></td>
                    <td><input type="text" name="street" value="${userLivingAddress.street}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="aboutUser.buildingNumber" bundle="${rb}"/></td>
                    <td><input type="text" name="building" value="${userLivingAddress.building}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="aboutUser.appartment" bundle="${rb}"/></td>
                    <td><input type="text" name="appartment" value="${userLivingAddress.appartment}"></td>
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
                    <td><input type="text" name="userPhoneNumber" value="${userContactInfo.phone}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.email" bundle="${rb}"/></th>
                    <td><input type="text" name="userEmail" value="${userContactInfo.email}"></td>
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
                    <td><input type="text" name="login" value="${userAccount.login}"></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.password" bundle="${rb}"/></th>
                    <td><input type="text" name="password" value="${userAccount.password}"></td>
                </tr>
                </tbody>
            </table>
        </div>

        <input type="submit" name="createUser" value="<fmt:message key="button.save" bundle="${rb}"/>">
    </form>

    <div>
        <form name="cancalEditUser" method="post" action="users">
            <input type="hidden" name="command" value="cancelEditUser">
            <input type="submit" name="cancelEdit" value="<fmt:message key="button.cancel" bundle="${rb}"/>">
        </form>
    </div>

    <%--<div <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>--%>
        <%--<a href="users.jsp">До переліку користувачів</a>--%>
    <%--</div>--%>


</div>
</body>
</html>
