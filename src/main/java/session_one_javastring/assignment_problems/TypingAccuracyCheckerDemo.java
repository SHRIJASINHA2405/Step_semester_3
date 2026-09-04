package session_one_javastring.assignment_problems;
import java.util.Scanner;

public class TypingAccuracyCheckerDemo {

    // Method to check typing accuracy
    public static void checkTypingAccuracy(String original, String typed) {

        int matchedCharacters = 0;
        int firstMismatchPosition = -1;

        // Compare each character using its position
        for (int i = 0; i < original.length(); i++) {

            if (original.charAt(i) == typed.charAt(i)) {
                matchedCharacters++;
            } else {

                // Store only the first mismatch
                if (firstMismatchPosition == -1) {
                    firstMismatchPosition = i;
                }
            }
        }

        // Calculate accuracy percentage
        double accuracy =
                (matchedCharacters * 100.0) / original.length();

        // Print result
        System.out.println("Matched: " +
                matchedCharacters + "/" + original.length());

        System.out.printf("Accuracy: %.2f%%%n", accuracy);

        // Display first mismatch
        if (firstMismatchPosition == -1) {

            System.out.println("No Mismatches");

        } else {

            System.out.println(
                    "First Mismatch at position " +
                            (firstMismatchPosition + 1) +
                            " ('" +
                            original.charAt(firstMismatchPosition) +
                            "' vs '" +
                            typed.charAt(firstMismatchPosition) +
                            "')"
            );
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter original passage: ");
        String original = scanner.nextLine();

        System.out.print("Enter typed text: ");
        String typed = scanner.nextLine();

        // Check that both strings have equal length
        if (original.length() != typed.length()) {
            System.out.println(
                    "Error: Both texts must have the same length."
            );
        } else {
            checkTypingAccuracy(original, typed);
        }

        scanner.close();
    }
}