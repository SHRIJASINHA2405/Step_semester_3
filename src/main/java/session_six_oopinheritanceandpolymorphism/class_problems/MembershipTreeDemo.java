package session_six_oopinheritanceandpolymorphism.class_problems;
class TreeLibraryMember {

    String memberId;
    int borrowLimit;
    int booksBorrowed;

    TreeLibraryMember(
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
                "General Member | Books Borrowed: "
                        + booksBorrowed
        );
    }
}


class TreeStudentMember
        extends TreeLibraryMember {

    String course;

    TreeStudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Student Member | Course: "
                        + course
                        + " | Books Borrowed: "
                        + booksBorrowed
        );
    }
}


class HonorsTreeStudentMember
        extends TreeStudentMember {

    int bonusLimit;

    HonorsTreeStudentMember(
            String memberId,
            int borrowLimit,
            String course,
            int bonusLimit) {

        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Honors Student Member | Course: "
                        + course
                        + " | Bonus Limit: "
                        + bonusLimit
                        + " | Books Borrowed: "
                        + booksBorrowed
        );
    }
}


class TreeFacultyMember
        extends TreeLibraryMember {

    String department;

    TreeFacultyMember(
            String memberId,
            int borrowLimit,
            String department) {

        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Faculty Member | Department: "
                        + department
                        + " | Books Borrowed: "
                        + booksBorrowed
        );
    }
}


public class MembershipTreeDemo {

    static String classifyGeneration(
            TreeLibraryMember member) {

        if (member instanceof HonorsTreeStudentMember) {

            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof TreeFacultyMember) {

            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof TreeStudentMember) {

            return "Student Member";
        }

        return "General Member";
    }


    static int getTotalBooksBorrowed(
            TreeLibraryMember[] members) {

        int total = 0;

        for (TreeLibraryMember member : members) {

            total += member.getBooksBorrowed();
        }

        return total;
    }


    public static void main(String[] args) {

        TreeLibraryMember general =
                new TreeLibraryMember(
                        "STU1", 3
                );

        TreeStudentMember student =
                new TreeStudentMember(
                        "STU2", 3, "CSE"
                );

        HonorsTreeStudentMember honors =
                new HonorsTreeStudentMember(
                        "STU3", 3, "ECE", 2
                );

        TreeFacultyMember faculty =
                new TreeFacultyMember(
                        "STU4", 5, "Physics"
                );

        student.borrowBook();
        student.borrowBook();

        honors.borrowBook();

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        general.displayInfo();
        student.displayInfo();
        honors.displayInfo();
        faculty.displayInfo();

        System.out.println(
                classifyGeneration(honors)
        );

        System.out.println(
                classifyGeneration(faculty)
        );

        TreeLibraryMember[] members = {
                student,
                honors,
                faculty
        };

        System.out.println(
                getTotalBooksBorrowed(members)
        );
    }
}