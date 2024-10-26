<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String error = (String) request.getAttribute("error"); %>
<html>
<head>
    <title>Registration</title>
    <%@ include file="links.jsp" %>
</head>
<body>
    <div>
        <form method="POST" action="${pageContext.request.contextPath}/registration">
            <label for="emailInput">Enter email:</label><br>
            <input type="email" id="emailInput" name="email" required><br>
            <label for="nameInput">Enter full name:</label><br>
            <input type="text" id="nameInput" name="name" required><br>
            <label for="passwordInput">Enter password:</label><br>
            <input type="password" id="passwordInput" name="password" required><br>
            <label for="confirmPasswordInput">Confirm password:</label><br>
            <input type="password" id="confirmPasswordInput" name="confirmPassword" required><br>
            <span>
                <button class="btn btn-primary">Register</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Back</a>
            </span>
        </form>
        <% if (error != null) { %>
        <p class="text-danger">A user with that email already exists. Please use another email.</p>
        <% } %>
    </div>
    <script>
        const form = document.querySelector("form")
        form.addEventListener("submit", function(event) {
            const password = document.getElementById("passwordInput").value
            const confirmPassword = document.getElementById("confirmPasswordInput").value

            if (password !== confirmPassword) {
                alert("Passwords do not match.")
                event.preventDefault()
                return
            }

            if (password.length < 6) {
                alert("Please create a longer password (6 char. minimum).")
                event.preventDefault()
            }
        });
    </script>
</body>
</html>
