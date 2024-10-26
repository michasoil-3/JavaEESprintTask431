<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="links.jsp" %>
</head>
<body>
<%--    <%@ include file="navbar.jsp" %>--%>
    <div>
        <form method="POST" action="${pageContext.request.contextPath}/login">
            <label for="emailInput">Email:</label><br>
            <input type="email" id="emailInput" name="email" required><br>
            <label for="passwordInput">Password:</label><br>
            <input type="password" id="passwordInput" name="password" required><br>
            <span>
                <button class="btn btn-primary">Log In</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/registration">Register</a>
            </span>
        </form>
    </div>
</body>
</html>
