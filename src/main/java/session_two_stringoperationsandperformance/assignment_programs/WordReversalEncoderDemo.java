package session_two_stringoperationsandperformance.assignment_programs;
import java.util.Scanner;

public class WordReversalEncoderDemo {

    // Method to reverse every word individually
    public static String reverseEachWord(String sentence) {

        // Split sentence into words
        String[] words = sentence.split(" ");

        // Store the final result
        StringBuilder result = new StringBuilder();

        // Process each word
        for (int i = 0; i < words.length; i++) {

            // Reverse the current word
            StringBuilder reversedWord = new StringBuilder();

            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversedWord.append(words[i].charAt(j));
            }

            // Add reversed word to result
            result.append(reversedWord);

            // Add space between words
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        String reversedSentence = reverseEachWord(sentence);

        System.out.println(reversedSentence);

        scanner.close();
    }
}