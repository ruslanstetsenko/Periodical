<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Admin page</title>
</head>
<body>
<div>
    <p>Перелік періодичних видань</p>
    <table>
        <thead>
        <tr>
            <th>Найменування</th>
            <th>ISSN</th>
            <th>Дата реєстрації</th>
            <th>Вебсайт</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="publication" items="${publicationList}">
            <tr>
                <td><c:out value="${publication.name}"/></td>
                <td align="right"><c:out value="${publication.issnNumber}"/></td>
                <td align="right"><c:out value="${publication.registrationDate}"/></td>
                <td align="right"><c:out value="${publication.website}"/></td>
                <td valign="bottom ">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="aboutPublication">
                        <button name="publicationId" value="${publication.id}" type="submit">Докладно</button>
                    </form>
                </td>
                <td valign="bottom ">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="editPublication">
                        <button name="publicationId" value="${publication.id}" type="submit">Редагувати</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість видань: ${fn:length(publicationList)}</p>
</div>

<div>
    <p>Перелік рахунків</p>
    <table>
        <thead>
        <tr>
            <th>Номер рахунку</th>
            <th>Дата виставлення рахунку</th>
            <th>Загальна вартість, грн</th>
            <th>Статус</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bill" items="${subscriptionBillList}">
            <tr>
                <td><c:out value="${bill.billNumber}"/></td>
                <td align="right"><c:out value="${bill.billSetDay}"/></td>
                <td align="right"><c:out value="${bill.totalCost}"/></td>
                <td align="right"><c:out value="${bill.paid}"/></td>
                <td>
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="showAboutBill">
                        <button name="currentBillPaidId" value="${bill.id}" type="submit">Докладно</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість рахунків: ${fn:length(subscriptionBillList)}</p>
</div>

<div>
    <form name="publicationType" method="get" action="controller">
        <input type="hidden" name="command" value="selectPublicationsAdminWindow">
        <div>
            <p><b>тип видання:</b></p>
            <input type="radio" name="currentPubTypeId" value="0" <c:if test="${currentPubTypeId == 0}">CHECKED</c:if>/>показати
            всі
            <c:forEach var="type" items="${publicationTypeList}">
                <p align="left">
                    <input type="radio" name="currentPubTypeId" value="${type.id}"
                           <c:if test="${currentPubTypeId == type.id}">CHECKED</c:if>/><c:out
                        value="${type.typeName}"/>
                </p>
            </c:forEach>
        </div>

        <div>
            <p><b>тематика видання:</b></p>
            <input type="radio" name="currentPubThemeId" value="0" <c:if test="${currentPubThemeId == 0}">CHECKED</c:if>/>показати
            всі
            <c:forEach var="theme" items="${publicationThemeList}">
                <p align="left">
                    <input type="radio" name="currentPubThemeId" value="${theme.id}"
                           <c:if test="${currentPubThemeId == theme.id}">CHECKED</c:if>/><c:out
                        value="${theme.themeName}"/>
                </p>
            </c:forEach>

            <%--<p><select size="1">--%>
            <%--<option name="currentPubThemeId" value="0" <c:if test="${currentPubThemeId == 0}">SELECTED</c:if>>--%>
            <%--показати--%>
            <%--всі--%>
            <%--</option>--%>
            <%--<c:forEach var="theme" items="${publicationThemeList}">--%>
            <%--<option name="currentPubThemeId" value="${theme.id}"--%>
            <%--<c:if test="${currentPubThemeId == theme.id}">SELECTED</c:if>><c:out--%>
            <%--value="${theme.themeName}"/></option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
        </div>

        <div>
            <p><b>статус видання:</b></p>
            <input type="radio" name="currentPubStatusId" value="0" <c:if test="${currentPubStatusId == 0}">CHECKED</c:if>/>показати
            всі
            <c:forEach var="status" items="${publicationStatusList}">
                <p align="left">
                    <input type="radio" name="currentPubStatusId" value="${status.id}"
                           <c:if test="${currentPubStatusId == status.id}">CHECKED</c:if>/><c:out
                        value="${status.statusName}"/>
                </p>
            </c:forEach>
            <%--<p><select name="selectStatus" size="1">--%>
                <%--<option name="currentPubStatusId" value="0" <c:if test="${currentPubStatusId == 0}">SELECTED</c:if>>--%>
                    <%--показати--%>
                    <%--всі--%>
                <%--</option>--%>
                <%--<c:forEach var="status" items="${publicationStatusList}">--%>
                    <%--<option name="currentPubStatusId" value="${status.id}"--%>
                            <%--<c:if test="${currentPubStatusId == status.id}">SELECTED</c:if>><c:out--%>
                            <%--value="${status.statusName}"/></option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>
        </div>

        <div>
            <p><b>статус рахунку</b></p>
            <p><input type="radio" name="currentBillPaidId" value="0" <c:if test="${currentBillPaidId == 0}">CHECKED</c:if>/>показати всі</p>
            <p><input type="radio" name="currentBillPaidId" value="1" <c:if test="${currentBillPaidId == 1}">CHECKED</c:if>/>оплачений</p>
            <p><input type="radio" name="currentBillPaidId" value="2" <c:if test="${currentBillPaidId == 2}">CHECKED</c:if>/>не оплачений</p>

            <%--<p><select size="1">--%>
                <%--<option name="currentBillPaidId" value="0" <c:if test="${currentBillPaidId == 0}">SELECTED</c:if>>--%>
                    <%--показати всі--%>
                <%--</option>--%>
                <%--<option name="currentBillPaidId" value="1" <c:if test="${currentBillPaidId == 1}">SELECTED</c:if>>--%>
                    <%--оплачений--%>
                <%--</option>--%>
                <%--<option name="currentBillPaidId" value="2" <c:if test="${currentBillPaidId == 2}">SELECTED</c:if>>не--%>
                    <%--оплачений--%>
                <%--</option>--%>
            <%--</select>--%>
        </div>

        <input type="submit" name="useFilters" value="Задіяти фільтри">
    </form>

    <form name="addpublication" method="post" action="controller">
        <input type="hidden" name="command" value="createPublication">
        <input type="submit" name="createPublication" value="Додати нове видання">
    </form>

</div>
</body>
</html>
