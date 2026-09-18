package session_six_oopinheritanceandpolymorphism.class_problems;
class AuditLibraryMember {

    private static int counter = 100;

    private final String memberNumber;

    int borrowLimit;
    int booksBorrowed;

    AuditLibraryMember(int borrowLimit) {

        counter++;

        memberNumber =
                "LIB-" + counter;

        this.borrowLimit = borrowLimit;
        booksBorrowed = 0;
    }

    void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    void borrowBook(String genre) {

        System.out.println(
                "Genre: " + genre
        );

        borrowBook();
    }

    int getBooksBorrowed() {

        return booksBorrowed;
    }

    String getMemberNumber() {

        return memberNumber;
    }

    static boolean isValidRenewalCode(
            String code) {

        if (code == null ||
                code.length() != 4) {

            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(
                code.charAt(1))) {

            return false;
        }

        if (!Character.isDigit(
                code.charAt(2))) {

            return false;
        }

        if (!Character.isUpperCase(
                code.charAt(3))) {

            return false;
        }

        return true;
    }

    static int getMembersEnrolled() {

        return counter - 100;
    }
}


class AuditFacultyMember
        extends AuditLibraryMember {

    String department;

    AuditFacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }
}


public class NightlyAuditDemo {

    static String processNightlyAudit(
            AuditLibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (AuditLibraryMember member : members) {

            if (member == null) {

                nullSkipped++;

            } else {

                processed++;

                if (member instanceof AuditFacultyMember) {

                    faculty++;

                } else {

                    regular++;
                }
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + faculty
                + " faculty | "
                + regular
                + " regular";
    }


    public static void main(String[] args) {

        AuditLibraryMember m1 =
                new AuditLibraryMember(3);

        System.out.println(
                m1.getMemberNumber()
        );

        System.out.println(
                AuditLibraryMember.getMembersEnrolled()
        );

        System.out.println(
                AuditLibraryMember
                        .isValidRenewalCode("R12A")
        );

        System.out.println(
                AuditLibraryMember
                        .isValidRenewalCode("R1A")
        );

        System.out.println(
                AuditLibraryMember
                        .isValidRenewalCode("X12A")
        );

        m1.borrowBook();

        m1.borrowBook("Fiction");

        System.out.println(
                m1.getBooksBorrowed()
        );

        AuditLibraryMember[] members = {

                new AuditFacultyMember(
                        5, "Physics"
                ),

                null,

                new AuditLibraryMember(3)
        };

        System.out.println(
                processNightlyAudit(members)
        );
    }
}