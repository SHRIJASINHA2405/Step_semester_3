package session_four_constructorsandjavakeywords.class_problems;

class SrmStudent {

    static String collegeName;
    static String academicYear;

    String name;

    // Static block runs only once
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";

        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;

        System.out.println("Student record created: " + this.name);
    }
}


public class OneTimeCollegeSetupDemo {

    public static void main(String[] args) {

        String[] names = {
                "Ravi",
                "Meera",
                "Karthik",
                "Divya",
                "Anitha"
        };

        // Array to store all student objects
        SrmStudent[] students = new SrmStudent[names.length];

        // Create all students in a single loop
        for (int i = 0; i < names.length; i++) {
            students[i] = new SrmStudent(names[i]);
        }
    }
}