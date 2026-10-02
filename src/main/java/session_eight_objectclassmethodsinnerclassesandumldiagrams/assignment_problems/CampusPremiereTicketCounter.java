package session_eight_objectclassmethodsinnerclassesandumldiagrams.assignment_problems;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// ================= CUSTOMER =================

class TicketCustomer {
    private final String customerId;
    private final String name;

    public TicketCustomer(String customerId, String name) {
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

// ================= SEAT =================

abstract class TicketSeat {
    private final String seatNumber;

    public TicketSeat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

// ================= REGULAR SEAT =================

class RegularSeat extends TicketSeat {

    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 150.00;
    }
}

// ================= PREMIUM SEAT =================

class PremiumSeat extends TicketSeat {

    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 250.00;
    }
}

// ================= RECLINER SEAT =================

class ReclinerSeat extends TicketSeat {

    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 400.00;
    }
}

// ================= SHOW =================

class TicketShow {
    private final String showName;
    private final LocalDateTime showStartTime;
    private final List<TicketSeat> seats;
    private final List<TicketSeat> bookedSeats;

    public TicketShow(
            String showName,
            LocalDateTime showStartTime,
            List<TicketSeat> seats) {

        this.showName = showName;
        this.showStartTime = showStartTime;
        this.seats = seats;
        this.bookedSeats = new ArrayList<>();
    }

    public String getShowName() {
        return showName;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public boolean isSeatAvailable(TicketSeat seat) {
        return seats.contains(seat) && !bookedSeats.contains(seat);
    }

    public boolean areSeatsAvailable(List<TicketSeat> requestedSeats) {
        for (TicketSeat seat : requestedSeats) {
            if (!isSeatAvailable(seat)) {
                return false;
            }
        }

        return true;
    }

    public void bookSeats(List<TicketSeat> requestedSeats) {
        bookedSeats.addAll(requestedSeats);
    }

    public void releaseSeats(List<TicketSeat> seatsToRelease) {
        bookedSeats.removeAll(seatsToRelease);
    }

    public TicketSeat findSeat(String seatNumber) {
        for (TicketSeat seat : seats) {
            if (seat.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                return seat;
            }
        }

        return null;
    }
}

// ================= BOOKING =================

class TicketBooking {
    private final TicketCustomer customer;
    private final TicketShow show;
    private final List<TicketSeat> bookedSeats;
    private boolean cancelled;

    public TicketBooking(
            TicketCustomer customer,
            TicketShow show,
            List<TicketSeat> bookedSeats) {

        this.customer = customer;
        this.show = show;
        this.bookedSeats = new ArrayList<>(bookedSeats);
        this.cancelled = false;
    }

    public double calculateTotal() {
        double total = 0;

        for (TicketSeat seat : bookedSeats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {

        if (cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (!LocalDateTime.now().isBefore(show.getShowStartTime())) {
            System.out.println(
                    "Cannot cancel: the show has already started."
            );
            return;
        }

        show.releaseSeats(bookedSeats);
        cancelled = true;

        System.out.println(
                customer.getName()
                        + "'s booking cancelled. Seats "
                        + getSeatNumbers()
                        + " released."
        );
    }

    public String getSeatNumbers() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bookedSeats.size(); i++) {
            result.append(bookedSeats.get(i).getSeatNumber());

            if (i < bookedSeats.size() - 1) {
                result.append(", ");
            }
        }

        return result.toString();
    }

    public TicketCustomer getCustomer() {
        return customer;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}

// ================= TICKET COUNTER =================

class TicketCounter {

    public TicketBooking bookTickets(
            TicketCustomer customer,
            TicketShow show,
            List<TicketSeat> seats) {

        if (seats.isEmpty()) {
            System.out.println("Booking must contain at least one seat.");
            return null;
        }

        if (seats.size() > 6) {
            System.out.println(
                    "Cannot book more than 6 seats per booking."
            );
            return null;
        }

        // Check every requested seat before booking any seat.
        for (TicketSeat seat : seats) {
            if (!show.isSeatAvailable(seat)) {
                System.out.println(
                        "Seat "
                                + seat.getSeatNumber()
                                + " is already booked for this show."
                );
                return null;
            }
        }

        show.bookSeats(seats);

        TicketBooking booking =
                new TicketBooking(customer, show, seats);

        System.out.printf(
                "Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customer.getName(),
                booking.getSeatNumbers(),
                booking.calculateTotal()
        );

        return booking;
    }
}

// ================= MAIN CLASS =================

public class CampusPremiereTicketCounter {

    public static void main(String[] args) {

        // Customers
        TicketCustomer asha =
                new TicketCustomer("C001", "Asha");

        TicketCustomer ravi =
                new TicketCustomer("C002", "Ravi");

        TicketCustomer neha =
                new TicketCustomer("C003", "Neha");

        // Seats
        List<TicketSeat> seats = new ArrayList<>();

        seats.add(new RegularSeat("A1"));
        seats.add(new RegularSeat("A2"));
        seats.add(new PremiumSeat("F5"));
        seats.add(new ReclinerSeat("R1"));

        // Show scheduled in the future
        TicketShow show =
                new TicketShow(
                        "Campus Premiere",
                        LocalDateTime.now().plusHours(2),
                        seats
                );

        TicketCounter counter = new TicketCounter();

        // Asha books A1, A2 and F5
        List<TicketSeat> ashaSeats = new ArrayList<>();

        ashaSeats.add(show.findSeat("A1"));
        ashaSeats.add(show.findSeat("A2"));
        ashaSeats.add(show.findSeat("F5"));

        TicketBooking ashaBooking =
                counter.bookTickets(
                        asha,
                        show,
                        ashaSeats
                );

        System.out.println();

        // Ravi attempts to book A2
        List<TicketSeat> raviFirstAttempt = new ArrayList<>();
        raviFirstAttempt.add(show.findSeat("A2"));

        counter.bookTickets(
                ravi,
                show,
                raviFirstAttempt
        );

        System.out.println();

        // Ravi books R1
        List<TicketSeat> raviSeats = new ArrayList<>();
        raviSeats.add(show.findSeat("R1"));

        counter.bookTickets(
                ravi,
                show,
                raviSeats
        );

        System.out.println();

        // Asha cancels before show starts
        ashaBooking.cancel();

        System.out.println();

        // Neha books A2 after it has been released
        List<TicketSeat> nehaSeats = new ArrayList<>();
        nehaSeats.add(show.findSeat("A2"));

        counter.bookTickets(
                neha,
                show,
                nehaSeats
        );
    }
}