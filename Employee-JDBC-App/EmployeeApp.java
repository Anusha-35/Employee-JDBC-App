import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Employee Database App ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: updateEmployee(); break;
                case 4: deleteEmployee(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // TODO: Implement methods addEmployee, viewEmployees, updateEmployee, deleteEmployee
    private static void addEmployee() {
    try (Connection conn = DBConnection.getConnection()) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);
            pstmt.executeUpdate();
            System.out.println("Employee added successfully!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private static void viewEmployees() {
    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
        System.out.printf("%-5s %-20s %-20s %-10s\n", "ID", "Name", "Department", "Salary");
        while (rs.next()) {
            System.out.printf("%-5d %-20s %-20s %-10.2f\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private static void updateEmployee() {
    try (Connection conn = DBConnection.getConnection()) {
        System.out.print("Enter employee ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("What do you want to update?");
        System.out.println("1. Department");
        System.out.println("2. Salary");
        System.out.println("3. Both Department and Salary");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        String sql = null;
        PreparedStatement pstmt = null;

        switch (choice) {
            case 1:
                System.out.print("Enter new department: ");
                String department = scanner.nextLine();
                sql = "UPDATE employees SET department = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, department);
                pstmt.setInt(2, id);
                break;
            case 2:
                System.out.print("Enter new salary: ");
                double salary = Double.parseDouble(scanner.nextLine());
                sql = "UPDATE employees SET salary = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, salary);
                pstmt.setInt(2, id);
                break;
            case 3:
                System.out.print("Enter new department: ");
                department = scanner.nextLine();
                System.out.print("Enter new salary: ");
                salary = Double.parseDouble(scanner.nextLine());
                sql = "UPDATE employees SET department = ?, salary = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, department);
                pstmt.setDouble(2, salary);
                pstmt.setInt(3, id);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found!");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private static void deleteEmployee() {
    try (Connection conn = DBConnection.getConnection()) {
        System.out.print("Enter employee ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
