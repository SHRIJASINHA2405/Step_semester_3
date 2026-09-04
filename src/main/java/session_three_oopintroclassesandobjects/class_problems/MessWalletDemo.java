package session_three_oopintroclassesandobjects.class_problems;
public class MessWalletDemo {

    public static void main(String[] args) {

        // Create wallet with opening balance
        MessWallet wallet = new MessWallet(500);

        // Add money
        wallet.topUp(200);

        // Try to deduct more than available balance
        wallet.deduct(1000);

        // Read balance using getter
        System.out.println("Final balance: " + wallet.getBalance());
    }
}


class MessWallet {

    // Private field - cannot be accessed directly outside the class
    private double balance;


    // Public constructor
    public MessWallet(double openingBalance) {

        if (openingBalance < 0) {

            balance = 0;
            System.out.println(
                    "Warning: Negative opening balance. Starting with 0."
            );

        } else {

            balance = openingBalance;
        }
    }


    // Method to add money
    public void topUp(double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Top-up rejected: amount must be greater than 0."
            );

        } else {

            balance = balance + amount;

            System.out.println(
                    "Balance after top-up: " + balance
            );
        }
    }


    // Method to deduct money
    public void deduct(double amount) {

        if (amount > balance) {

            System.out.println(
                    "Deduct rejected: insufficient balance"
            );

        } else {

            balance = balance - amount;

            System.out.println(
                    "Balance after deduction: " + balance
            );
        }
    }


    // Getter method for read-only access
    public double getBalance() {

        return balance;
    }
}