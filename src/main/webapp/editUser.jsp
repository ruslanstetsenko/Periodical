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
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/about_user.css' %>
    </style>
</head>
<body>
<header>
    <h3 class="header_title1"><fmt:message key="aboutUser.user" bundle="${rb}"/></h3>
</header>

<article>
    <div class="about_user_block1">
        <form name="editUser" method="post" action="to_user_list">
            <%--<input type="hidden" name="command" value="okEditUser">--%>
            <div>
                <table>
                    <tbody>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.surname" bundle="${rb}"/></th>
                        <td>
                            <input class="input_FIO" type="text" name="userSurName" value="${userSurName}">
                            <span class="error">
                            <c:if test="${incorectSurname}">
                                <fmt:message key="validation.user.surName" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.name" bundle="${rb}"/></th>
                        <td>
                            <input class="input_FIO" type="text" name="userName" value="${userName}">
                            <span class="error">
                            <c:if test="${incorectName}">
                                <fmt:message key="validation.user.name" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.lastName" bundle="${rb}"/></th>
                        <td>
                            <input class="input_FIO" type="text" name="userLastName" value="${userLastName}">
                            <span class="error">
                            <c:if test="${incorectLastName}">
                                <fmt:message key="validation.user.lastName" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.birthday" bundle="${rb}"/></th>
                        <td>
                            <input class="input_date" type="date" name="userBirthDate" value="${userBirthDate}"
                                   onkeydown="return false">
                            <span class="error">
                            <c:if test="${incorectUserBirthDate}">
                                <fmt:message key="validation.user.userBirthDate" bundle="${validation}"/>
                            </c:if>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.regDate" bundle="${rb}"/></th>
                        <td>
                            <input class="input_date" type="date" name="userRegistrationDate"
                                   value="${userRegistrationDate}"
                                   onkeydown="return false" disabled>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="passport_info">
                <p class="blocks_title"><fmt:message key="aboutUser.passportData" bundle="${rb}"/></p>
                <table>
                    <tbody>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.passportSerial" bundle="${rb}"/></th>
                        <td>
                            <input class="input_passpNoSerial" type="text" name="passportSerial"
                                   value="${passportSerial}">
                            <span class="error">
                            <c:if test="${incorectPassportSerial}">
                                <fmt:message key="validation.passport.serial" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.passportNumber" bundle="${rb}"/></th>
                        <td>
                            <input class="input_passpNoSerial" type="text" name="passportNumber"
                                   value="${passportNumber}">
                            <span class="error">
                            <c:if test="${incorectPassportNumber}">
                                <fmt:message key="validation.passport.number" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.dateOfIssue" bundle="${rb}"/></th>
                        <td>
                            <input class="input_date" type="date" name="passportDateOfIssue"
                                   value="${passportDateOfIssue}"
                                   onkeydown="return false">
                            <span class="error">
                            <c:if test="${incorectParrportDateOfIssue}">
                                <fmt:message key="validation.passport.DateOfIssue" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.issuedBy" bundle="${rb}"/></th>
                        <td>
                            <textarea class="input_passpIssuedBy" type="text"
                                      name="passportIssuedBy">${passportIssuedBy}</textarea>
                            <span class="error">
                            <c:if test="${incorectPassportIssuedBy}">
                                <fmt:message key="validation.passport.issuedBy" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.identNumber" bundle="${rb}"/></th>
                        <td>
                            <input class="input_ID" type="text" name="identNuber" value="${identNuber}">
                            <span class="error">
                            <c:if test="${incorectIdentNuber}">
                                <fmt:message key="validation.identNuber" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="address_info">
                <p class="blocks_title"><fmt:message key="aboutUser.address" bundle="${rb}"/></p>
                <table>
                    <tbody>
                    <tr>
                        <td align="left"><fmt:message key="aboutUser.region" bundle="${rb}"/></td>
                        <td>
                            <input class="input_address1" type="text" name="region" value="${region}">
                            <span class="error">
                            <c:if test="${incorectRegion}">
                                <fmt:message key="validation.address.region" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td align="left"><fmt:message key="aboutUser.district" bundle="${rb}"/></td>
                        <td>
                            <input class="input_address1" type="text" name="district" value="${district}">
                            <span class="error">
                            <c:if test="${incorectDistrict}">
                                <fmt:message key="validation.address.district" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td align="left"><fmt:message key="aboutUser.city" bundle="${rb}"/></td>
                        <td>
                            <input class="input_address1" type="text" name="city" value="${city}">
                            <span class="error">
                            <c:if test="${incorectCity}">
                                <fmt:message key="validation.address.city" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="aboutUser.street" bundle="${rb}"/></td>
                        <td>
                            <input class="input_address1" type="text" name="street" value="${street}">
                            <span class="error">
                            <c:if test="${incorectStreet}">
                                <fmt:message key="validation.address.street" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="aboutUser.buildingNumber" bundle="${rb}"/></td>
                        <td>
                            <input class="input_address2" type="text" name="building" value="${building}">
                            <span class="error">
                            <c:if test="${incorectBuildingNumber}">
                                <fmt:message key="validation.address.building" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="aboutUser.appartment" bundle="${rb}"/></td>
                        <td>
                            <input class="input_address2" type="text" name="appartment" value="${appartment}">
                            <span class="error">
                            <c:if test="${incorectAppartment}">
                                <fmt:message key="validation.address.appartment" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="contact_info">
                <p class="blocks_title"><fmt:message key="aboutUser.contacts" bundle="${rb}"/></p>
                <table>
                    <tbody>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.phone" bundle="${rb}"/></th>
                        <td>
                            <input class="input_contacts" type="text" name="userPhoneNumber" value="${userPhoneNumber}">
                            <span class="error">
                            <c:if test="${incorectPhoneNumber}">
                                <fmt:message key="validation.user.phoneNumber" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.email" bundle="${rb}"/></th>
                        <td>
                            <input class="input_contacts" type="text" name="userEmail" value="${userEmail}">
                            <span class="error">
                            <c:if test="${incorectEmail}">
                                <fmt:message key="validation.user.email" bundle="${validation}"/>
                            </c:if>
                        </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>
                <p class="blocks_title"><fmt:message key="aboutUser.authorizationOptions" bundle="${rb}"/></p>
                <table>
                    <tbody>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.login" bundle="${rb}"/></th>
                        <td>
                            <%--<input class="input_contacts" type="text" name="login" value="${login}" disabled>--%>
                            ${login}
                        </td>
                    </tr>
                    <tr>
                        <th align="left"><fmt:message key="aboutUser.password" bundle="${rb}"/></th>
                        <td>
                            <%--input class="input_contacts" type="text" name="password" value="${password}" disabled>--%>
                            ${password}
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <%--<input type="submit" name="createUser" value="<fmt:message key="button.save" bundle="${rb}"/>">--%>
            <div class="buttons1">
                <button class="button" type="submit" name="command" value="okEditUser">
                    <fmt:message key="button.save" bundle="${rb}"/>
                </button>
                <button class="button" type="submit" name="command" value="cancelEditUser">
                    <fmt:message key="button.cancel" bundle="${rb}"/>
                </button>
            </div>

        </form>
    </div>
</article>
<div>
    <%--<form name="cancalEditUser" method="post" action="users">--%>
    <%--<input type="hidden" name="command" value="cancelEditUser">--%>
    <%--<input type="submit" name="cancelEdit" value="<fmt:message key="button.cancel" bundle="${rb}"/>">--%>
    <%--</form>--%>
</div>

<footer>

</footer>
<%--<div <c:if test="${currentUser.userRoleId == 1}">HIDDEN</c:if>>--%>
<%--<a href="users.jsp">До переліку користувачів</a>--%>
<%--</div>--%>

</body>
</html>
