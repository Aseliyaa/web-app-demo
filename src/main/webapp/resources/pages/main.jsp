<%--
  Created by IntelliJ IDEA.
  User: Asya
  Date: 13.04.2024
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hello (forward) = ${user}
<hr/>
H1 (redirect/forward) = ${user_name}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logOut">
</form>
</body>
</html>
