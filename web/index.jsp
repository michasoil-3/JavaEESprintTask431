<%@ page import="models.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.User" %>
<%@ page import="functools.Formatting" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products"); %>
<% User user = (User) request.getAttribute("user");%>
<html>
<head>
    <title>Home</title>
    <%@ include file="links.jsp" %>
    <!--
    perplexity.ai used to help with the front-end product cards flex organisation
    -->
    <style>
        .product-container {
            display: flex;          /* Enable Flexbox */
            flex-wrap: wrap;       /* Allow wrapping of items */
            justify-content: center; /* Center items in the row */
        }
        .product-card {
            display: flex;
            flex-wrap: wrap;
            flex-direction: column;
            justify-content: space-between; /* Ensures space between content and button */
            height: 300px; /* Makes sure the card takes full height */
            width: calc(20% - 20px);
            margin: 10px;   /* Margin around each card */
            border: 1px solid #ccc; /* Optional border for better visibility */
            padding: 10px;  /* Padding inside the card */
            box-sizing: border-box; /* Include padding and border in width calculation */
        }
        .product-content {
            flex-grow: 1; /* Allows content to grow and push the button down */
        }
    </style>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="position-relative text-center">
        <h1>Welcome to BATLIB Shop!</h1>
        <p>The home of only the best electronic devices on the market.</p>
    </div>
    <div class="product-container">
        <% for (Product product: products) {%>
        <div class="border border-1 p-2 product-card">
            <div class="product-content">
                <h3><%=product.getName()%></h3>
                <p><%=product.getDescription()%></p>
                <h4 class="text-success"><%=Formatting.renderPrice(product.getPrice())%>$</h4>
            </div>
            <a class="btn btn-success mt-auto" href="#">Buy</a>
        </div>
        <% } %>
    </div>
</body>
</html>
