package session_two_stringoperationsandperformance.class_programs;
import java.util.Scanner;

public class MaskedPhoneNumberFormatterDemo {

    // Method to validate and mask phone number
    public static String maskPhoneNumber(String phone) {

        // Check whether the phone number has exactly 10 characters
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Check whether every character is a digit
        for (int i = 0; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        // Extract the last 4 digits
        String lastFourDigits = phone.substring(6);

        // Build the masked phone number
        StringBuilder maskedPhone = new StringBuilder();

        maskedPhone.append("XXXXXX");
        maskedPhone.append(lastFourDigits);

        // Insert '-' between mask and last 4 digits
        maskedPhone.insert(6, "-");

        return maskedPhone.toString();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        String result = maskPhoneNumber(phone);

        System.out.println(result);

        scanner.close();
    }
}