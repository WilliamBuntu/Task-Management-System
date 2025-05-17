-- Create the database
CREATE DATABASE IF NOT EXISTS task_management;
USE task_management;

-- Create the tasks table
CREATE TABLE IF NOT EXISTS tasks (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(100) NOT NULL,
    description TEXT,
    due_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

-- Add some sample data
INSERT INTO tasks (title, description, due_date, status)
VALUES
    ('Complete Project Proposal', 'Draft and submit the project proposal for client approval', '2025-05-25', 'Pending'),
    ('Review Team Timesheet', 'Review and approve team timesheets for the current sprint', '2025-05-20', 'Pending'),
    ('Set up Development Environment', 'Install and configure tools needed for the new project', '2025-05-18', 'Completed'),
    ('Meeting with Stakeholders', 'Discuss project requirements and timeline with stakeholders', '2025-05-30', 'Pending');