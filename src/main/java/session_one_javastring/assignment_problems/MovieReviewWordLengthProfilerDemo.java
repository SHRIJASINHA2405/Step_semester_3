package session_one_javastring.assignment_problems;
import java.util.Scanner;

public class MovieReviewWordLengthProfilerDemo {

    // Method to classify words according to their length
    public static void classifyWordLengths(String review) {

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        // Split the review into individual words
        String[] words = review.split("\\s+");

        // Check every word
        for (int i = 0; i < words.length; i++) {

            // Remove punctuation for more accurate word length
            String word = words[i].replaceAll("[^a-zA-Z]", "");

            int length = word.length();

            // Ignore empty words
            if (length == 0) {
                continue;
            }

            // Classify according to length
            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        // Print final result
        System.out.println("Short: " + shortCount);
        System.out.println("Medium: " + mediumCount);
        System.out.println("Long: " + longCount);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter movie review: ");
        String review = scanner.nextLine();

        classifyWordLengths(review);

        scanner.close();
    }
}