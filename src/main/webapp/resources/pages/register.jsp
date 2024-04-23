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
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
</head>
<body>
<div class="container">
    <div class="register">
        <form class="register-form" action="controller" method="post">
            <input type="hidden" name="command" value="register"/>
            <div class="form-group">
                <label for="userName"></label><input type="text" class="form-control" name="userName" id="userName"
                                                     placeholder="Username"/>
            </div>
            <div class="form-group">
                <label for="userEmail"></label><input type="text" class="form-control" name="userEmail" id="userEmail"
                                                      placeholder="Email"/>
            </div>
            <div class="form-group">
                <label>
                    <input type="password" class="form-control" name="userPass" placeholder="Password"/>
                </label>
            </div>
            <div class="form-group">
                <label>
                    <input class="form-control" type="password" name="repeatPass" placeholder="Repeat password"/>
                </label>
            </div>
            <button type="submit" name="register">SignUp</button>
        </form>
    </div>
</div>
</body>
</html>
