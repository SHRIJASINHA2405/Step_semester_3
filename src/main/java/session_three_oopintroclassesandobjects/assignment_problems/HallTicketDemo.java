package session_three_oopintroclassesandobjects.assignment_problems;
public class HallTicketDemo {

    public static void main(String[] args) {

        // Create one HallTicket object
        HallTicket priya = new HallTicket("Priya", 0);

        // copy points to the SAME object as priya
        HallTicket copy = priya;

        // Change seat number through the second variable
        copy.seatNumber = 45;

        // Print value using the first variable
        System.out.println(
                "Priya's seatNumber (via first variable): "
                        + priya.seatNumber
        );

        // Check whether both variables point to the same object
        System.out.println(
                "copy == priya: " + (copy == priya)
        );

        // Create a separate object with identical field values
        HallTicket separate =
                new HallTicket("Priya", 45);

        // Check whether separate and priya are the same object
        System.out.println(
                "separate == priya: " + (separate == priya)
        );
    }
}


class HallTicket {

    // Fields
    String studentName;
    int seatNumber;


    // Constructor
    HallTicket(String studentName, int seatNumber) {

        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}