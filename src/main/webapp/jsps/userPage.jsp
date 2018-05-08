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
    <title>User Window</title>
</head>
<body>
    <div>subscriptions list</div>
    <p>Publications list</p>
    <div id="subscriptionsList">
        <form name="publicationType" method="get" action="controller">
            <input type="hidden" name="command" value="selectSubscriptionsByStatus">
            <input type="radio" name="subscriptionType">
        </form>

        <form name="showSubscriptionIngo" method="get" action="controller">
            <input type="hidden" name="command" value="showAboutSubscription">
            <input type="submit" name="showInfo" value="Info">
        </form>

        <form name="createSubscription" method="post" action="controller">
            <input type="hidden" name="command" value="createSubscription">
            <input type="submit" name="create" value="Create">
        </form>
        <p>subscriptions ammount </p><%=request.getParameter("") %>
    </div>

    <div>bills list</div>
    <p>Bills list</p>
    <div id="billsList">
        <form name="billType" method="get" action="controller">
            <input type="hidden" name="command" value="selectBillsByStatusByUser">
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
