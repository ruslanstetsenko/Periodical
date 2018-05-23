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
    <c:set var="currentPage" value="path.page.createUser" scope="request"/>
    <title><fmt:message key="aboutUser.createUserTitle" bundle="${rb}"/></title>
</head>
<body>
<div>
     <form name="createUser" action="create_user" method="post">
         <input type="hidden" name="command" value="okCreateUser">
         <div>
             <p><b><fmt:message key="aboutUser.user" bundle="${rb}"/></b></p>
             <table>
                 <tbody>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.surname" bundle="${rb}"/></th>
                     <td><input type="text" name="userSurName"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.name" bundle="${rb}"/></th>
                     <td><input type="text" name="userName"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.lastName" bundle="${rb}"/></th>
                     <td><input type="text" name="userLastName"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.birthday" bundle="${rb}"/></th>
                     <td><input type="date" name="userBirthDate" onkeydown="return false"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.regDate" bundle="${rb}"/></th>
                     <td><input type="date" name="userRegistrationDate" onkeydown="return false"></td>
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
                     <td><input type="text" name="passportSerial"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.passportNumber" bundle="${rb}"/></th>
                     <td><input type="text" name="passportNumber"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.dateOfIssue" bundle="${rb}"/></th>
                     <td><input type="date" name="passportDateOfIssue" onkeydown="return false"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.issuedBy" bundle="${rb}"/></th>
                     <td><input type="text" name="passportIssuedBy"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.identNumber" bundle="${rb}"/></th>
                     <td><input type="text" name="identNuber"></td>
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
                     <td><input type="text" name="region"></td>
                 </tr>
                 <tr>
                     <td align="left"><fmt:message key="aboutUser.district" bundle="${rb}"/></td>
                     <td><input type="text" name="district"></td>
                 </tr>
                 <tr>
                     <td align="left"><fmt:message key="aboutUser.city" bundle="${rb}"/></td>
                     <td><input type="text" name="city"></td>
                 </tr>
                 <tr>
                     <td><fmt:message key="aboutUser.street" bundle="${rb}"/></td>
                     <td><input type="text" name="street"></td>
                 </tr>
                 <tr>
                     <td><fmt:message key="aboutUser.buildingNumber" bundle="${rb}"/></td>
                     <td><input type="text" name="building"></td>
                 </tr>
                 <tr>
                     <td><fmt:message key="aboutUser.appartment" bundle="${rb}"/></td>
                     <td><input type="text" name="appartment"></td>
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
                     <td><input type="text" name="userPhoneNumber"></td>
                 </tr>
                 <tr>
                     <th align="left"><fmt:message key="aboutUser.email" bundle="${rb}"/></th>
                     <td><input type="text" name="userEmail"></td>
                 </tr>
                 </tbody>
             </table>
         </div>

         <input type="submit" name="createUser" value="<fmt:message key="aboutUser.createUser" bundle="${rb}"/>">
     </form>

    <form name="createUser" method="post" action="users">
        <input type="hidden" name="command" value="cancelEditUser">
        <input type="submit" name="cancelCreateUser" value="<fmt:message key="button.cancel" bundle="${rb}"/>">
    </form>
</div>
</body>
</html>
