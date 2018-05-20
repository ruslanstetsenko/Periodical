<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Create User</title>
</head>
<body>
<div>
     <form name="createUser" action="create_user" method="post">
         <input type="hidden" name="command" value="okCreateUser">
         <div>
             <p><b>Користувач</b></p>
             <table>
                 <tbody>
                 <tr>
                     <th align="left">Прізвище</th>
                     <td><input type="text" name="userSurName"></td>
                 </tr>
                 <tr>
                     <th align="left">Імя</th>
                     <td><input type="text" name="userName"></td>
                 </tr>
                 <tr>
                     <th align="left">По-батькові</th>
                     <td><input type="text" name="userLastName"></td>
                 </tr>
                 <tr>
                     <th align="left">Дата народження</th>
                     <td><input type="date" name="userBirthDate"></td>
                 </tr>
                 <tr>
                     <th align="left">Реєстрація в системі</th>
                     <td><input type="date" name="userRegistrationDate"></td>
                 </tr>
                 </tbody>
             </table>
         </div>

         <div>
             <p><b>Паспортні дані</b></p>
             <table>
                 <tbody>
                 <tr>
                     <th align="left">Серія</th>
                     <td><input type="text" name="passportSerial"></td>
                 </tr>
                 <tr>
                     <th align="left">Номер</th>
                     <td><input type="text" name="passportNumber"></td>
                 </tr>
                 <tr>
                     <th align="left">Дата видачі</th>
                     <td><input type="date" name="passportDateOfIssue"></td>
                 </tr>
                 <tr>
                     <th align="left">Ким виданий</th>
                     <td><input type="text" name="passportIssuedBy"></td>
                 </tr>
                 <tr>
                     <th align="left">Ідентифікаційний номер</th>
                     <td><input type="text" name="identNuber"></td>
                 </tr>
                 </tbody>
             </table>
         </div>

         <div>
             <p><b>Адреса проживання</b></p>
             <table>
                 <tbody>
                 <tr>
                     <td align="left">Область</td>
                     <td><input type="text" name="region"></td>
                 </tr>
                 <tr>
                     <td align="left">Район</td>
                     <td><input type="text" name="district"></td>
                 </tr>
                 <tr>
                     <td align="left">Населений пункт</td>
                     <td><input type="text" name="city"></td>
                 </tr>
                 <tr>
                     <td>Вулиця</td>
                     <td><input type="text" name="street"></td>
                 </tr>
                 <tr>
                     <td>Номер будинка</td>
                     <td><input type="text" name="building"></td>
                 </tr>
                 <tr>
                     <td>Номер квартири</td>
                     <td><input type="text" name="appartment"></td>
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
                     <td><input type="text" name="userPhoneNumber"></td>
                 </tr>
                 <tr>
                     <th align="left">Електронна адреса</th>
                     <td><input type="text" name="userEmail"></td>
                 </tr>
                 </tbody>
             </table>
         </div>

         <input type="submit" name="createUser" value="Створити користувача">
     </form>

    <form name="createUser" method="post" action="users">
        <input type="hidden" name="command" value="cancelEditUser">
        <input type="submit" name="cancelCreateUser" value="Відміна">
    </form>
</div>
</body>
</html>
