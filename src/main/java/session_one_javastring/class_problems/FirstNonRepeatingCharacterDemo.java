package session_one_javastring.class_problems;
import java.util.Scanner;

public class FirstNonRepeatingCharacterDemo {

    // Method to find the first non-repeating character
    public static char findFirstNonRepeatingChar(String text) {

        // Frequency array for ASCII characters
        int[] frequency = new int[256];

        // Count frequency of every character
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            frequency[character]++;
        }

        // Scan from left to right
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            // First character appearing exactly once
            if (frequency[character] == 1) {
                return character;
            }
        }

        // Return a special value if no character is found
        return '\0';
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or sentence: ");
        String text = scanner.nextLine();

        char result = findFirstNonRepeatingChar(text);

        // Check whether a character was found
        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println(
                    "First Non-Repeating Character: '" + result + "'"
            );
        }

        scanner.close();
    }
}