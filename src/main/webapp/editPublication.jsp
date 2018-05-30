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
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/edit_publication.css' %>
    </style>
</head>
<body>
<article>
    <div class="edit_publication">
        <h3 class="about_publication_title"><fmt:message key="aboutPublication.editPublication" bundle="${rb}"/></h3>
        <form name="editPublication" method="post" action="controler">
            <%--<input type="hidden" name="command" value="okEditPublication">--%>
            <table>
                <tbody>
                <tr>
                    <th align="left"><fmt:message key="aboutPublication.publicationName" bundle="${rb}"/></th>
                    <td><input class="input_text" type="text" name="pubName" value="${pubName}"></td>
                    <td class="error">
                        <c:if test="${incorectName}">
                            <fmt:message key="validation.publication.name" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutPublication.publicationISSN" bundle="${rb}"/></th>
                    <td><input class="input_text" type="text" name="ISSN" value="${ISSN}"></td>
                    <td class="error">
                        <c:if test="${incorectISSN}">
                            <fmt:message key="validation.publication.ISSN" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left" valign="top"><fmt:message key="aboutPublication.publicationType"
                                                               bundle="${rb}"/></th>
                    <td>
                        <select class="input_select" size="1" name="type">
                            <c:forEach var="type" items="${publicationTypeList}">
                                <option value="${type.id}"
                                        <c:if test="${publication.publicationTypeId == type.id}">SELECTED</c:if>><c:out
                                        value="${type.typeName}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="error">
                        <c:if test="${incorectPubType}">
                            <fmt:message key="validation.publication.pubType" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left" valign="top"><fmt:message key="aboutPublication.publicationStatus"
                                                               bundle="${rb}"/></th>
                    <td>
                        <select class="input_select" size="1" name="status">
                            <c:forEach var="status" items="${publicationStatusList}">
                                <option value="${status.id}"
                                        <c:if test="${publication.publicationStatusId == status.id}">SELECTED</c:if>>
                                    <c:out
                                            value="${status.statusName}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="error">
                        <c:if test="${incorectPubStatus}">
                            <fmt:message key="validation.publication.pubStatus" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left" valign="top"><fmt:message key="aboutPublication.publicationTheme"
                                                               bundle="${rb}"/></th>
                    <td>
                        <select class="input_select" size="1" name="theme">
                            <c:forEach var="theme" items="${publicationThemeList}">
                                <option value="${theme.id}"
                                        <c:if test="${publication.publicationThemeId == theme.id}">SELECTED</c:if>>
                                    <c:out
                                            value="${theme.themeName}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="error">
                        <c:if test="${incorectPubTheme}">
                            <fmt:message key="validation.publication.pubTheme" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutPublication.publicationRegDate" bundle="${rb}"/></th>
                    <td><input class="input_date" type="date" onkeydown="return false" name="setDate"
                               value="${setDate}"></td>
                    <td class="error">
                        <c:if test="${incorectSetDate}">
                            <fmt:message key="validation.publication.setDate" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left"><fmt:message key="aboutPublication.publicationWebsite" bundle="${rb}"/></th>
                    <td><input class="input_text" type="text" name="website" value="${website}"></td>
                    <td class="error">
                        <c:if test="${incorectWebsite}">
                            <fmt:message key="validation.publication.website" bundle="${validation}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th align="left" valign="top">
                        <fmt:message key="aboutPublication.publicationCost" bundle="${rb}"/>
                    </th>
                    <td>
                        <p><input class="input_cost" type="text" name="cost1Month" size="5" align="right"
                                  value="${cost1Month}"/>
                            <fmt:message key="aboutPublication.cost1mohth" bundle="${rb}"/>
                            <span class="error">
                                <c:if test="${incorectCost1M}">
                                    <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                                </c:if>
                            </span>
                        </p>
                        <p><input class="input_cost" type="text" name="cost3Months" size="5" align="right"
                                  value="${cost3Months}"/>
                            <fmt:message key="aboutPublication.cost3mohth" bundle="${rb}"/>
                            <span class="error">
                              <c:if test="${incorectCost3M}">
                                  <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                              </c:if>
                            </span>

                        </p>
                        <p><input class="input_cost" type="text" name="cost6Months" size="5" align="right"
                                  value="${cost6Months}"/>
                            <fmt:message key="aboutPublication.cost6mohth" bundle="${rb}"/>
                            <span class="error">
                              <c:if test="${incorectCost6M}">
                                  <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                              </c:if>
                            </span>
                        </p>
                        <p><input class="input_cost" type="text" name="cost12Months" size="5" align="right"
                                  value="${cost12Months}"/>
                            <fmt:message key="aboutPublication.cost12mohth" bundle="${rb}"/>
                            <span class="error">
                                 <c:if test="${incorectCost12M}">
                                     <fmt:message key="validation.publication.cost" bundle="${validation}"/>
                                 </c:if>
                            </span>
                        </p>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="buttons">
                <%--<input class="button" type="submit" name="saveEditPub" value="<fmt:message key="button.save" bundle="${rb}"/>">--%>

                <button class="button" type="submit" name="command" value="okEditPublication">
                    <fmt:message key="button.save" bundle="${rb}"/>
                </button>

                <button class="button" type="submit" name="command" value="cancelEditPublication">
                    <fmt:message key="button.cancel" bundle="${rb}"/>
                </button>
            </div>

        </form>

        <%--<form name="cancelEditPublication" method="post" action="controller">--%>
        <%--<input type="hidden" name="command" value="cancelEditPublication">--%>
        <%--<input class="button" type="submit" name="cancel" value="<fmt:message key="button.cancel" bundle="${rb}"/>">--%>
        <%--</form>--%>
    </div>
</article>

</body>
</html>