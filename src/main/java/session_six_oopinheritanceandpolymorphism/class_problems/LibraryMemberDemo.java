package session_six_oopinheritanceandpolymorphism.class_problems ;
class LibraryMember {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }
}


class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }
}


public class LibraryMemberDemo {

    static String enrollBatch(
            String[] memberIds,
            int borrowLimit) {

        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {

            if (id != null &&
                    !id.trim().isEmpty() &&
                    id.length() >= 4) {

                LibraryMember m =
                        new LibraryMember(id, borrowLimit);

                enrolled++;
            }
            else {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled +
                " | Rejected: " + rejected;
    }


    public static void main(String[] args) {

        StudentMember s =
                new StudentMember("STU10", 3, "CSE");

        s.borrowBook();
        s.borrowBook();

        System.out.println(
                s.getBooksBorrowed()
        );

        String[] ids =
                {"STU1", "LB1", "STU2", " ", "STU3"};

        System.out.println(
                enrollBatch(ids, 3)
        );
    }
}