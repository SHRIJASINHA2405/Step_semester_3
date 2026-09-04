package session_two_stringoperationsandperformance.class_programs;
import java.util.Scanner;

public class FileExtensionValidatorDemo {

    // Method to validate the file extension
    public static String validateFileExtension(String filename) {

        // Find the position of the last dot
        int dotIndex = filename.lastIndexOf('.');

        // Check if a valid extension exists
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        // Extract extension after the last dot
        String extension = filename.substring(dotIndex + 1);

        // Check accepted extensions
        if (extension.equalsIgnoreCase("pdf") ||
                extension.equalsIgnoreCase("docx") ||
                extension.equalsIgnoreCase("zip")) {

            return "Accepted";
        }

        return "Rejected — invalid file type";
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        String result = validateFileExtension(filename);

        System.out.println(result);

        scanner.close();
    }
}