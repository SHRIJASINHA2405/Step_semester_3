package session_two_stringoperationsandperformance.class_programs;
import java.util.Scanner;

public class BankTransactionReferenceDemo {

    // Method to normalize the reference
    public static String normalizeReference(String raw) {

        // Remove leading and trailing spaces
        String reference = raw.trim();

        // Normalize first 3 characters only if available
        if (reference.length() >= 3) {

            String bankCode = reference.substring(0, 3).toUpperCase();

            String remainingPart = reference.substring(3);

            reference = bankCode + remainingPart;
        }

        return reference;
    }


    // Method to validate and format the reference
    public static String validateAndFormat(String reference) {

        // Check length
        if (reference.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check remaining 11 characters are digits
        for (int i = 3; i < reference.length(); i++) {

            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: reference body must contain only digits";
            }
        }

        // Extract bank code
        String bankCode = reference.substring(0, 3);

        // Extract date: ddMMyy
        String date = reference.substring(3, 9);

        String day = date.substring(0, 2);
        String month = date.substring(2, 4);
        String year = date.substring(4, 6);

        // Extract sequence number
        String sequence = reference.substring(9);

        // Build formatted output
        StringBuilder formattedReference = new StringBuilder();

        formattedReference.append("[");
        formattedReference.append(bankCode);
        formattedReference.append("] DATE: ");

        formattedReference.append(day);
        formattedReference.append("/");
        formattedReference.append(month);
        formattedReference.append("/");
        formattedReference.append(year);

        formattedReference.append(" | SEQ: ");
        formattedReference.append(sequence);

        return formattedReference.toString();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");

        String rawReference = scanner.nextLine();

        // Normalize the reference
        String normalizedReference =
                normalizeReference(rawReference);

        // Validate and format
        String result =
                validateAndFormat(normalizedReference);

        System.out.println(result);

        scanner.close();
    }
}