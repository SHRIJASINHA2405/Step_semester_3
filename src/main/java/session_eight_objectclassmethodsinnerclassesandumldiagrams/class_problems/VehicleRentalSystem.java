package session_eight_objectclassmethodsinnerclassesandumldiagrams.class_problems;
import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {

    protected String vehicleId;
    protected String model;
    protected boolean available;

    public Vehicle(String vehicleId, String model) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    public abstract double calculateRentalCharge(int days);
}


class Sedan extends Vehicle {

    public Sedan(String vehicleId, String model) {
        super(vehicleId, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return 50 * days;
    }
}


class SUV extends Vehicle {

    public SUV(String vehicleId, String model) {
        super(vehicleId, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return 80 * days;
    }
}


class Truck extends Vehicle {

    public Truck(String vehicleId, String model) {
        super(vehicleId, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return 100 * days;
    }
}


class Customer {

    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
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


class Rental {

    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double amount;
    private boolean active;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.amount = vehicle.calculateRentalCharge(days);
        this.active = true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isActive() {
        return active;
    }

    public void closeRental() {
        active = false;
        vehicle.returnVehicle();
    }
}


public class VehicleRentalSystem {

    private List<Rental> rentals = new ArrayList<>();

    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(
                    vehicle.getModel() + " is currently unavailable."
            );
            return null;
        }

        vehicle.rent();

        Rental rental = new Rental(customer, vehicle, days);
        rentals.add(rental);

        System.out.println(
                vehicle.getModel()
                        + " rented successfully by "
                        + customer.getCustomerId()
        );

        System.out.printf(
                "Rental charge: $%.2f%n",
                rental.getAmount()
        );

        return rental;
    }


    public void returnVehicle(Rental rental) {

        if (rental == null || !rental.isActive()) {
            return;
        }

        rental.closeRental();

        System.out.println(
                rental.getVehicle().getModel()
                        + " returned by "
                        + rental.getCustomer().getCustomerId()
        );
    }


    public static void main(String[] args) {

        VehicleRentalSystem system = new VehicleRentalSystem();

        Customer customer1 =
                new Customer("Customer 1", "Alice");

        Customer customer2 =
                new Customer("Customer 2", "Bob");

        Customer customer3 =
                new Customer("Customer 3", "Charlie");


        Vehicle sedanA =
                new Sedan("S-101", "Sedan A");

        Vehicle suvB =
                new SUV("SUV-201", "SUV B");


        // Customer 1 rents Sedan A for 3 days
        Rental rental1 =
                system.rentVehicle(customer1, sedanA, 3);


        // Customer 2 attempts to rent the same Sedan
        system.rentVehicle(customer2, sedanA, 2);


        // Customer 1 returns Sedan A
        system.returnVehicle(rental1);


        // Customer 3 rents SUV B for 5 days
        system.rentVehicle(customer3, suvB, 5);
    }
}