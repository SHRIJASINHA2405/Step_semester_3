package session_one_javastring.class_problems;
import java.util.Scanner;

public class ReverseCustomerNameDemo {

    // Method to reverse the customer name
    public static String reverseCustomerName(String customerName) {

        // Convert String into character array
        char[] characters = customerName.toCharArray();

        // Create array for reversed characters
        char[] reversedCharacters = new char[characters.length];

        // Traverse from the end of the original array
        for (int i = 0; i < characters.length; i++) {
            reversedCharacters[i] =
                    characters[characters.length - 1 - i];
        }

        // Convert character array back to String
        return new String(reversedCharacters);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        // Call the method
        String reversedName =
                reverseCustomerName(customerName);

        // Print both names
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversedName);

        scanner.close();
    }
}