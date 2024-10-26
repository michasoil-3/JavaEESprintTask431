<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">BATLIB SHOP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Top Sales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">New Sales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">By Category</a>
                </li>
            </ul>
        </div>
        <div class="nav-item">
            <% if (user == null) { %>
            <a class="nav-link" href="${pageContext.request.contextPath}/login">Sign In</a>
            <% } else { %>
            <a class="nav-link" href="${pageContext.request.contextPath}/profile?userId=<%=user.getId()%>">Profile</a>
            <% } %>
        </div>
    </div>
</nav>
