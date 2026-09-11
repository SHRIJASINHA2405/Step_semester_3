package session_five_accessmodifiersandencapsulationpracticeproblems.class_problems;

class CineScreen {

    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {

        if (seatsTotal <= 0) {

            throw new IllegalArgumentException(
                    "seatsTotal must be positive"
            );
        }

        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {

        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {

        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }
}

public class CineScreenDemo {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        try {

            CineScreen invalidScreen =
                    new CineScreen(0);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Construction rejected"
            );
        }

        CineScreen screen =
                new CineScreen(2);

        System.out.println(
                "Total seats: "
                        + screen.getSeatsTotal()
        );

        screen.bookSeat();
        screen.bookSeat();
        screen.bookSeat();

        System.out.println(
                "Available after booking: "
                        + screen.getSeatsAvailable()
        );

        screen.cancelBooking();
        screen.cancelBooking();
        screen.cancelBooking();

        System.out.println(
                "Available after cancellation: "
                        + screen.getSeatsAvailable()
        );
    }
}