package session_five_accessmodifiersandencapsulationpracticeproblems.class_problems;

class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId,
                          String[] seatNumbers) {

        this.bookingId = bookingId;

        // Defensive copy
        this.seatNumbers = seatNumbers.clone();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {

        // Defensive copy
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(
            int index,
            String newSeat) {

        String[] newSeats =
                seatNumbers.clone();

        newSeats[index] = newSeat;

        return new BookingReceipt(
                bookingId,
                newSeats
        );
    }
}

class GroupBookingReceipt
        extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);

        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class BookingReceiptDemo {

    static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (BookingReceipt receipt : receipts) {

            if (receipt == null) {

                nullCount++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {

                groupCount++;

            } else {

                individualCount++;
            }
        }

        return processed
                + " processed | "
                + nullCount
                + " null skipped | "
                + groupCount
                + " group | "
                + individualCount
                + " individual";
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        BookingReceipt booking =
                new BookingReceipt(
                        "CH-1001",
                        new String[]{"A1", "A2"}
                );

        System.out.println(
                "Booking ID: "
                        + booking.getBookingId()
        );

        System.out.println(
                "Original seats: "
                        + java.util.Arrays.toString(
                        booking.getSeatNumbers()
                )
        );

        String[] seats =
                booking.getSeatNumbers();

        seats[0] = "X";

        System.out.println(
                "After external modification: "
                        + java.util.Arrays.toString(
                        booking.getSeatNumbers()
                )
        );

        BookingReceipt updated =
                booking.withUpdatedSeat(
                        1,
                        "A3"
                );

        System.out.println(
                "Updated seats: "
                        + java.util.Arrays.toString(
                        updated.getSeatNumbers()
                )
        );

        GroupBookingReceipt groupBooking =
                new GroupBookingReceipt(
                        "CH-2002",
                        new String[]{"B1", "B2"},
                        2
                );

        System.out.println(
                "Group size: "
                        + groupBooking.getGroupSize()
        );

        BookingReceipt[] receipts = {

                groupBooking,

                null,

                new BookingReceipt(
                        "CH-3003",
                        new String[]{"C1"}
                )
        };

        System.out.println(
                processNightlySettlement(
                        receipts
                )
        );
    }
}