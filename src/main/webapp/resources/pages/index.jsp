<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>

<body>
<div class="container">
    <div class="register">
        <h1>Login</h1>
        <h2>to get started</h2>
        <form class="register-form" action="controller" method="post">

            <input type="hidden" name="command" value="login" />

            <input type="text" class="form-control" name="login" id="userEmail" placeholder="Email" />
            <input type="password" class="form-control" name="pass" placeholder="Password" />

            <p id="forgot">Forgot Password?</p>
            <input type="submit" class="form-control" name="sub" value="Continue" />
        </form>
        ${login_msg.toUpperCase()}
        <div class="already">
            <p>New User? <b>Register</b></p>
        </div>
    </div>

</div>
</body>

<%--<body>--%>
<%--<form action="controller">--%>
<%--    <input type="hidden" name="command" value="login"/>--%>
<%--    Login: <input type="text" name="login" value=""/>--%>
<%--    <br/>--%>
<%--    Password: <input type="password" name="pass" value=""/>--%>
<%--    <br/>--%>
<%--    <input type="submit" name="sub" value="Push"/>--%>
<%--    <br/>--%>
<%--    ${login_msg.toUpperCase()}--%>
<%--    <br/>--%>
<%--    ${pageContext.session.id}--%>
<%--    <br/>--%>
<%--    ${filter_attr}--%>
<%--</form>--%>

<%--</body>--%>
<%--</html>--%>