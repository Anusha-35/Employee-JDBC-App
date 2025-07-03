# Employee-JDBC-App

A simple Java console application demonstrating CRUD (Create, Read, Update, Delete) operations using JDBC and MySQL.

## 🚀 Features
- Add new employees to a MySQL database
- View existing employees in a tabular format
- Update employee department and/or salary
- Delete employees by ID
- Interactive console-based menu

## 📦 Technologies Used
- Java
- JDBC (MySQL Connector/J)
- MySQL
- VS Code

## 🗂️ Project Structure

Employee-JDBC-App/
├── DBConnection.java
├── EmployeeApp.java
├── mysql-connector-j-9.3.0.jar
├── setup.sql
└── README.md

Create the database & table in MySQL:

Open MySQL client or Workbench.

Run the commands in setup.sql, or manually execute:

sql
Copy code
CREATE DATABASE employee_db;
USE employee_db;
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    salary DOUBLE
);
Update DBConnection.java with your MySQL root password.

Compile your Java files with the JDBC driver:

bash
Copy code
javac -cp ".;mysql-connector-j-9.3.0.jar" DBConnection.java EmployeeApp.java
Run the application:

bash
Copy code
java -cp ".;mysql-connector-j-9.3.0.jar" EmployeeApp

