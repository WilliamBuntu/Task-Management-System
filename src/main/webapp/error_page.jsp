<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
    <div class="container">
        <h1>Oops! Something Went Wrong</h1>

        <div class="alert error">
            <h3>Error Details:</h3>
            <p>
                <% if(exception != null) { %>
                    <%= exception.getMessage() %>
                <% } else { %>
                    An unknown error occurred.
                <% } %>
            </p>
        </div>

        <div class="buttons">
            <a href="${pageContext.request.contextPath}/tasks" class="btn primary">Back to Task List</a>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn secondary">Go to Home</a>
        </div>
    </div>
</body>
</html>