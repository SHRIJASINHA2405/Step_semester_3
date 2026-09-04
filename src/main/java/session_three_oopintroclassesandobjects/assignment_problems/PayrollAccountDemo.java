package session_three_oopintroclassesandobjects.assignment_problems;
public class PayrollAccountDemo {

    public static void main(String[] args) {

        // Create payroll account
        PayrollAccount account = new PayrollAccount(50000);

        // Credit bonus
        account.creditBonus(5000);

        // Deduct 10% tax from basic salary
        account.deductTax(10);

        // Display net salary
        System.out.println(
                "Net salary: Rs " + account.getNetSalary()
        );
    }
}


class PayrollAccount {

    // Private fields
    private double basicSalary;
    private double bonus;


    // Public constructor
    public PayrollAccount(double basicSalary) {

        if (basicSalary < 0) {

            this.basicSalary = 0;

            System.out.println(
                    "Warning: Negative salary. Starting with Rs 0."
            );

        } else {

            this.basicSalary = basicSalary;
        }

        // Initial bonus is 0
        this.bonus = 0;
    }


    // Method to credit bonus
    public void creditBonus(double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Bonus rejected: amount must be greater than 0."
            );

        } else {

            bonus = bonus + amount;

            System.out.println(
                    "Bonus credited: Rs " + amount
            );
        }
    }


    // Method to deduct tax from basic salary
    public void deductTax(double percent) {

        if (percent < 0 || percent > 100) {

            System.out.println(
                    "Tax deduction rejected: percentage must be between 0 and 100."
            );

        } else {

            double taxAmount =
                    basicSalary * percent / 100;

            basicSalary = basicSalary - taxAmount;

            System.out.println(
                    "Tax deducted: " + percent + "%"
            );
        }
    }


    // Read-only access to net salary
    public double getNetSalary() {

        return basicSalary + bonus;
    }
}