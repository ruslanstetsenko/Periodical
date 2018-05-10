<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <td>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="showAboutPublication">
                    <button name="publicationId" value="${publication.id}" type="submit">Докладно</button>
                </form>
            </td>
            <td>
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
    <p>тип видання:</p>
    <form name="publicationType" method="get" action="controller">
        <input type="hidden" name="command" value="selectPublicationsAdminWindow">
        <p><select name="select" size="1">
            <option value="s1">газета</option>
            <option value="s2">журнал</option>
            <option value="s3">бюлетень</option>
            <option value="s4">збірник</option>
        </select>
    </form>

    <p>тематика видання:</p>
    <form name="publicationTheme" method="get" action="controller">
        <input type="hidden" name="command" value="selectPublicationsAdminWindow">
        <p><select name="select" size="1">
            <option value="s1">спорт</option>
            <option value="s2">сімя</option>
            <option value="s3">туризм</option>
            <option value="s4">культура</option>
            <option value="s4">наука</option>
            <option value="s4">здоровя</option>
        </select>
    </form>

    <form name="addpublication" method="post" action="controller">
        <input type="hidden" name="command" value="createPublication">
        <input type="submit" name="createPublication" value="Додати нове видання">
    </form>

    <%--<form name="editpublication" method="post" action="controller">--%>
        <%--<input type="hidden" name="command" value="editPublication">--%>
        <%--<input type="submit" name="editPublication" value="Редагувати інформацію про видання">--%>
    <%--</form>--%>

    <%--<form name="showPublicationIngo" method="get" action="controller">--%>
        <%--<input type="hidden" name="command" value="showAboutPublication">--%>
        <%--<input type="submit" name="showInfo" value="Детальна інформація">--%>
    <%--</form>--%>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>кількість рахунків: ${fn:length(subscriptionBillList)}</p>
</div>

<div>
    <p>статус рахунку</p>
    <form name="billType" method="get" action="controller">
        <input type="hidden" name="command" value="selectBillsByStatus">
        <p><select name="select" size="1">
            <option value="s1">оплачений</option>
            <option value="s2">не оплачений</option>
        </select>
    </form>

    <form name="showBillIngo" method="get" action="controller">
        <input type="hidden" name="command" value="showAboutBill">
        <input type="submit" name="showInfo" value="детальна інформація">
    </form>
</div>
</body>
</html>
