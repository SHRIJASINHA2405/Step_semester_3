package session_two_stringoperationsandperformance.class_programs;
import java.util.Scanner;

public class CSVStudentRecordParserDemo {

    // Method to parse and display student record
    public static void parseStudentRecord(String csvLine) {

        // Split the CSV line using comma
        String[] fields = csvLine.split(",");

        // Validate that exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        // Extract individual fields
        String name = fields[0];
        String rollNumber = fields[1];
        String department = fields[2];

        // Print formatted record
        System.out.println(
                "Name: " + name +
                        " | Roll No: " + rollNumber +
                        " | Dept: " + department
        );
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print(
                "Enter student record (Name,RollNumber,Department): "
        );

        String csvLine = scanner.nextLine();

        parseStudentRecord(csvLine);

        scanner.close();
    }
}