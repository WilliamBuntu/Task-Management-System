package novaTech.servlet;

import novaTech.dao.TaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to handle task deletion
 */
@WebServlet(name = "TaskDeleteServlet", urlPatterns = "/task/delete")
public class TaskDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();
    }

    /**
     * Handles POST requests for deleting tasks
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String taskIdStr = request.getParameter("id");

            if (taskIdStr != null && !taskIdStr.isEmpty()) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    taskDAO.deleteTask(taskId);

                    // Set success message as request attribute
                    request.getSession().setAttribute("successMessage", "Task deleted successfully!");
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("errorMessage", "Invalid task ID.");
                }
            } else {
                request.getSession().setAttribute("errorMessage", "Task ID is required.");
            }

            // Redirect back to the task list
            response.sendRedirect(request.getContextPath() + "/tasks");

        } catch (Exception e) {
            throw new ServletException("Error deleting task", e);
        }
    }

    /**
     * Handles DELETE requests (maps to POST for browser compatibility)
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to doPost for compatibility
        doPost(request, response);
    }
}