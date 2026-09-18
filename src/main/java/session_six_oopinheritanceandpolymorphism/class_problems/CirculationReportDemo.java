package session_six_oopinheritanceandpolymorphism.class_problems;
class ReportLibraryMember {

    String memberId;
    int borrowLimit;
    int booksBorrowed;

    ReportLibraryMember(
            String memberId,
            int borrowLimit) {

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        booksBorrowed = 0;
    }

    void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    void displayInfo() {

        System.out.println(
                "General | Books: "
                        + booksBorrowed
        );
    }
}


class ReportStudentMember
        extends ReportLibraryMember {

    String course;

    ReportStudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Student | Course: "
                        + course
                        + " | Books: "
                        + booksBorrowed
        );
    }
}


public class CirculationReportDemo {

    static String batchPrint(
            ReportLibraryMember[] members) {

        StringBuilder report =
                new StringBuilder();

        for (ReportLibraryMember member : members) {

            // Polymorphic call
            member.displayInfo();

            if (member instanceof ReportStudentMember) {

                ReportStudentMember student =
                        (ReportStudentMember) member;

                report.append(
                        "Student | Course: "
                                + student.course
                                + " | Books: "
                                + student.getBooksBorrowed()
                );

                report.append(
                        " [Course via downcast: "
                                + student.course
                                + "] | "
                );

            } else {

                report.append(
                        "General | Books: "
                                + member.getBooksBorrowed()
                                + " | "
                );
            }
        }

        return report.toString();
    }


    public static void main(String[] args) {

        ReportLibraryMember general =
                new ReportLibraryMember(
                        "LB5", 3
                );

        ReportStudentMember student =
                new ReportStudentMember(
                        "STU6", 3, "ECE"
                );

        ReportLibraryMember[] members = {
                general,
                student
        };

        System.out.println(
                batchPrint(members)
        );
    }
}