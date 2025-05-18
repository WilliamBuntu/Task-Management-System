package novaTech.dao;

import novaTech.model.Task;
import novaTech.util.DatabaseUtil;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Task-related database operations
 */
public class TaskDAO {



    /**
     * Inserts a new task into the database
     *
     * @param task The task to insert
     * @return The ID of the newly inserted task
     * @throws SQLException If a database access error occurs
     */
    public int insertTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, due_date, status) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        int id = -1;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new Date(task.getDueDate().getTime()));
            stmt.setString(4, task.getStatus());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating task failed, no rows affected.");
            }

            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
                task.setId(id);
            } else {
                throw new SQLException("Creating task failed, no ID obtained.");
            }

            return id;
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (stmt != null) stmt.close();
            DatabaseUtil.closeConnection(conn);
        }
    }

    /**
     * Updates an existing task in the database
     *
     * @param task The task to update
     * @return The number of rows affected
     * @throws SQLException If a database access error occurs
     */
    public int updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, new Date(task.getDueDate().getTime()));
            stmt.setString(4, task.getStatus());
            stmt.setInt(5, task.getId());

            return stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            DatabaseUtil.closeConnection(conn);
        }
    }

    /**
     * Deletes a task from the database
     *
     * @param taskId The ID of the task to delete
     * @return The number of rows affected
     * @throws SQLException If a database access error occurs
     */
    public int deleteTask(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taskId);

            return stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            DatabaseUtil.closeConnection(conn);
        }
    }

    /**
     * Retrieves a task by its ID
     *
     * @param taskId The ID of the task to retrieve
     * @return The Task object, or null if not found
     * @throws SQLException If a database access error occurs
     */
    public Task getTaskById(int taskId) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Task task = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taskId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getDate("due_date"));
                task.setStatus(rs.getString("status"));
            }

            return task;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DatabaseUtil.closeConnection(conn);
        }
    }

    /**
     * Retrieves all tasks from the database
     *
     * @return A list of all tasks
     * @throws SQLException If a database access error occurs
     */
    public List<Task> getAllTasks() throws SQLException {
        String sql = "SELECT * FROM tasks ORDER BY due_date ASC";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Task> tasks = new ArrayList<>();

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getDate("due_date"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }

            return tasks;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DatabaseUtil.closeConnection(conn);
        }
    }

    /**
     * Retrieves tasks by status
     *
     * @param status The status to filter by ("Pending" or "Completed")
     * @return A list of tasks with the specified status
     * @throws SQLException If a database access error occurs
     */
    public List<Task> getTasksByStatus(String status) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE status = ? ORDER BY due_date ASC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Task> tasks = new ArrayList<>();

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getDate("due_date"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }

            return tasks;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DatabaseUtil.closeConnection(conn);
        }
    }



}