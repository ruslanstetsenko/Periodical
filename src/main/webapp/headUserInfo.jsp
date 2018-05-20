<%--
  Created by IntelliJ IDEA.
  User: rstet
  Date: 20.05.2018
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <%--<title>Title</title>--%>
</head>
<body>
<a href="controller?command=logout">Logout</a>

<h3>Вітаємо, <c:out value="${currentUser.surname}"/> <c:out value="${currentUser.name}"/> <c:out value="${currentUser.lastName}"/></h3>
<%--<form name="logout" method="get" action="login">--%>
<%--<input type="hidden" name="command" value="logout">--%>
<%--<input type="submit" name="cancel" value="Logout">--%>
<%--</form>--%>
</body>
</html>
