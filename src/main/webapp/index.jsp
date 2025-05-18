<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Task Management System</title>
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
    <div class="container">
        <header>
            <h1>NovaTech Solutions</h1>
            <h2>Task Management System</h2>
        </header>

        <main>
            <div class="welcome">
                <h3>Welcome to the Task Management System</h3>
                <p>
                    This system helps NovaTech employees organize and track their tasks efficiently.
                    Manage your tasks, set deadlines, and stay on top of your work!
                </p>

                <div class="actions">
                    <a href="${pageContext.request.contextPath}/tasks" class="btn primary">View All Tasks</a>
                    <a href="${pageContext.request.contextPath}/tasks/new" class="btn secondary">Create New Task</a>
                </div>
            </div>
        </main>

        <footer>
            <p>&copy; 2025 NovaTech Solutions - Task Management System</p>
        </footer>
    </div>
    </body>
    </html>