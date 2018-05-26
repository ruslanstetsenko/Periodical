<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 10.05.2018
  Time: 16:07
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
    <c:set var="currentPage" value="path.page.editPublication" scope="request"/>
    <title><fmt:message key="aboutPublication.editPubTitle" bundle="${rb}"/></title>
</head>
<body>
<div>
    <p><fmt:message key="aboutPublication.editPublication" bundle="${rb}"/></p>
    <form name="editPublication" method="post" action="controller">
        <input type="hidden" name="command" value="okEditPublication">
        <table>
            <tbody>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationName" bundle="${rb}"/></strong></th>
                <td><input type="text" name="pubName" value="${pubName}"></td>
                <td>
                    <c:if test="${incorectName}">
                        <fmt:message key="validation.publication.name" bundle="${validation}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationISSN" bundle="${rb}"/></strong></th>
                <td><input type="text" name="ISSN" value="${ISSN}"></td>
                <td>
                    <c:if test="${incorectISSN}">
                        <fmt:message key="validation.publication.ISSN" bundle="${validation}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationType" bundle="${rb}"/></strong></th>
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
                <td>
                    <c:if test="${incorectPubType}">
                        <fmt:message key="validation.publication.pubType" bundle="${validation}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationStatus" bundle="${rb}"/></strong></th>
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
                <td>
                    <c:if test="${incorectPubStatus}">
                        <fmt:message key="validation.publication.pubStatus" bundle="${validation}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationTheme" bundle="${rb}"/></strong></th>
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
                <td>
                    <c:if test="${incorectPubTheme}">
                        <fmt:message key="validation.publication.pubTheme" bundle="${validation}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationRegDate" bundle="${rb}"/></strong></th>
                <td><input type="date" onkeydown="return false" name="setDate" value="${setDate}"></td>
                <td>
                    <c:if test="${incorectSetDate}">
                        <fmt:message key="validation.publication.setDate" bundle="${validation}"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th align="left"><strong><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></strong></th>
                <td><input type="text" name="website" value="${website}"></td>
                <td>
                    <c:if test="${incorectWebsite}">
                        <fmt:message key="validation.publication.website" bundle="${validation}"/>
                    </c:if>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationCost" bundle="${rb}"/></strong></th>--%>
                <%--<td>--%>
                    <%--<c:forEach var="cost" items="${publicationPeriodicityCostList}">--%>
                        <%--<p align="left">--%>
                            <%--<input type="text" name="cost" size="5" align="right" value="${cost.cost}"/> <fmt:message key="aboutPublication.subsCostTail" bundle="${rb}"/>--%>
                            <%--<c:out value="${cost.timesPerYear}"/> <fmt:message key="aboutPublication.subsCostHead" bundle="${rb}"/>--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<c:if test="${incorectCost}">--%>
                        <%--<fmt:message key="validation.publication.cost" bundle="${validation}"/>--%>
                    <%--</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationCost"
                                                                   bundle="${rb}"/></strong></th>
                <td>
                    <p><input type="text" name="cost1Month" size="5" align="right" value="${cost1Month}"/>
                        <fmt:message key="aboutPublication.cost1mohth" bundle="${rb}"/>
                        <c:if test="${incorectCost1M}">
                            <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                        </c:if>
                    </p>
                    <p><input type="text" name="cost3Months" size="5" align="right" value="${cost3Months}"/>
                        <fmt:message key="aboutPublication.cost3mohth" bundle="${rb}"/>
                        <c:if test="${incorectCost3M}">
                            <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                        </c:if>
                    </p>
                    <p><input type="text" name="cost6Months" size="5" align="right" value="${cost6Months}"/>
                        <fmt:message key="aboutPublication.cost6mohth" bundle="${rb}"/>
                        <c:if test="${incorectCost6M}">
                            <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                        </c:if>
                    </p>
                    <p><input type="text" name="cost12Months" size="5" align="right" value="${cost12Months}"/>
                        <fmt:message key="aboutPublication.cost12mohth" bundle="${rb}"/>
                        <c:if test="${incorectCost12M}">
                            <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                        </c:if>
                    </p>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="submit" name="saveEditPub" value="<fmt:message key="button.save" bundle="${rb}"/>">
    </form>

    <form name="cancelEditPublication" method="post" action="controller">
        <input type="hidden" name="command" value="cancelEditPublication">
        <input type="submit" name="cancel" value="<fmt:message key="button.cancel" bundle="${rb}"/>">
    </form>
</div>
</body>
</html>