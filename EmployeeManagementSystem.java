package employeemanagementsystem;

import java.util.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id +
                "\nName: " + name +
                "\nDepartment: " + department +
                "\nSalary: " + salary + "\n";
    }
}

public class EmployeeManagementSystem {
    private static List<Employee> employeeList = new ArrayList<>();
    private static int employeeCounter = 1; // Start employee ID from 1

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee by ID (Binary Search)");
            System.out.println("6. Exit");
            System.out.println("7. Sort Employees By Salary (Quick Sort)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    searchEmployeeByIdBinary(scanner);
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                case 7:
                    sortEmployeesBySalaryQuickSort();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(employeeCounter++, name, department, salary);
        employeeList.add(employee);
        System.out.println("Employee added successfully! Employee ID: " + employee.getId());
    }

    private static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("\n--- Employee List ---");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                System.out.print("Enter new Name (or press Enter to skip): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) employee.setName(name);

                System.out.print("Enter new Department (or press Enter to skip): ");
                String department = scanner.nextLine();
                if (!department.isEmpty()) employee.setDepartment(department);

                System.out.print("Enter new Salary (or press Enter to skip): ");
                String salaryInput = scanner.nextLine();
                if (!salaryInput.isEmpty()) employee.setSalary(Double.parseDouble(salaryInput));

                System.out.println("Employee updated successfully!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Iterator<Employee> iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                System.out.println("Employee deleted successfully!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    // Binary Search for Employee by ID
    private static void searchEmployeeByIdBinary(Scanner scanner) {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Sort the list by ID
        Collections.sort(employeeList, Comparator.comparingInt(Employee::getId));

        int left = 0, right = employeeList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Employee midEmployee = employeeList.get(mid);

            if (midEmployee.getId() == id) {
                System.out.println("\n--- Employee Found ---");
                System.out.println(midEmployee);
                return;
            } else if (midEmployee.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Employee not found!");
    }

    // Quick Sort for Employees by Salary
    private static void sortEmployeesBySalaryQuickSort() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to sort.");
            return;
        }

        quickSort(employeeList, 0, employeeList.size() - 1);

        System.out.println("\nEmployees sorted by Salary:");
        viewEmployees();
    }

    private static void quickSort(List<Employee> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1); // Sort left of pivot
            quickSort(list, pi + 1, high); // Sort right of pivot
        }
    }

    private static int partition(List<Employee> list, int low, int high) {
        double pivot = list.get(high).getSalary();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).getSalary() <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}
