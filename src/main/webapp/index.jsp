<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Index</title>
</head>
<body>
<h2>Hello World!</h2>
<form name="indexPage" method="post" action="controller">
    <input type="hidden" name="command" value="login">
    <input type="submit"  value="go">
</form>

</body>
</html>
