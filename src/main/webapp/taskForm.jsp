<jsp:useBean id="task" scope="request" type="novaTech.model.Task"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${empty task ? 'Create Task' : 'Edit Task'} - NovaTech TMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>NovaTech Solutions</h1>
        <h2>Task Management System</h2>
        <nav>
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/tasks">Tasks</a>
            <a href="${pageContext.request.contextPath}/tasks/new" class="${empty task ? 'active' : ''}">New Task</a>
        </nav>
    </header>

    <main>
        <section class="task-form">
            <h3>${empty task ? 'Create New Task' : 'Edit Task'}</h3>

            <!-- Error Message Display -->
            <jsp:useBean id="errorMessage" scope="request" type="java.lang.String"/>
            <c:if test="${not empty errorMessage}">
                <div class="alert error">
                    <p>${errorMessage}</p>
                </div>
            </c:if>

            <!-- Task Form -->
            <form action="${pageContext.request.contextPath}/task" method="post">
                <!-- Hidden ID field for edit mode -->
                <c:if test="${not empty task}">
                    <input type="hidden" name="id" value="${task.id}">
                </c:if>

                <div class="form-group">
                    <label for="title">Title<span class="required">*</span></label>
                    <input type="text" id="title" name="title" value="${task.title}" required>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" rows="4">${task.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="dueDate">Due Date<span class="required">*</span></label>
                    <input type="date" id="dueDate" name="dueDate"
                           value="<fmt:formatDate value="${task.dueDate}" pattern="yyyy-MM-dd" />"
                           required>
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option value="Pending" ${task.status eq 'Pending' or empty task ? 'selected' : ''}>Pending</option>
                        <option value="Completed" ${task.status eq 'Completed' ? 'selected' : ''}>Completed</option>
                    </select>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn primary">${empty task ? 'Create Task' : 'Update Task'}</button>
                    <a href="${pageContext.request.contextPath}/tasks" class="btn secondary">Cancel</a>
                </div>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 NovaTech Solutions—Task Management System</p>
    </footer>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>