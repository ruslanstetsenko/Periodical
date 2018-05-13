<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true"%>
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
        <input type="hidden" name="command" value="createPublication">
        <table>
            <tbody>
            <tr>
                <th align="left"><strong>Найменування видання:</strong></th>
                <th><input type="text" name="pubName" placeholder="найменування"></th>
            </tr>
            <tr>
                <th align="left"><strong>ISSN номер видання:</strong></th>
                <th><input type="text" name="ISSN" placeholder="ISSN"></th>
            </tr>
            <tr>
                <th align="left"><strong>Тип видання:</strong></th>
                <th>
                    <select name="selectPubType" size="1">
                        <option name="type1" value="">газета</option>
                        <option name="type2" value="">журнал</option>
                        <option name="type3" value="">бюлетень</option>
                        <option name="type4" value="">збірник</option>
                    </select>
                </th>
            </tr>
            <tr>
                <th align="left"><strong>Статус:</strong></th>
                <th>
                    <select name="selectPubStatus" size="1">
                        <option name="status1" value="">діюча</option>
                        <option name="status2" value="">не діюча</option>
                    </select>
                </th>
            </tr>
            <tr>
                <th align="left"><strong>Тематика:</strong></th>
                <th>
                    <select name="selectPubTheme" size="1">
                        <option name="theme1" value="">спорт</option>
                        <option name="theme2" value="">сімя</option>
                        <option name="theme3" value="">туризм </option>
                        <option name="theme4" value="">культура</option>
                        <option name="theme5" value="">наука</option>
                        <option name="theme6" value="">здоровя </option>
                    </select>
                </th>
            </tr>
            <tr>
                <th align="left"><strong>Дата реєстрації:</strong></th>
                <th><c:out value=""/></th>
            </tr>
            <tr>
                <th align="left"><strong>Вебсайт:</strong></th>
                <th><c:out value=""/></th>
            </tr>
            <tr>
                <th align="left"><strong>Вартість передплати:</strong></th>
                <th>
                    <select name="selectSubsCost" size="1">
                        <option name="cost1" value="">, грн. на місяць</option>
                        <option name="cost2" value="">, грн. на 3 місяці</option>
                        <option name="cost3" value="">, грн. на 6 місяців</option>
                        <option name="cost4" value="">, грн. на 12 місяців</option>
                    </select>
                </th>
            </tr>

            </tbody>
        </table>
        <input type="submit" name="saveNewPub" value="Додати публікацію">
        <input type="reset" name="clearForm" value="Очистити форму">
    </form>
    <input type="submit" name="cancel" value="Відміна додавання" onclick="history.back()">
</div>
</body>
</html>
