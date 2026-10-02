package session_eight_objectclassmethodsinnerclassesandumldiagrams.assignment_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// ================= STUDENT =================

class AssignmentStudent {
    private final String studentId;
    private final String name;

    public AssignmentStudent(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

// ================= ASSIGNMENT =================

abstract class Assignment {
    private final String title;
    private final int maximumMarks;
    private final LocalDate dueDate;

    public Assignment(String title, int maximumMarks, LocalDate dueDate) {
        this.title = title;
        this.maximumMarks = maximumMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaximumMarks() {
        return maximumMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // Each assignment type applies its own penalty
    public abstract double applyLatePenalty(double awardedMarks, long lateDays);
}

// ================= CODING ASSIGNMENT =================

class CodingAssignment extends Assignment {

    public CodingAssignment(String title, int maximumMarks, LocalDate dueDate) {
        super(title, maximumMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(double awardedMarks, long lateDays) {
        // 10% penalty for each day late
        double penalty = lateDays * 0.10;
        return awardedMarks * (1 - penalty);
    }
}

// ================= WRITTEN ASSIGNMENT =================

class WrittenAssignment extends Assignment {

    public WrittenAssignment(String title, int maximumMarks, LocalDate dueDate) {
        super(title, maximumMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(double awardedMarks, long lateDays) {
        // 20% penalty for each day late
        double penalty = lateDays * 0.20;
        return awardedMarks * (1 - penalty);
    }
}

// ================= SUBMISSION =================

class AssignmentSubmission {

    private final AssignmentStudent student;
    private final Assignment assignment;
    private final LocalDate submissionDate;

    private SubmissionStatus status;
    private double finalMarks;

    public AssignmentSubmission(
            AssignmentStudent student,
            Assignment assignment,
            LocalDate submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.SUBMITTED;
        this.finalMarks = 0;
    }

    public AssignmentStudent getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public double getFinalMarks() {
        return finalMarks;
    }

    public long getLateDays() {
        if (submissionDate.isAfter(assignment.getDueDate())) {
            return ChronoUnit.DAYS.between(
                    assignment.getDueDate(),
                    submissionDate
            );
        }

        return 0;
    }

    // Status cannot be changed from outside.
    private void changeStatus(SubmissionStatus newStatus) {
        status = newStatus;
    }

    public void grade(double awardedMarks) {

        if (status == SubmissionStatus.GRADED) {
            System.out.println(
                    "Cannot grade: submission has already been graded."
            );
            return;
        }

        if (awardedMarks < 0 || awardedMarks > assignment.getMaximumMarks()) {
            System.out.println(
                    "Invalid marks. Marks must be between 0 and "
                            + assignment.getMaximumMarks() + "."
            );
            return;
        }

        long lateDays = getLateDays();

        finalMarks = assignment.applyLatePenalty(
                awardedMarks,
                lateDays
        );

        changeStatus(SubmissionStatus.GRADED);
    }
}

// ================= STATUS =================

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

// ================= PORTAL =================

class AssignmentPortal {

    public AssignmentSubmission submit(
            AssignmentStudent student,
            Assignment assignment,
            LocalDate submissionDate) {

        AssignmentSubmission submission =
                new AssignmentSubmission(
                        student,
                        assignment,
                        submissionDate
                );

        long lateDays = submission.getLateDays();

        if (lateDays == 0) {
            System.out.println(
                    student.getName()
                            + "'s submission for '"
                            + assignment.getTitle()
                            + "' received (on time). Status: "
                            + submission.getStatus() + "."
            );
        } else {
            System.out.println(
                    student.getName()
                            + "'s submission for '"
                            + assignment.getTitle()
                            + "' received ("
                            + lateDays
                            + " days late). Status: "
                            + submission.getStatus() + "."
            );
        }

        return submission;
    }

    public void grade(
            AssignmentSubmission submission,
            double awardedMarks) {

        submission.grade(awardedMarks);

        System.out.printf(
                "%s graded: %.0f/%d.",
                submission.getStudent().getName(),
                submission.getFinalMarks(),
                submission.getAssignment().getMaximumMarks()
        );

        if (submission.getLateDays() > 0) {
            double penaltyPercentage =
                    getPenaltyPercentage(
                            submission.getAssignment(),
                            submission.getLateDays()
                    );

            System.out.printf(
                    " after %.0f%% late penalty.",
                    penaltyPercentage
            );
        }

        System.out.println(
                " Status: " + submission.getStatus() + "."
        );
    }

    private double getPenaltyPercentage(
            Assignment assignment,
            long lateDays) {

        if (assignment instanceof CodingAssignment) {
            return lateDays * 10;
        }

        if (assignment instanceof WrittenAssignment) {
            return lateDays * 20;
        }

        return 0;
    }

    public void resubmit(AssignmentSubmission submission) {

        if (submission.getStatus() == SubmissionStatus.GRADED) {
            System.out.println(
                    "Cannot resubmit: '"
                            + submission.getAssignment().getTitle()
                            + "' has already been graded."
            );
        } else {
            System.out.println(
                    "Resubmission allowed because the submission "
                            + "has not been graded yet."
            );
        }
    }
}

// ================= MAIN CLASS =================

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        AssignmentPortal portal = new AssignmentPortal();

        // Students
        AssignmentStudent asha =
                new AssignmentStudent("S001", "Asha");

        AssignmentStudent ravi =
                new AssignmentStudent("S002", "Ravi");

        // Assignments
        Assignment codingAssignment =
                new CodingAssignment(
                        "Linked List Lab",
                        50,
                        LocalDate.of(2026, 3, 10)
                );

        Assignment writtenAssignment =
                new WrittenAssignment(
                        "Design Essay",
                        50,
                        LocalDate.of(2026, 3, 12)
                );

        // Asha submits on time
        AssignmentSubmission ashaSubmission =
                portal.submit(
                        asha,
                        codingAssignment,
                        LocalDate.of(2026, 3, 10)
                );

        System.out.println();

        // Ravi submits 2 days late
        AssignmentSubmission raviSubmission =
                portal.submit(
                        ravi,
                        writtenAssignment,
                        LocalDate.of(2026, 3, 14)
                );

        System.out.println();

        // Faculty grades Asha
        portal.grade(ashaSubmission, 45);

        System.out.println();

        // Faculty grades Ravi
        portal.grade(raviSubmission, 40);

        System.out.println();

        // Asha attempts to resubmit
        portal.resubmit(ashaSubmission);
    }
}