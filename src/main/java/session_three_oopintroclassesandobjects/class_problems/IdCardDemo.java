package session_three_oopintroclassesandobjects.class_problems;
public class IdCardDemo {

    public static void main(String[] args) {

        // Create one IdCard object
        IdCard ravi = new IdCard("Ravi", 0);

        // duplicate points to the SAME object as ravi
        IdCard duplicate = ravi;

        // Change booksIssued using the second reference
        duplicate.booksIssued = 3;

        // Print value using the first reference
        System.out.println(
                "Ravi's booksIssued (via first variable): "
                        + ravi.booksIssued
        );

        // Check whether both variables point to the same object
        System.out.println(
                "duplicate == ravi: " + (duplicate == ravi)
        );


        // Create a separate object with identical field values
        IdCard separate = new IdCard("Ravi", 3);

        // Check whether separate and ravi are the same object
        System.out.println(
                "separate == ravi: " + (separate == ravi)
        );
    }
}


class IdCard {

    // Fields
    String name;
    int booksIssued;


    // Constructor
    IdCard(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;
    }
}