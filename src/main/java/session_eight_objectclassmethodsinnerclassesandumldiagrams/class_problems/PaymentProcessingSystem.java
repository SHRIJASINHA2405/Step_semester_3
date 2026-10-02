package session_eight_objectclassmethodsinnerclassesandumldiagrams.class_problems;

import java.util.ArrayList;
import java.util.List;

// ================= PRODUCT =================

class ShoppingProduct {

    private final String productId;
    private final String name;
    private final double price;

    public ShoppingProduct(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// ================= ORDER ITEM =================

class ShoppingOrderItem {

    private final ShoppingProduct product;
    private final int quantity;

    public ShoppingOrderItem(
            ShoppingProduct product,
            int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }

        this.product = product;
        this.quantity = quantity;
    }

    public ShoppingProduct getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

// ================= CUSTOMER =================

class ShoppingCustomer {

    private final String customerId;
    private final String name;

    public ShoppingCustomer(
            String customerId,
            String name) {

        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

// ================= PAYMENT METHOD =================

interface PaymentMethod {

    boolean processPayment(
            ShoppingOrder order,
            double amount);
}

// ================= CREDIT CARD PAYMENT =================

class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(
            ShoppingOrder order,
            double amount) {

        System.out.println(
                "Payment initiated via Credit Card for Order "
                        + order.getOrderId()
        );

        System.out.println(
                "Payment for Order "
                        + order.getOrderId()
                        + " successful."
        );

        return true;
    }
}

// ================= PAYPAL PAYMENT =================

class PayPalPayment implements PaymentMethod {

    @Override
    public boolean processPayment(
            ShoppingOrder order,
            double amount) {

        System.out.println(
                "Payment initiated via PayPal for Order "
                        + order.getOrderId()
        );

        System.out.println(
                "Payment for Order "
                        + order.getOrderId()
                        + " failed."
        );

        return false;
    }
}

// ================= BANK TRANSFER PAYMENT =================

class BankTransferPayment implements PaymentMethod {

    @Override
    public boolean processPayment(
            ShoppingOrder order,
            double amount) {

        System.out.println(
                "Payment initiated via Bank Transfer for Order "
                        + order.getOrderId()
        );

        System.out.println(
                "Payment for Order "
                        + order.getOrderId()
                        + " successful."
        );

        return true;
    }
}

// ================= ORDER =================

class ShoppingOrder {

    private final String orderId;
    private final ShoppingCustomer customer;

    private final List<ShoppingOrderItem> items;

    private String status;

    public ShoppingOrder(
            String orderId,
            ShoppingCustomer customer) {

        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "Pending";

        System.out.println(
                "Order created for "
                        + customer.getName()
                        + "."
        );
    }

    public String getOrderId() {
        return orderId;
    }

    public ShoppingCustomer getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    // Add product to order
    public void addProduct(
            ShoppingProduct product,
            int quantity) {

        ShoppingOrderItem item =
                new ShoppingOrderItem(
                        product,
                        quantity
                );

        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Calculate total order amount
    public double calculateTotal() {

        double total = 0;

        for (ShoppingOrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    // Payment workflow
    public void pay(PaymentMethod paymentMethod) {

        if (items.isEmpty()) {

            System.out.println(
                    "Cannot process payment for an empty order."
            );

            return;
        }

        boolean paymentSuccessful =
                paymentMethod.processPayment(
                        this,
                        calculateTotal()
                );

        if (paymentSuccessful) {
            status = "Paid";
        }

        System.out.println(
                "Order status: " + status
        );
    }
}

// ================= MAIN CLASS =================

public class PaymentProcessingSystem {

    public static void main(String[] args) {

        // ================= CUSTOMER X =================

        ShoppingCustomer customerX =
                new ShoppingCustomer(
                        "C001",
                        "Customer X"
                );

        ShoppingProduct productA =
                new ShoppingProduct(
                        "P001",
                        "Product A",
                        1000
                );

        ShoppingProduct productB =
                new ShoppingProduct(
                        "P002",
                        "Product B",
                        500
                );

        ShoppingOrder orderX =
                new ShoppingOrder(
                        "X",
                        customerX
                );

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        PaymentMethod creditCard =
                new CreditCardPayment();

        orderX.pay(creditCard);

        System.out.println();

        // ================= CUSTOMER Y =================

        ShoppingCustomer customerY =
                new ShoppingCustomer(
                        "C002",
                        "Customer Y"
                );

        ShoppingOrder orderY =
                new ShoppingOrder(
                        "Y",
                        customerY
                );

        PaymentMethod bankTransfer =
                new BankTransferPayment();

        orderY.pay(bankTransfer);

        System.out.println();

        // ================= CUSTOMER Z =================

        ShoppingCustomer customerZ =
                new ShoppingCustomer(
                        "C003",
                        "Customer Z"
                );

        ShoppingProduct productC =
                new ShoppingProduct(
                        "P003",
                        "Product C",
                        2000
                );

        ShoppingOrder orderZ =
                new ShoppingOrder(
                        "Z",
                        customerZ
                );

        orderZ.addProduct(productC, 1);

        PaymentMethod paypal =
                new PayPalPayment();

        orderZ.pay(paypal);
    }
}