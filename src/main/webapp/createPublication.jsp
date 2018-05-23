<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 12:47
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
    <c:set var="currentPage" value="path.page.createPublication" scope="request"/>
    <title><fmt:message key="aboutPublication.title" bundle="${rb}"/></title>
</head>
<body>
<div>
    <p><fmt:message key="aboutPublication.createPublication" bundle="${rb}"/></p>
    <form name="createPublication" method="post" action="controller">
        <input type="hidden" name="command" value="okCreatePublication">
        <table>
            <tbody>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationName" bundle="${rb}"/></strong></th>
                <td><input type="text" name="pubName" placeholder="найменування"></td>
            </tr>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationISSN" bundle="${rb}"/></strong></th>
                <td><input type="text" name="ISSN" placeholder="ISSN"></td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationType" bundle="${rb}"/></strong></th>
                <td>
                    <%--<c:forEach var="type" items="${publicationTypeList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="radio" name="type" value="${type.id}"/><c:out value="${type.typeName}"/>--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>

                    <select size="1" name="type">
                        <option value="0"></option>
                        <c:forEach var="type" items="${publicationTypeList}">
                            <option value="${type.id}"><c:out
                                    value="${type.typeName}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <%--<div>--%>
                <%--<p><b>тип видання:</b></p>--%>
                <%--<select size="1" name="currentPubTypeId">--%>
                    <%--<option value="0" <c:if test="${currentPubTypeId == 0}">SELECTED</c:if>>показати всі</option>--%>
                    <%--<c:forEach var="type" items="${publicationTypeList}">--%>
                        <%--<option value="${type.id}" <c:if test="${currentPubTypeId == type.id}">SELECTED</c:if>><c:out--%>
                                <%--value="${type.typeName}"/></option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationStatus" bundle="${rb}"/></strong></th>
                <td>
                    <%--<c:forEach var="status" items="${publicationStatusList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="radio" name="status" value="${status.id}"/><c:out value="${status.statusName}"/>--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>

                    <select size="1" name="status">
                        <option value="0"></option>
                        <c:forEach var="status" items="${publicationStatusList}">
                            <option value="${status.id}"><c:out
                                    value="${status.statusName}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationTheme" bundle="${rb}"/></strong></th>
                <td>
                    <%--<c:forEach var="theme" items="${publicationThemeList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="radio" name="theme" value="${theme.id}"/><c:out value="${theme.themeName}"/>--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>

                    <select size="1" name="theme">
                        <option value="0"></option>
                        <c:forEach var="theme" items="${publicationThemeList}">
                            <option value="${theme.id}"><c:out
                                    value="${theme.themeName}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationRegDate" bundle="${rb}"/></strong></th>
                <td><input type="date" name="setDate" onkeydown="return false"></td>
            </tr>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></strong></th>
                <td><input type="text" name="website"></td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationCost" bundle="${rb}"/></strong></th>
                <td>
                    <p><input type="text" name="cost1Month" size="5" align="right"/> <fmt:message key="aboutPublication.cost1mohth" bundle="${rb}"/></p>
                    <p><input type="text" name="cost3Months" size="5" align="right"/> <fmt:message key="aboutPublication.cost3mohth" bundle="${rb}"/></p>
                    <p><input type="text" name="cost6Months" size="5" align="right"/> <fmt:message key="aboutPublication.cost6mohth" bundle="${rb}"/></p>
                    <p><input type="text" name="cost12Months" size="5" align="right"/> <fmt:message key="aboutPublication.cost12mohth" bundle="${rb}"/></p>
                    <%--<c:out--%>
                    <%--value="${cost.timesPerYear}"/> міс/місяців
    <%--<c:forEach var="cost" items="${publicationPeriodicityCostList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="text" name="cost" size="5" align="right" value="${cost.cost}"/> грн. на--%>
                            <%--<c:out--%>
                                    <%--value="${cost.timesPerYear}"/> міс/місяців--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="submit" name="saveNewPub" value="<fmt:message key="aboutPublication.addPublication" bundle="${rb}"/>">

        <input type="reset" name="clearForm" value="<fmt:message key="aboutPublication.clearForm" bundle="${rb}"/>">
    </form>

    <form name="cancelEditPublication" method="post" action="controller">
        <input type="hidden" name="command" value="cancelCreatePublication">
        <input type="submit" name="cancel" value="<fmt:message key="button.cancel" bundle="${rb}"/>">
    </form>
</div>
</body>
</html>
