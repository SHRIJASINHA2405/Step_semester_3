package session_eight_objectclassmethodsinnerclassesandumldiagrams.class_problems;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// ================= EMPLOYEE =================

abstract class Employee {

    private final String employeeId;
    private final String name;
    private final LeavePolicy leavePolicy;

    public Employee(String employeeId, String name, LeavePolicy leavePolicy) {
        this.employeeId = employeeId;
        this.name = name;
        this.leavePolicy = leavePolicy;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public boolean isLeaveAllowed(long days) {
        return leavePolicy.isLeaveAllowed(days);
    }
}

// ================= LEAVE POLICY =================

interface LeavePolicy {

    boolean isLeaveAllowed(long days);
}

// ================= FULL-TIME POLICY =================

class FullTimeLeavePolicy implements LeavePolicy {

    @Override
    public boolean isLeaveAllowed(long days) {
        return days <= 20;
    }
}

// ================= PART-TIME POLICY =================

class PartTimeLeavePolicy implements LeavePolicy {

    @Override
    public boolean isLeaveAllowed(long days) {
        return days <= 10;
    }
}

// ================= CONTRACTOR POLICY =================

class ContractorLeavePolicy implements LeavePolicy {

    @Override
    public boolean isLeaveAllowed(long days) {
        return days <= 5;
    }
}

// ================= EMPLOYEE TYPES =================

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String employeeId, String name) {
        super(employeeId, name, new FullTimeLeavePolicy());
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String employeeId, String name) {
        super(employeeId, name, new PartTimeLeavePolicy());
    }
}

class Contractor extends Employee {

    public Contractor(String employeeId, String name) {
        super(employeeId, name, new ContractorLeavePolicy());
    }
}

// ================= REVIEWER =================

class Reviewer {

    private final String name;

    public Reviewer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// ================= LEAVE REQUEST =================

class LeaveRequest {

    enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    private final Employee employee;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private Status status;

    public LeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date."
            );
        }

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.PENDING;
    }

    public long getNumberOfDays() {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public Status getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void approve() {
        changeStatus(Status.APPROVED);
    }

    public void reject() {
        changeStatus(Status.REJECTED);
    }

    private void changeStatus(Status newStatus) {

        if (status != Status.PENDING) {
            throw new IllegalStateException(
                    "Cannot change status from "
                            + status
                            + " to "
                            + newStatus
            );
        }

        status = newStatus;
    }

    // Used only to demonstrate invalid status change
    public void tryChangeToPending() {
        changeStatus(Status.PENDING);
    }
}

// ================= MAIN CLASS =================

public class EmployeeLeaveRequestWorkflow {

    public static LeaveRequest submitLeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        LeaveRequest request =
                new LeaveRequest(
                        employee,
                        startDate,
                        endDate
                );

        if (!employee.isLeaveAllowed(
                request.getNumberOfDays())) {

            throw new IllegalArgumentException(
                    "Leave policy does not allow "
                            + request.getNumberOfDays()
                            + " days for "
                            + employee.getName()
            );
        }

        System.out.println(
                "Leave request submitted for "
                        + employee.getName()
                        + " ("
                        + startDate
                        + " - "
                        + endDate
                        + "). Status: "
                        + request.getStatus()
        );

        return request;
    }

    public static void reviewRequest(
            LeaveRequest request,
            Reviewer reviewer,
            boolean approve) {

        if (approve) {

            request.approve();

            System.out.println(
                    request.getEmployee().getName()
                            + "'s leave request approved by "
                            + reviewer.getName()
                            + ". Status: "
                            + request.getStatus()
            );

        } else {

            request.reject();

            System.out.println(
                    request.getEmployee().getName()
                            + "'s leave request rejected by "
                            + reviewer.getName()
                            + ". Status: "
                            + request.getStatus()
            );
        }
    }

    public static void main(String[] args) {

        // ================= JOHN =================

        Employee john =
                new FullTimeEmployee(
                        "E101",
                        "John"
                );

        Reviewer alice =
                new Reviewer("Alice");

        LeaveRequest johnRequest =
                submitLeaveRequest(
                        john,
                        LocalDate.of(2026, 1, 1),
                        LocalDate.of(2026, 1, 5)
                );

        reviewRequest(
                johnRequest,
                alice,
                true
        );

        // ================= JANE =================

        Employee jane =
                new PartTimeEmployee(
                        "E102",
                        "Jane"
                );

        Reviewer bob =
                new Reviewer("Bob");

        LeaveRequest janeRequest =
                submitLeaveRequest(
                        jane,
                        LocalDate.of(2026, 2, 10),
                        LocalDate.of(2026, 2, 11)
                );

        reviewRequest(
                janeRequest,
                bob,
                false
        );

        // ================= INVALID STATUS CHANGE =================

        System.out.println();

        try {

            johnRequest.tryChangeToPending();

        } catch (IllegalStateException e) {

            System.out.println(e.getMessage());
        }
    }
}