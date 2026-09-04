package session_two_stringoperationsandperformance.assignment_programs;
import java.util.Scanner;

public class LibraryISBNValidatorDemo {

    // Method to normalize the code
    public static String normalizeCode(String raw) {

        // Remove leading and trailing spaces
        String code = raw.trim();

        // Convert only the first 3 characters to uppercase
        if (code.length() >= 3) {

            String publisherCode = code.substring(0, 3).toUpperCase();

            String remainingPart = code.substring(3);

            code = publisherCode + remainingPart;
        }

        return code;
    }


    // Method to validate and format the code
    public static String validateAndFormat(String code) {

        // Check whether the code has exactly 13 characters
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        // Check whether the first 3 characters are letters
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check whether the remaining 10 characters are digits
        for (int i = 3; i < code.length(); i++) {

            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: code body must contain only digits";
            }
        }

        // Extract publisher code
        String publisherCode = code.substring(0, 3);

        // Extract year
        String year = code.substring(3, 7);

        // Extract catalog number
        String catalogNumber = code.substring(7);

        // Build the formatted output
        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(publisherCode);
        result.append("] YEAR: ");
        result.append(year);
        result.append(" | CATALOG: ");
        result.append(catalogNumber);

        return result.toString();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ISBN-style code: ");
        String rawCode = scanner.nextLine();

        // Normalize the code
        String normalizedCode = normalizeCode(rawCode);

        // Validate and format the code
        String result = validateAndFormat(normalizedCode);

        System.out.println(result);

        scanner.close();
    }
}