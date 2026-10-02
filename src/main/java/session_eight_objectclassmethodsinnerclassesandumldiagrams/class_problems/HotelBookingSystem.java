package session_eight_objectclassmethodsinnerclassesandumldiagrams.class_problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// ================= HOTEL ROOM =================

abstract class HotelRoom {

    private final int roomNumber;
    private final String roomType;
    private final double basePrice;

    public HotelRoom(int roomNumber, String roomType, double basePrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public abstract double calculatePrice(int numberOfNights);
}

// ================= STANDARD ROOM =================

class HotelStandardRoom extends HotelRoom {

    public HotelStandardRoom(int roomNumber, double basePrice) {
        super(roomNumber, "Standard Room", basePrice);
    }

    @Override
    public double calculatePrice(int numberOfNights) {
        return getBasePrice() * numberOfNights;
    }
}

// ================= DELUXE ROOM =================

class HotelDeluxeRoom extends HotelRoom {

    public HotelDeluxeRoom(int roomNumber, double basePrice) {
        super(roomNumber, "Deluxe Room", basePrice);
    }

    @Override
    public double calculatePrice(int numberOfNights) {
        return getBasePrice() * numberOfNights * 1.20;
    }
}

// ================= SUITE =================

class HotelSuite extends HotelRoom {

    public HotelSuite(int roomNumber, double basePrice) {
        super(roomNumber, "Suite", basePrice);
    }

    @Override
    public double calculatePrice(int numberOfNights) {
        return getBasePrice() * numberOfNights * 1.50;
    }
}

// ================= HOTEL CUSTOMER =================

class HotelCustomer {

    private final int customerId;
    private final String name;

    public HotelCustomer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

// ================= HOTEL RESERVATION =================

class HotelReservation {

    private final HotelCustomer customer;
    private final HotelRoom room;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate cancellationDeadline;

    private boolean active;

    public HotelReservation(
            HotelCustomer customer,
            HotelRoom room,
            LocalDate startDate,
            LocalDate endDate,
            LocalDate cancellationDeadline) {

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(
                    "End date must be after start date."
            );
        }

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
        this.active = true;
    }

    public HotelCustomer getCustomer() {
        return customer;
    }

    public HotelRoom getRoom() {
        return room;
    }

    public int getNumberOfNights() {
        return (int)
                (endDate.toEpochDay() - startDate.toEpochDay());
    }

    public double calculatePrice() {
        return room.calculatePrice(getNumberOfNights());
    }

    public boolean overlaps(
            LocalDate newStart,
            LocalDate newEnd) {

        return active
                && newStart.isBefore(endDate)
                && newEnd.isAfter(startDate);
    }

    public void cancel(LocalDate cancellationDate) {

        if (!active) {
            System.out.println(
                    "Reservation is already cancelled."
            );
            return;
        }

        if (cancellationDate.isAfter(cancellationDeadline)) {
            System.out.println(
                    "Cancellation failed: cancellation deadline has passed."
            );
            return;
        }

        active = false;

        System.out.println(
                "Reservation for "
                        + customer.getName()
                        + ", "
                        + room.getRoomType()
                        + " "
                        + room.getRoomNumber()
                        + " cancelled successfully."
        );
    }
}

// ================= HOTEL MANAGEMENT =================

class HotelManagement {

    private final List<HotelRoom> rooms;
    private final List<HotelReservation> reservations;

    public HotelManagement() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(HotelRoom room) {
        rooms.add(room);
    }

    public boolean isRoomAvailable(
            HotelRoom room,
            LocalDate startDate,
            LocalDate endDate) {

        for (HotelReservation reservation : reservations) {

            if (reservation.getRoom() == room
                    && reservation.overlaps(startDate, endDate)) {

                return false;
            }
        }

        return true;
    }

    public void checkAvailability(
            HotelRoom room,
            LocalDate startDate,
            LocalDate endDate) {

        if (isRoomAvailable(room, startDate, endDate)) {

            System.out.println(
                    room.getRoomType()
                            + " "
                            + room.getRoomNumber()
                            + " is available from "
                            + startDate
                            + " to "
                            + endDate
            );

        } else {

            System.out.println(
                    room.getRoomType()
                            + " "
                            + room.getRoomNumber()
                            + " is not available from "
                            + startDate
                            + " to "
                            + endDate
            );
        }
    }

    public HotelReservation makeReservation(
            HotelCustomer customer,
            HotelRoom room,
            LocalDate startDate,
            LocalDate endDate,
            LocalDate cancellationDeadline) {

        if (!isRoomAvailable(room, startDate, endDate)) {

            throw new IllegalStateException(
                    "Room is not available for the selected dates."
            );
        }

        HotelReservation reservation =
                new HotelReservation(
                        customer,
                        room,
                        startDate,
                        endDate,
                        cancellationDeadline
                );

        reservations.add(reservation);

        System.out.printf(
                "Reservation confirmed for %s, %s %d (%s-%s). Price: ₹%.2f%n",
                customer.getName(),
                room.getRoomType(),
                room.getRoomNumber(),
                startDate,
                endDate,
                reservation.calculatePrice()
        );

        return reservation;
    }

    public void cancelReservation(
            HotelReservation reservation,
            LocalDate cancellationDate) {

        reservation.cancel(cancellationDate);
    }
}

// ================= MAIN CLASS =================

public class HotelBookingSystem {

    public static void main(String[] args) {

        HotelManagement hotel = new HotelManagement();

        // Create rooms

        HotelRoom standard101 =
                new HotelStandardRoom(101, 2500);

        HotelRoom deluxe201 =
                new HotelDeluxeRoom(201, 3000);

        HotelRoom suite301 =
                new HotelSuite(301, 5000);

        hotel.addRoom(standard101);
        hotel.addRoom(deluxe201);
        hotel.addRoom(suite301);

        // Create customers

        HotelCustomer customerA =
                new HotelCustomer(1, "Customer A");

        HotelCustomer customerB =
                new HotelCustomer(2, "Customer B");

        HotelCustomer customerC =
                new HotelCustomer(3, "Customer C");

        // ================= BOOKING 1 =================

        LocalDate jan1 =
                LocalDate.of(2026, 1, 1);

        LocalDate jan5 =
                LocalDate.of(2026, 1, 5);

        hotel.checkAvailability(
                standard101,
                jan1,
                jan5
        );

        HotelReservation reservationA =
                hotel.makeReservation(
                        customerA,
                        standard101,
                        jan1,
                        jan5,
                        LocalDate.of(2025, 12, 30)
                );

        System.out.println();

        // ================= BOOKING 2 =================

        LocalDate jan3 =
                LocalDate.of(2026, 1, 3);

        LocalDate jan7 =
                LocalDate.of(2026, 1, 7);

        hotel.checkAvailability(
                standard101,
                jan3,
                jan7
        );

        try {

            hotel.makeReservation(
                    customerB,
                    standard101,
                    jan3,
                    jan7,
                    LocalDate.of(2026, 1, 1)
            );

        } catch (IllegalStateException e) {

            System.out.println(
                    "Customer B could not reserve the room: "
                            + e.getMessage()
            );
        }

        System.out.println();

        // ================= CANCEL BOOKING =================

        hotel.cancelReservation(
                reservationA,
                LocalDate.of(2025, 12, 29)
        );

        System.out.println();

        // ================= BOOKING 3 =================

        LocalDate feb10 =
                LocalDate.of(2026, 2, 10);

        LocalDate feb12 =
                LocalDate.of(2026, 2, 12);

        hotel.checkAvailability(
                deluxe201,
                feb10,
                feb12
        );

        hotel.makeReservation(
                customerC,
                deluxe201,
                feb10,
                feb12,
                LocalDate.of(2026, 2, 5)
        );
    }
}