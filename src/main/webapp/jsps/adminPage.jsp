<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 06.05.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
    <div>publications list</div>
    <p>Publications list</p>
    <div id="publicationsList">
        <form name="publicationType" method="get" action="controller">
            <input type="hidden" name="command" value="selectPublicationsAdminWindow">
            <input type="radio" name="publicationType">
            <input type="radio" name="periodicalTheme">
        </form>

        <form name="addpublication" method="post" action="controller">
            <input type="hidden" name="command" value="createPublication">
            <input type="submit" name="createPublication" value="Create">
        </form>

        <form name="editpublication" method="post" action="controller">
            <input type="hidden" name="command" value="editPublication">
            <input type="submit" name="editPublication" value="Edit">
        </form>

        <form name="showPublicationIngo" method="get" action="controller">
            <input type="hidden" name="command" value="showAboutPublication">
            <input type="submit" name="showInfo" value="Info">
        </form>
        <p>publications ammount </p><%=request.getParameter("") %>
    </div>

    <div>bills list</div>
    <p>Bills list</p>
    <div id="billsList">
        <form name="billType" method="get" action="controller">
            <input type="hidden" name="command" value="selectBillsByStatus">
            <input type="radio" name="billStatus">
        </form>

        <form name="showBillIngo" method="get" action="controller">
            <input type="hidden" name="command" value="showAboutBill">
            <input type="submit" name="showInfo" value="Info">
        </form>
        <p>bills anount </p><%=request.getParameter("") %>
    </div>
</body>
</html>
