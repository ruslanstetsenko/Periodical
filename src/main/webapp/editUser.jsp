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
<fmt:setBundle basename="message" var="validation"/>

<html>
<head>
    <c:set var="currentPage" value="path.page.editUser" scope="request"/>
    <title><fmt:message key="aboutUser.editUserTitle" bundle="${rb}"/></title>
</head>
<body>
<div>
    <form name="editUser" action="users" method="post">
        <input type="hidden" name="command" value="okEditUser">
        <div>
            <p><b><fmt:message key="aboutUser.user" bundle="${rb}"/></b></p>
            <table>
                <tbody>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.surname" bundle="${rb}"/></th>
                    <td><input type="text" name="userSurName" value="${userSurName}"></td>
                    <td>
                        <c:if test="${incorectSurname}">
                            <fmt:message key="validation.user.surName" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.name" bundle="${rb}"/></th>
                    <td><input type="text" name="userName" value="${userName}"></td>
                    <td>
                        <c:if test="${incorectName}">
                            <fmt:message key="validation.user.name" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.lastName" bundle="${rb}"/></th>
                    <td><input type="text" name="userLastName" value="${userLastName}"></td>
                    <td>
                        <c:if test="${incorectLastName}">
                            <fmt:message key="validation.user.lastName" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.birthday" bundle="${rb}"/></th>
                    <td><input type="date" name="userBirthDate" value="${userBirthDate}" onkeydown="return false"></td>
                    <td>
                        <c:if test="${incorectUserBirthDate}">
                            <fmt:message key="validation.user.userBirthDate" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.regDate" bundle="${rb}"/></th>
                    <td><input type="date" name="userRegistrationDate" value="${userRegistrationDate}"
                               onkeydown="return false" disabled>
                    </td>
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
                    <td><input type="text" name="passportSerial" value="${passportSerial}"></td>
                    <td>
                        <c:if test="${incorectPassportSerial}">
                            <fmt:message key="validation.passport.serial" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.passportNumber" bundle="${rb}"/></th>
                    <td><input type="text" name="passportNumber" value="${passportNumber}"></td>
                    <td>
                        <c:if test="${incorectPassportNumber}">
                            <fmt:message key="validation.passport.number" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.dateOfIssue" bundle="${rb}"/></th>
                    <td><input type="date" name="passportDateOfIssue" value="${passportDateOfIssue}"
                               onkeydown="return false"></td>
                    <td>
                        <c:if test="${incorectParrportDateOfIssue}">
                            <fmt:message key="validation.passport.DateOfIssue" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.issuedBy" bundle="${rb}"/></th>
                    <td><input type="text" name="passportIssuedBy" value="${passportIssuedBy}"></td>
                    <td>
                        <c:if test="${incorectPassportIssuedBy}">
                            <fmt:message key="validation.passport.issuedBy" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.identNumber" bundle="${rb}"/></th>
                    <td><input type="text" name="identNuber" value="${identNuber}"></td>
                    <td>
                        <c:if test="${incorectIdentNuber}">
                            <fmt:message key="validation.identNuber" bundle="${validation}"/>
                        </c:if>
                    </td>
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
                    <td><input type="text" name="region" value="${region}"></td>
                    <td>
                        <c:if test="${incorectRegion}">
                            <fmt:message key="validation.address.region" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="left"><fmt:message key="aboutUser.district" bundle="${rb}"/></td>
                    <td><input type="text" name="district" value="${district}"></td>
                    <td>
                        <c:if test="${incorectDistrict}">
                            <fmt:message key="validation.address.district" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td align="left"><fmt:message key="aboutUser.city" bundle="${rb}"/></td>
                    <td><input type="text" name="city" value="${city}"></td>
                    <td>
                        <c:if test="${incorectCity}">
                            <fmt:message key="validation.address.city" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="aboutUser.street" bundle="${rb}"/></td>
                    <td><input type="text" name="street" value="${street}"></td>
                    <td>
                        <c:if test="${incorectStreet}">
                            <fmt:message key="validation.address.street" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="aboutUser.buildingNumber" bundle="${rb}"/></td>
                    <td><input type="text" name="building" value="${building}"></td>
                    <td>
                        <c:if test="${incorectBuildingNumber}">
                            <fmt:message key="validation.address.building" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="aboutUser.appartment" bundle="${rb}"/></td>
                    <td><input type="text" name="appartment" value="${appartment}"></td>
                    <td>
                        <c:if test="${incorectAppartment}">
                            <fmt:message key="validation.address.appartment" bundle="${validation}"/>
                        </c:if>
                    </td>
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
                    <td><input type="text" name="userPhoneNumber" value="${userPhoneNumber}"></td>
                    <td>
                        <c:if test="${incorectPhoneNumber}">
                            <fmt:message key="validation.user.phoneNumber" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.email" bundle="${rb}"/></th>
                    <td><input type="text" name="userEmail" value="${userEmail}"></td>
                    <td>
                        <c:if test="${incorectEmail}">
                            <fmt:message key="validation.user.email" bundle="${validation}"/>
                        </c:if>
                    </td>
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
                    <td><input type="text" name="login" value="${login}" disabled></td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutUser.password" bundle="${rb}"/></th>
                    <td><input type="text" name="password" value="${password}" disabled></td>
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
