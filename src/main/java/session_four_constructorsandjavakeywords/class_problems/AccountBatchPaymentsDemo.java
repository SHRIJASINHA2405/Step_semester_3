package session_four_constructorsandjavakeywords.class_problems;

class PaymentFeeAccount {

    String regNo;

    public PaymentFeeAccount(String regNo) {
        this.regNo = regNo;
    }
}

class HostelFeeAccount extends PaymentFeeAccount {

    public HostelFeeAccount(String regNo) {
        super(regNo);
    }
}

class PaymentProcessor {

    int hostelCount = 0;
    int dayScholarCount = 0;

    public void processPayment(PaymentFeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {

            System.out.println(
                    "Paid in two installments (hostel account)"
            );

            hostelCount++;

        } else {

            System.out.println(
                    "Paid in one go (day-scholar account)"
            );

            dayScholarCount++;
        }
    }

    public void printCounters() {

        System.out.println(
                "Hostel accounts processed: " + hostelCount +
                        " | Day-scholar accounts processed: " + dayScholarCount
        );
    }
}

public class AccountBatchPaymentsDemo {

    public static void main(String[] args) {

        PaymentFeeAccount[] accounts = {

                new HostelFeeAccount("RA001"),
                new HostelFeeAccount("RA002"),
                new PaymentFeeAccount("RA003"),
                new PaymentFeeAccount("RA004")
        };

        double amount = 60000;

        PaymentProcessor processor = new PaymentProcessor();

        // Process every account in a single pass
        for (PaymentFeeAccount account : accounts) {

            processor.processPayment(account, amount);
        }

        // Print both counters after the complete batch
        processor.printCounters();
    }
}