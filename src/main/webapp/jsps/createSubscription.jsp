<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 07.05.2018
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create subscription</title>
</head>
<body>
<div>publications list</div>
<p>Publications list</p>
<div id="publicationsList">
    <form name="publicationType" method="get" action="controller">
        <input type="hidden" name="command" value="selectPublicationsAdminWindow">
        <input type="radio" name="publicationType">
        <input type="radio" name="periodicalTheme">
        <input type="radio" name="periodicyCost">
    </form>

    <form name="addPublicationToSubs" method="post" action="controller">
        <input type="hidden" name="command" value="addPublicationToSubscription">
        <input type="submit" name="addPublication" value="Add">
    </form>

    <form name="createSubscription" method="post" action="controller">
        <input type="hidden" name="command" value="createSubscription">
        <input type="submit" name="createSubscription" value="Create">
    </form>

    <form name="cancelSubscription" method="get" action="controller">
        <input type="hidden" name="command" value="cancelEditSubscription">
        <input type="submit" name="showInfo" value="Info">
    </form>
    <p>publications ammount </p><%=request.getParameter("") %>
</div>

<div>
    <p>About publication</p>
    <form name="aboutPublication" method="get" action="controller">
        <input type="hidden" name="command" value="createPublication">
        <p>Name</p><%=request.getParameter("") %>
        <input type="radio" name="periodicalType">
        <input type="radio" name="periodicalTheme">
        <p>ISSN</p><%=request.getParameter("") %>
        <p>Periodical register date</p><%=request.getParameter("") %>
        <p>Website</p><%=request.getParameter("") %>
        <p>Periodicy cost</p>
        <p>per 1 months </p><%=request.getParameter("") %>
        <p>per 3 months </p><%=request.getParameter("") %>
        <p>per 6 months </p><%=request.getParameter("") %>
        <p>per 12 months </p><%=request.getParameter("") %>
    </form>
</div>
</body>
</html>
