package novaTech.servlet;

import novaTech.model.Task;
import novaTech.dao.TaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet to handle task creation and updating
 */
@WebServlet(name = "TaskServlet", urlPatterns = "/tasks")
public class TaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO;
    private SimpleDateFormat dateFormat;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * Handles POST requests for creating new tasks
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get form parameters
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String dueDateStr = request.getParameter("dueDate");
            String status = request.getParameter("status");

            // Validate required fields
            if (title == null || title.trim().isEmpty() || dueDateStr == null || dueDateStr.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Title and Due Date are required fields.");
                request.getRequestDispatcher("/taskForm.jsp").forward(request, response);
                return;
            }

            // Default status to "Pending" if not provided
            if (status == null || status.trim().isEmpty()) {
                status = "Pending";
            }

            // Parse due date
            Date dueDate;
            try {
                dueDate = dateFormat.parse(dueDateStr);
            } catch (ParseException e) {
                request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD.");
                request.getRequestDispatcher("/taskForm.jsp").forward(request, response);
                return;
            }

            // Create a new task
            Task task = new Task(title, description, dueDate, status);

            // Check if we're updating an existing task
            String taskIdStr = request.getParameter("id");
            if (taskIdStr != null && !taskIdStr.isEmpty()) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    task.setId(taskId);
                    taskDAO.updateTask(task);
                    request.setAttribute("successMessage", "Task updated successfully!");
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid task ID.");
                    request.getRequestDispatcher("/taskForm.jsp").forward(request, response);
                    return;
                }
            } else {
                // Insert a new task
                taskDAO.insertTask(task);
                request.setAttribute("successMessage", "Task created successfully!");
            }

            // Redirect to task list
            response.sendRedirect(request.getContextPath() + "/tasks");

        } catch (Exception e) {
            throw new ServletException("Error processing task submission", e);
        }
    }

    /**
     * Handles PUT requests for updating existing tasks
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to doPost, which handles both create and update
        doPost(request, response);
    }
}