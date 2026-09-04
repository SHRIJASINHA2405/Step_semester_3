package session_two_stringoperationsandperformance.class_programs;
import java.util.Scanner;

public class VowelConsonantCounterDemo {

    // Method to count vowels and consonants
    public static void countVowelsAndConsonants(String text) {

        int vowels = 0;
        int consonants = 0;

        // Convert the text to lowercase for case-insensitive comparison
        text = text.toLowerCase();

        // Traverse each character
        for (int i = 0; i < text.length(); i++) {

            char character = text.charAt(i);

            // Ignore spaces
            if (character == ' ') {
                continue;
            }

            // Check for vowels
            if (character == 'a' ||
                    character == 'e' ||
                    character == 'i' ||
                    character == 'o' ||
                    character == 'u') {

                vowels++;

            } else {
                // Since input contains only letters and spaces
                consonants++;
            }
        }

        // Print result
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a book title: ");
        String text = scanner.nextLine();

        countVowelsAndConsonants(text);

        scanner.close();
    }
}