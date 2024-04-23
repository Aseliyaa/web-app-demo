<%--
  Created by IntelliJ IDEA.
  User: Asya
  Date: 20.04.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
</head>
<style>
    <%@include file="../css/style.css" %>
</style>
<body>
<div class="container">
    <div class="register">
        <h1>Signup</h1>
        <h2>to get started</h2>
        <form class="register-form" action="controller">

            <input type="hidden" name="command" value="register"/>

            <input type="text" class="form-control" name="userName" id="userName" placeholder="Username"/>
            <input type="text" class="form-control" name="userEmail" id="userEmail" placeholder="Email"/>
            <input type="password" class="form-control" name="userPass" placeholder="Password"/>
            <input class="form-control" type="password" name="repeatPass" placeholder="Repeat password"/>
            <span>
                    <label>
                        <input type="checkbox" name="checkbox"/> Agree to Our terms and Conditions
                    </label>
                </span>
            <input type="submit" class="form-control" name="register" value="Continue"/>
        </form>

        <div class="already">
            <p>Already registered? <b><a href="${pageContext.request.contextPath}/resources/pages/index.jsp">Login</a></b></p>
        </div>
        <div class="err-message">
            <p>${register_msg}</p>
        </div>
    </div>

</div>
</body>

</html>
