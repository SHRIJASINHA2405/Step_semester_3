package session_four_constructorsandjavakeywords.class_problems;

class Employee {

    String employeeId;
    double salary;

    // Constructor: field/parameter naming clash resolved using this
    public Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        this.salary = salary;
    }

    // Method: field/parameter naming clash resolved using this
    public void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    public void displaySalary() {
        System.out.println(employeeId + " | Final Salary: Rs " + salary);
    }
}

public class PayrollBatchBonusDemo {

    public static void main(String[] args) {

        Employee[] employees = {
                new Employee("E-101", 40000),
                new Employee("E-102", 55000),
                new Employee("E-103", 62000),
                new Employee("E-104", 48000)
        };

        double bonus = 5000;

        // Single pass through every employee
        for (Employee employee : employees) {

            employee.raiseSalary(bonus);
            employee.displaySalary();

        }
    }
}