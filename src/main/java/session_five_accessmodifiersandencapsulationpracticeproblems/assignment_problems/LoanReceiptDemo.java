package session_five_accessmodifiersandencapsulationpracticeproblems.assignment_problems;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId,
                       String[] bookIds) {

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {

        // Defensive copy
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        String[] correctedIds =
                bookIds.clone();

        correctedIds[index] = newId;

        return new LoanReceipt(
                memberId,
                correctedIds
        );
    }
}

class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

class CirculationLedger {

    static String branchCode;

    static {
        branchCode = "BR-PT-01";
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                regular++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + referenceOnly
                + " reference-only | "
                + regular
                + " regular";
    }
}

public class LoanReceiptDemo {

    public static void main(String[] args) {

        LoanReceipt receipt =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{
                                "BK-100",
                                "BK-101"
                        }
                );

        System.out.println(
                "Original: "
                        + java.util.Arrays.toString(
                        receipt.getBookIds()
                )
        );

        String[] ids =
                receipt.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                "After external modification: "
                        + java.util.Arrays.toString(
                        receipt.getBookIds()
                )
        );

        LoanReceipt corrected =
                receipt.withCorrectedBookId(
                        1,
                        "BK-102"
                );

        System.out.println(
                "Original after correction: "
                        + java.util.Arrays.toString(
                        receipt.getBookIds()
                )
        );

        System.out.println(
                "Corrected receipt: "
                        + java.util.Arrays.toString(
                        corrected.getBookIds()
                )
        );

        ReferenceOnlyLoanReceipt referenceReceipt =
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                );

        System.out.println(
                "Room: "
                        + referenceReceipt.getRoomNumber()
        );

        LoanReceipt[] receipts = {

                referenceReceipt,

                null,

                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                CirculationLedger
                        .processNightlyCirculation(
                                receipts
                        )
        );

        System.out.println(
                "Branch: "
                        + CirculationLedger.branchCode
        );
    }
}