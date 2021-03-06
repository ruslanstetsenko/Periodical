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
<fmt:setBundle basename="message" var="validation"/>

<html>
<head>
    <c:set var="currentPage" value="path.page.createPublication" scope="request"/>
    <title><fmt:message key="aboutPublication.title" bundle="${rb}"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet"/>
    <style>
        <%@include file='css/edit_publication.css' %>
    </style>
</head>
<body>
<article>
    <div class="edit_publication">
        <h3 class="about_publication_title"><fmt:message key="aboutPublication.createPublication" bundle="${rb}"/></h3>
        <form name="createPublication" method="post" action="publications">
            <table>
                <tbody>
                <tr>
                    <th align="left"><strong><fmt:message key="aboutPublication.publicationName"
                                                          bundle="${rb}"/></strong>
                    </th>
                    <td>
                        <input class="input_text" type="text" name="pubName" placeholder="найменування"
                               value="${pubName}">
                        <span class="error">
                            <c:if test="${incorectName}">
                                <fmt:message key="validation.publication.name" bundle="${validation}"/>
                            </c:if>
                        </span>
                    </td>
                </tr>
                <tr>
                    <th align="left"><strong><fmt:message key="aboutPublication.publicationISSN"
                                                          bundle="${rb}"/></strong>
                    </th>
                    <td>
                        <input class="input_text" type="text" name="ISSN" placeholder="ISSN" value="${ISSN}">
                        <span class="error">
                        <c:if test="${incorectISSN}">
                            <fmt:message key="validation.publication.ISSN" bundle="${validation}"/>
                        </c:if>
                    </span>
                    </td>
                </tr>
                <tr>
                    <th align="left" valign="top">
                        <fmt:message key="aboutPublication.publicationType" bundle="${rb}"/>

                    </th>
                    <td>
                        <select class="input_select" size="1" name="type">
                            <option value="0"></option>
                            <c:forEach var="type" items="${publicationTypeList}">
                                <option value="${type.id}"><c:out
                                        value="${type.typeName}"/></option>
                            </c:forEach>
                        </select>
                        <span class="error">
                        <c:if test="${incorectPubType}">
                            <fmt:message key="validation.publication.pubType" bundle="${validation}"/>
                        </c:if>
                    </span>
                    </td>

                </tr>
                <tr>
                    <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationStatus"
                                                                       bundle="${rb}"/></strong></th>
                    <td>
                        <select class="input_select" size="1" name="status">
                            <option value="0"></option>
                            <c:forEach var="status" items="${publicationStatusList}">
                                <option value="${status.id}"><c:out
                                        value="${status.statusName}"/></option>
                            </c:forEach>
                        </select>
                        <span class="error">
                        <c:if test="${incorectPubStatus}">
                            <fmt:message key="validation.publication.pubStatus" bundle="${validation}"/>
                        </c:if>
                    </span>
                    </td>
                </tr>
                <tr>
                    <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationTheme"
                                                                       bundle="${rb}"/></strong></th>
                    <td>
                        <select class="input_select" size="1" name="theme">
                            <option value="0"></option>
                            <c:forEach var="theme" items="${publicationThemeList}">
                                <option value="${theme.id}"><c:out
                                        value="${theme.themeName}"/></option>
                            </c:forEach>
                        </select>
                        <span class="error">
                        <c:if test="${incorectPubTheme}">
                            <fmt:message key="validation.publication.pubTheme" bundle="${validation}"/>
                        </c:if>
                    </span>
                    </td>

                </tr>
                <tr>
                    <th align="left"><strong><fmt:message key="aboutPublication.publicationRegDate"
                                                          bundle="${rb}"/></strong></th>
                    <td>
                        <input class="input_date" type="date" name="setDate" onkeydown="return false"
                               value="${setDate}">
                        <span class="error">
                        <c:if test="${incorectSetDate}">
                            <fmt:message key="validation.publication.setDate" bundle="${validation}"/>
                        </c:if>
                        </span>
                    </td>
                </tr>
                <tr>
                    <th align="left"><strong><fmt:message key="aboutPublication.publicationWebsite"
                                                          bundle="${rb}"/></strong></th>
                    <td>
                        <input class="input_text" type="text" name="website" value="${website}">
                        <span class="error">
                        <c:if test="${incorectWebsite}">
                            <fmt:message key="validation.publication.website" bundle="${validation}"/>
                        </c:if>
                        </span>
                    </td>

                </tr>
                <tr>
                    <th align="left" valign="top"><strong><fmt:message key="aboutPublication.publicationCost"
                                                                       bundle="${rb}"/></strong></th>
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

            <%--<input type="submit" name="saveNewPub"--%>
                   <%--value="<fmt:message key="aboutPublication.addPublication" bundle="${rb}"/>">--%>
            <div class="buttons">
                <input class="button" type="reset" name="clearForm" value="<fmt:message key="aboutPublication.clearForm" bundle="${rb}"/>">
                <button class="button" type="submit" name="command" value="okCreatePublication">
                    <fmt:message key="aboutPublication.addPublication" bundle="${rb}"/>
                </button>
                <button class="button" type="submit" name="command" value="cancelCreatePublication">
                    <fmt:message key="button.cancel" bundle="${rb}"/>
                </button>
            </div>


        </form>

        <%--<form name="cancelEditPublication" method="post" action="controller">--%>
            <%--<input type="hidden" name="command" value="cancelCreatePublication">--%>
            <%--<input type="submit" name="cancel" value="<fmt:message key="button.cancel" bundle="${rb}"/>">--%>
        <%--</form>--%>
    </div>
</article>

</body>
</html>
