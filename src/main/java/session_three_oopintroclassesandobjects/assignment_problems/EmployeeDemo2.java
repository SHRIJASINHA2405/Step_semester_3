package session_three_oopintroclassesandobjects.assignment_problems;
public class EmployeeDemo2 {

    public static void main(String[] args) {

        // Create three Employee2 objects
        Employee2 employee1 =
                new Employee2("Divya", 65000);

        Employee2 employee2 =
                new Employee2("Arjun", 45000);

        Employee2 employee3 =
                new Employee2("Priya", 55000);


        // Call static method using the class name
        Employee2.printCompanyInfo();
    }
}


class Employee2 {

    // Instance fields
    String empName;
    double salary;


    // Static fields shared by all Employee2 objects
    static String companyName =
            "Bright Horizon Technologies";

    static int employeeCount = 0;


    // Constructor
    Employee2(String empName, double salary) {

        this.empName = empName;
        this.salary = salary;

        // Increment whenever an Employee2 object is created
        employeeCount++;
    }


    // Static method
    static void printCompanyInfo() {

        System.out.println(companyName);
        System.out.println(
                "Employees on record: " + employeeCount
        );
    }
}