package novaTech.model;

import java.util.Date;

/**
 * Represents a Task in the Task Management System
 */
public class Task {
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private String status; // "Pending" or "Completed"

    // Default constructor
    public Task() {
    }

    // Parameterized constructor
    public Task(int id, String title, String description, Date dueDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Constructor without ID (for new tasks)
    public Task(String title, String description, Date dueDate, String status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
                + ", status=" + status + "]";
    }
}