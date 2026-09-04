package session_three_oopintroclassesandobjects.class_problems;
public class StudentDemo {

    public static void main(String[] args) {

        // Create two Student objects
        Student student1 = new Student("Ravi", 85.5);
        Student student2 = new Student("Anitha", 92.0);

        // Call static method using the class name
        Student.printCollegeInfo();
    }
}


class Student {

    // Instance fields
    String name;
    double attendance;

    // Static fields shared by all Student objects
    static String collegeName =
            "SRM Institute of Science and Technology";

    static int studentCount = 0;


    // Constructor
    Student(String name, double attendance) {

        this.name = name;
        this.attendance = attendance;

        // Increment once whenever a Student object is created
        studentCount++;
    }


    // Static method
    static void printCollegeInfo() {

        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}