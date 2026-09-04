package session_three_oopintroclassesandobjects.class_problems;
public class CourseDemo {

    public static void main(String[] args) {

        // Theory-only course
        Course course1 =
                new Course("21CSC201J", "Data Structures", 4);

        // Course with lab credits
        Course course2 =
                new Course("21CSC205L", "DSA Lab", 3, 1);


        // Print total credits
        System.out.println(
                course1.code + " total credits: "
                        + course1.totalCredits()
        );

        System.out.println(
                course2.code + " total credits: "
                        + course2.totalCredits()
        );
    }
}


class Course {

    // Fields
    String code;
    String title;
    int credits;
    int labCredits;


    // Four-argument constructor
    public Course(String code, String title,
                  int credits, int labCredits) {

        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }


    // Three-argument constructor
    // Used for theory-only courses
    public Course(String code, String title, int credits) {

        // Constructor chaining
        this(code, title, credits, 0);
    }


    // Method to calculate total credits
    public int totalCredits() {

        return credits + labCredits;
    }
}