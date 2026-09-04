package session_two_stringoperationsandperformance.assignment_programs;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StopWordFrequencyReportDemo {

    public static void printFilteredWordFrequency(String feedback) {

        // Convert text to lowercase
        String cleanedText = feedback.toLowerCase();

        // Remove punctuation using replace()
        cleanedText = cleanedText.replace(".", "");
        cleanedText = cleanedText.replace(",", "");

        // Split text into words
        String[] words = cleanedText.split("\\s+");

        // Fixed list of stop words
        String[] stopWords = {
                "the", "was", "and", "a", "is", "of", "in"
        };

        // HashMap to store word frequencies
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        // Process every word
        for (String word : words) {

            boolean isStopWord = false;

            // Check whether the word is a stop word
            for (String stopWord : stopWords) {

                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            // Count only non-stop words
            if (!isStopWord) {

                if (frequencyMap.containsKey(word)) {

                    frequencyMap.put(
                            word,
                            frequencyMap.get(word) + 1
                    );

                } else {

                    frequencyMap.put(word, 1);
                }
            }
        }

        // Convert HashMap entries into a list for sorting
        List<Map.Entry<String, Integer>> wordList =
                new ArrayList<>(frequencyMap.entrySet());

        // Sort by frequency in descending order
        wordList.sort((word1, word2) ->
                word2.getValue().compareTo(word1.getValue())
        );

        // Print final report
        for (Map.Entry<String, Integer> entry : wordList) {

            System.out.println(
                    entry.getKey() + ": " + entry.getValue()
            );
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter feedback: ");

        String feedback = scanner.nextLine();

        printFilteredWordFrequency(feedback);

        scanner.close();
    }
}