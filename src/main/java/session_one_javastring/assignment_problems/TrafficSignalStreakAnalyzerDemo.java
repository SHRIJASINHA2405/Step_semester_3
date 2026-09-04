package session_one_javastring.assignment_problems;
import java.util.Scanner;

public class TrafficSignalStreakAnalyzerDemo {

    // Method to find the longest streak
    public static void findLongestStreak(String signalLog) {

        // Handle empty input
        if (signalLog == null || signalLog.length() == 0) {
            System.out.println("Signal log is empty.");
            return;
        }

        char longestColor = signalLog.charAt(0);
        int longestStreak = 1;

        int currentStreak = 1;

        // Start from the second character
        for (int i = 1; i < signalLog.length(); i++) {

            // Compare current character with previous character
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {

                currentStreak++;

            } else {

                // Check if current streak is the longest
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                    longestColor = signalLog.charAt(i - 1);
                }

                // Start a new streak
                currentStreak = 1;
            }
        }

        // Check the final streak
        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
            longestColor = signalLog.charAt(signalLog.length() - 1);
        }

        System.out.println(
                "Longest Streak: '" +
                        longestColor +
                        "' repeated " +
                        longestStreak +
                        " times"
        );
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter signal log: ");
        String signalLog = scanner.nextLine();

        findLongestStreak(signalLog);

        scanner.close();
    }
}