package session_six_oopinheritanceandpolymorphism.class_problems;
class FineLibraryMember {

    String memberId;
    int borrowLimit;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    FineLibraryMember(
            String memberId,
            int borrowLimit) {

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {

        if (fineCount < 10) {

            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    int[] getFineHistory() {

        int[] copy = new int[fineCount];

        for (int i = 0; i < fineCount; i++) {

            copy[i] = fineHistory[i];
        }

        return copy;
    }

    int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++) {

            total += fineHistory[i];
        }

        return total;
    }
}


class FineStudentMember
        extends FineLibraryMember {

    String course;

    FineStudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {

        super.chargeFine(amount / 2);
    }
}


public class FineLedgerDemo {

    public static void main(String[] args) {

        FineStudentMember s =
                new FineStudentMember(
                        "STU5", 3, "CSE"
                );

        s.chargeFine(100);

        System.out.println(
                s.getTotalFine()
        );

        int[] history =
                s.getFineHistory();

        history[0] = 999;

        System.out.println(
                s.getFineHistory()[0]
        );
    }
}