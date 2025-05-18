package novaTech.servlet;

import novaTech.model.Task;
import novaTech.dao.TaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to handle task listing, filtering, and task form preparation
 */
@WebServlet(name = "TaskListServlet", urlPatterns = {"/tasks", "/tasks/new", "/tasks/edit"})
public class TaskListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();
    }

    /**
     * Handles GET requests for task lists and task forms
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo();

        try {
            if ("/tasks".equals(path) && pathInfo == null) {
                // List all tasks or filter by status
                listTasks(request, response);
            } else if ("/tasks/new".equals(path)) {
                // Show form for new task
                showNewForm(request, response);
            } else if ("/tasks/edit".equals(path)) {
                // Show form for editing task
                showEditForm(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Lists tasks, optionally filtered by status
     */
    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String statusFilter = request.getParameter("status");
            List<Task> tasks;

            if (statusFilter != null && !statusFilter.isEmpty()) {
                tasks = taskDAO.getTasksByStatus(statusFilter);
                request.setAttribute("statusFilter", statusFilter);
            } else {
                tasks = taskDAO.getAllTasks();
            }

            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("/taskList.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving tasks", e);
        }
    }

    /**
     * Shows the form for creating a new task
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/taskForm.jsp").forward(request, response);
    }

    /**
     * Shows the form for editing an existing task
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int taskId = Integer.parseInt(request.getParameter("id"));
            Task task = taskDAO.getTaskById(taskId);

            if (task != null) {
                request.setAttribute("task", task);
                request.getRequestDispatcher("/taskForm.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid task ID");
        } catch (Exception e) {
            throw new ServletException("Error retrieving task for editing", e);
        }
    }
}