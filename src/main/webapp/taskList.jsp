<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task List - NovaTech TMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>NovaTech Solutions</h1>
        <h2>Task Management System</h2>
        <nav>
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/tasks" class="active">Tasks</a>
            <a href="${pageContext.request.contextPath}/tasks/new">New Task</a>
        </nav>
    </header>

    <main>
        <section class="task-list-header">
            <h3>Task List</h3>

            <!-- Success/Error Messages -->
            <c:if test="${not empty sessionScope.successMessage}">
                <div class="alert success">
                    <p>${sessionScope.successMessage}</p>
                </div>
                <c:remove var="successMessage" scope="session" />
            </c:if>

            <c:if test="${not empty sessionScope.errorMessage}">
                <div class="alert error">
                    <p>${sessionScope.errorMessage}</p>
                </div>
                <c:remove var="errorMessage" scope="session" />
            </c:if>

            <!-- Filter Controls -->
            <div class="filter-controls">
                <div class="filter-box">
                    <span>Filter by status:</span>
                    <a href="${pageContext.request.contextPath}/tasks" class="${empty statusFilter ? 'active' : ''}">All</a>
                    <a href="${pageContext.request.contextPath}/tasks?status=Pending" class="${statusFilter eq 'Pending' ? 'active' : ''}">Pending</a>
                    <a href="${pageContext.request.contextPath}/tasks?status=Completed" class="${statusFilter eq 'Completed' ? 'active' : ''}">Completed</a>
                </div>

                <div class="action-box">
                    <a href="${pageContext.request.contextPath}/tasks/new" class="btn primary">New Task</a>
                </div>
            </div>
        </section>

        <!-- Task List Table -->
        <section class="task-list">
            <c:choose>
                <jsp:useBean id="tasks" scope="request" type="novaTech.model.Task"/>
                <c:when test="${empty tasks}">
                    <div class="no-tasks">
                        <p>No tasks found. Create a new task to get started!</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Due Date</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="task" items="${tasks}">
                            <tr class="${task.status eq 'Completed' ? 'completed' : ''}">
                                <td>${task.title}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty task.description}">
                                            <span class="no-desc">No description</span>
                                        </c:when>
                                        <c:otherwise>
                                            ${task.description}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><fmt:formatDate value="${task.dueDate}" pattern="yyyy-MM-dd" /></td>
                                <td><span class="status ${task.status}">${task.status}</span></td>
                                <td class="actions">
                                    <a href="${pageContext.request.contextPath}/tasks/edit?id=${task.id}" class="btn small">Edit</a>
                                    <form action="${pageContext.request.contextPath}/task/delete" method="post" class="inline-form">
                                        <input type="hidden" name="id" value="${task.id}">
                                        <button type="submit" class="btn small danger" onclick="return confirm('Are you sure you want to delete this task?')">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 NovaTech Solutions - Task Management System</p>
    </footer>
</div>

<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>