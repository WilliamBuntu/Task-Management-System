# Task Management System

A web-based task management application built with Java Servlets, JSP, and Postgres, with optional Spring MVC integration.

## Project Overview

This Task Management System allows users to create, view, edit, and delete tasks. Users can track task status, set due dates, and organize their workflow efficiently.

## Features

- Create new tasks with title, description, due date, and status
- View all tasks with optional filtering by status
- Edit existing tasks
- Delete tasks
- Responsive web interface

## Technology Stack

- **Backend:** Java Servlets
- **Frontend:** JSP, HTML, CSS, JavaScript
- **Database:** Postgres
- **Build Tool:** Maven
- **Server:** Apache Tomcat

## Project Structure

## Setup Instructions

### Prerequisites
- JDK 11 or higher
- Maven 3.6 or higher
- Postgres 13 or higher
- Apache Tomcat 9 or higher

### Database Setup
1. Create a Postgres database named `Task_management`
2. Use the following SQL to create the task table:

```sql  
- V1__create_tasks_table.sql
```
### Configuration
1. Edit src/main/resources/db.properties with your database credentials
2. Configure a Tomcat server in your IDE or standalone

### Build and Run
1. Clone the repository
2. Navigate to the project directory
3. Run mvn clean install to build the project
4. Deploy the generated WAR file to Tomcat
5. Access the application at http://localhost:8080/taskmanager

### Usage
### Creating a Task
1. Click "New Task" on the main page
2. Fill in the task details (title, description, due date, status)
3. Click "Save"

### Viewing Tasks
1. The home page displays all tasks
2. Use the status filter dropdown to filter tasks

### Editing a Task
1. Click "Edit" next to the task you want to modify
2. Update the task details
3. Click "Save"