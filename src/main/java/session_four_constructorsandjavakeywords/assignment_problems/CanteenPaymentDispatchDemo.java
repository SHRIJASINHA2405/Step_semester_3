package session_four_constructorsandjavakeywords.assigment_problems;

class Payment {

    public void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {

    public double payWithProcessingFee(double amount) {

        double totalCharged = amount + (amount * 0.02);

        System.out.println(
                "Charged (card, incl. fee): Rs " + totalCharged
        );

        return totalCharged;
    }
}

class PaymentProcessor {

    public double processTransaction(Payment payment, double amount) {

        if (payment instanceof CardPayment) {

            CardPayment cardPayment = (CardPayment) payment;

            return cardPayment.payWithProcessingFee(amount);

        } else {

            payment.pay(amount);

            return amount;
        }
    }
}

public class CanteenPaymentDispatchDemo {

    public static void main(String[] args) {

        Payment[] payments = {
                new CardPayment(),
                new Payment(),
                new CardPayment(),
                new Payment(),
                new CardPayment()
        };

        double[] amounts = {
                100,
                50,
                200,
                75,
                120
        };

        PaymentProcessor processor = new PaymentProcessor();

        double totalCollected = 0;

        // Process all transactions in one loop
        for (int i = 0; i < payments.length; i++) {

            double actualAmount =
                    processor.processTransaction(
                            payments[i],
                            amounts[i]
                    );

            totalCollected = totalCollected + actualAmount;
        }

        System.out.println(
                "Total Collected: Rs " + totalCollected
        );
    }
}