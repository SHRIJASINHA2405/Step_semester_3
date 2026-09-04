package session_one_javastring.class_problems;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissorsDemo {

    // Method to determine the winner of one round
    public static String playRound(String playerMove, String computerMove) {

        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if (
                (playerMove.equalsIgnoreCase("Rock") &&
                        computerMove.equalsIgnoreCase("Scissors")) ||

                        (playerMove.equalsIgnoreCase("Paper") &&
                                computerMove.equalsIgnoreCase("Rock")) ||

                        (playerMove.equalsIgnoreCase("Scissors") &&
                                computerMove.equalsIgnoreCase("Paper"))
        ) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        int rounds = 5;

        // Arrays for storing round information
        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < rounds; i++) {

            System.out.println("\nRound " + (i + 1));

            // Player input
            System.out.print("Enter Rock, Paper, or Scissors: ");
            String playerMove = scanner.nextLine();

            // Input validation
            while (
                    !playerMove.equalsIgnoreCase("Rock") &&
                            !playerMove.equalsIgnoreCase("Paper") &&
                            !playerMove.equalsIgnoreCase("Scissors")
            ) {
                System.out.print("Invalid input! Enter Rock, Paper, or Scissors: ");
                playerMove = scanner.nextLine();
            }

            // Generate computer move randomly
            String computerMove = moves[random.nextInt(3)];

            // Play the round
            String result = playRound(playerMove, computerMove);

            // Store data in arrays
            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            // Update statistics
            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            // Display current round result
            System.out.println("Player: " + playerMove);
            System.out.println("Computer: " + computerMove);
            System.out.println("Result: " + result);
        }

        // Calculate win percentage
        double winPercentage = (wins * 100.0) / rounds;

        // Final Summary
        System.out.println("\n========== FINAL SUMMARY ==========");

        System.out.printf(
                "%-10s %-15s %-15s %-20s%n",
                "Round",
                "Player Move",
                "Computer Move",
                "Result"
        );

        for (int i = 0; i < rounds; i++) {
            System.out.printf(
                    "%-10d %-15s %-15s %-20s%n",
                    i + 1,
                    playerMoves[i],
                    computerMoves[i],
                    results[i]
            );
        }

        System.out.println("\nWins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.printf("Win %% = %.1f%%%n", winPercentage);

        scanner.close();
    }
}