<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) request.getAttribute("user");%>
<html>
<head>
    <title>Profile</title>
    <%@ include file="links.jsp" %>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
        <p>Id: <%=user.getId()%></p>
        <p>Email address: <%=user.getEmail()%></p>
        <p>Full name: <%=user.getFullName()%></p>
    </div>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Logout</a>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete?userId=<%=user.getId()%>" onclick="return confirm('Are you sure you want to delete this account? This action is irreversable.');">Delete account</a>
</body>
</html>
