<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
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
    <p>Редагування інформації про видання</p>
    <form name="editPublication" method="post" action="controller">
        <input type="hidden" name="command" value="okEditPublication">
        <table>
            <tbody>
            <tr>
                <th align="left"><strong>Найменування видання:</strong></th>
                <th><input type="text" name="pubName" value="${publication.name}"></th>
            </tr>
            <tr>
                <th align="left"><strong>ISSN номер видання:</strong></th>
                <th><input type="text" name="ISSN" value="${publication.issnNumber}"></th>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Тип видання:</strong></th>
                <td>
                    <%--<c:forEach var="type" items="${publicationTypeList}">--%>
                    <%--<p align="left">--%>
                    <%--<input type="radio" name="type" value="${type.id}"--%>
                    <%--<c:if test="${publication.publicationTypeId == type.id}">CHECKED</c:if>/><c:out--%>
                    <%--value="${type.typeName}"/>--%>
                    <%--</p>--%>
                    <%--</c:forEach>--%>

                    <select size="1" name="type">
                        <c:forEach var="type" items="${publicationTypeList}">
                            <option value="${type.id}" <c:if test="${publication.publicationTypeId == type.id}">SELECTED</c:if>><c:out
                                    value="${type.typeName}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Статус:</strong></th>
                <td>
                    <%--<c:forEach var="status" items="${publicationStatusList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="radio" name="status" value="${status.id}"--%>
                                   <%--<c:if test="${publication.publicationStatusId == status.id}">CHECKED</c:if>/><c:out--%>
                                <%--value="${status.statusName}"/>--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>

                    <select size="1" name="status">
                        <c:forEach var="status" items="${publicationStatusList}">
                            <option value="${status.id}" <c:if test="${publication.publicationStatusId == status.id}">SELECTED</c:if>><c:out
                                    value="${status.statusName}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Тематика:</strong></th>
                <td>
                    <%--<c:forEach var="theme" items="${publicationThemeList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="radio" name="theme" value="${theme.id}"--%>
                                   <%--<c:if test="${publication.publicationThemeId == theme.id}">CHECKED</c:if>/><c:out--%>
                                <%--value="${theme.themeName}"/>--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>

                    <select size="1" name="theme">
                        <c:forEach var="theme" items="${publicationThemeList}">
                            <option value="${theme.id}" <c:if test="${publication.publicationThemeId == theme.id}">SELECTED</c:if>><c:out
                                    value="${theme.themeName}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="left"><strong>Дата реєстрації:</strong></th>
                <td><input type="date" name="setDate" value="${publication.registrationDate}"></td>
            </tr>
            <tr>
                <th align="left"><strong>Вебсайт:</strong></th>
                <td><input type="text" name="website" value="${publication.website}"></td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong>Вартість передплати:</strong></th>
                <td>
                    <c:forEach var="cost" items="${publicationPeriodicityCostList}">
                        <p align="left">
                            <input type="text" name="cost" size="5" align="right" value="${cost.cost}"/> грн. на
                            <c:out
                                    value="${cost.timesPerYear}"/> міс/місяців
                        </p>
                    </c:forEach>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="submit" name="saveEditPub" value="Зберегти зміни">
    </form>

    <form name="cancelEditPublication" method="post" action="controller">
        <input type="hidden" name="command" value="cancelEditPublication">
        <input type="submit" name="cancel" value="Відміна редагування">
    </form>
</div>
</body>
</html>