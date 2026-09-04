package session_one_javastring.assignment_problems;
public class DuplicateSeatCheckerDemo {

    // Method to check duplicate seat numbers
    public static void checkDuplicateSeats(int[] seatNumbers) {

        boolean duplicateFound = false;

        // Compare every element with every element after it
        for (int i = 0; i < seatNumbers.length; i++) {

            for (int j = i + 1; j < seatNumbers.length; j++) {

                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println(
                            "Duplicate Seat Number Found: " + seatNumbers[i]
                    );

                    duplicateFound = true;
                }
            }
        }

        // If no duplicate was found
        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }


    public static void main(String[] args) {

        int[] seatNumbers = {101, 102, 103, 102, 105};

        checkDuplicateSeats(seatNumbers);
    }
}