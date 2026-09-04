package session_three_oopintroclassesandobjects.assignment_problems;
public class EmployeeDemo {

    public static void main(String[] args) {

        // Permanent employee
        Employee employee1 =
                new Employee("E-101", "Divya", 65000);

        // Intern employee
        Employee employee2 =
                new Employee("E-102", "Arjun");

        // Print profiles
        employee1.printProfile();
        employee2.printProfile();
    }
}


class Employee {

    // Fields
    String empId;
    String empName;
    double salary;
    boolean isIntern;


    // Constructor for permanent employees
    public Employee(String empId, String empName, double salary) {

        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }


    // Constructor for interns
    public Employee(String empId, String empName) {

        // Constructor chaining
        this(empId, empName, 0);

        // Set intern status
        this.isIntern = true;
    }


    // Method to print employee profile
    public void printProfile() {

        System.out.println(
                empId + " | " +
                        empName + " | Rs " +
                        salary + " | Intern: " +
                        isIntern
        );
    }
}