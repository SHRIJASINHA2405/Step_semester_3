package session_one_javastring.class_problems;
import java.util.Scanner;

public class PalindromeCheckerDemo {

    // Approach 1: Iterative comparison
    public static boolean isPalindromeIterative(String text) {

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {

            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }


    // Approach 2: Recursive comparison
    public static boolean isPalindromeRecursive(String text) {

        // Base case
        if (text.length() <= 1) {
            return true;
        }

        // Compare first and last characters
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }

        // Recursive call with the middle part of the string
        return isPalindromeRecursive(
                text.substring(1, text.length() - 1)
        );
    }


    // Approach 3: Array reversal
    public static boolean isPalindromeArrayReversal(String text) {

        // Convert String into character array
        char[] characters = text.toCharArray();

        // Create another array for reversed characters
        char[] reversed = new char[characters.length];

        // Reverse the array
        for (int i = 0; i < characters.length; i++) {
            reversed[i] = characters[characters.length - 1 - i];
        }

        // Convert reversed array back to String
        String reversedText = new String(reversed);

        // Compare original and reversed strings
        return text.equals(reversedText);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or text: ");
        String text = scanner.nextLine();

        // Optional: Ignore spaces and uppercase/lowercase
        text = text.replace(" ", "").toLowerCase();


        // Call all three methods
        boolean iterativeResult = isPalindromeIterative(text);
        boolean recursiveResult = isPalindromeRecursive(text);
        boolean arrayResult = isPalindromeArrayReversal(text);


        // Display results
        System.out.println("\n========== PALINDROME RESULTS ==========");

        System.out.println(
                "Iterative: " +
                        (iterativeResult ? "Palindrome" : "Not Palindrome")
        );

        System.out.println(
                "Recursive: " +
                        (recursiveResult ? "Palindrome" : "Not Palindrome")
        );

        System.out.println(
                "Array Reversal: " +
                        (arrayResult ? "Palindrome" : "Not Palindrome")
        );


        // Verify all approaches agree
        if (iterativeResult == recursiveResult &&
                recursiveResult == arrayResult) {

            System.out.println("\nAll three approaches agree.");
        } else {
            System.out.println("\nResults do not agree!");
        }

        scanner.close();
    }
}