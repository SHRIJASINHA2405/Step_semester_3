package session_six_oopinheritanceandpolymorphism.assignment_problems;
class SettlementGymMember {

    private static int counter = 2000;

    private final String membershipNumber;

    int monthlyFee;
    int feesPaid;

    SettlementGymMember(int monthlyFee) {

        counter++;

        membershipNumber =
                "GYM-" + counter;

        this.monthlyFee = monthlyFee;
        feesPaid = 0;
    }

    void payFee(int amount) {

        feesPaid += amount;
    }

    void payFee(int amount, String mode) {

        System.out.println(
                "Payment Mode: " + mode
        );

        payFee(amount);
    }

    int getFeesPaid() {

        return feesPaid;
    }

    String getMembershipNumber() {

        return membershipNumber;
    }

    static boolean isValidReferralCode(
            String code) {

        if (code == null ||
                code.length() != 4) {

            return false;
        }

        if (code.charAt(0) != 'G') {

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

        return counter - 2000;
    }
}


class SettlementGroupMember
        extends SettlementGymMember {

    String className;

    SettlementGroupMember(
            int monthlyFee,
            String className) {

        super(monthlyFee);

        this.className = className;
    }
}


public class WeeklySettlementDemo {

    static String processWeeklyCheckIn(
            SettlementGymMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (SettlementGymMember member : members) {

            if (member == null) {

                nullSkipped++;

            } else {

                processed++;

                if (member instanceof SettlementGroupMember) {

                    group++;

                } else {

                    individual++;
                }
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + group
                + " group | "
                + individual
                + " individual";
    }


    public static void main(String[] args) {

        SettlementGymMember m1 =
                new SettlementGymMember(1000);

        System.out.println(
                m1.getMembershipNumber()
        );

        System.out.println(
                SettlementGymMember
                        .getMembersEnrolled()
        );

        System.out.println(
                SettlementGymMember
                        .isValidReferralCode("G45B")
        );

        System.out.println(
                SettlementGymMember
                        .isValidReferralCode("G4B")
        );

        System.out.println(
                SettlementGymMember
                        .isValidReferralCode("X45B")
        );

        m1.payFee(500);

        m1.payFee(500, "UPI");

        System.out.println(
                m1.getFeesPaid()
        );

        SettlementGymMember[] members = {

                new SettlementGroupMember(
                        1500,
                        "Zumba"
                ),

                null,

                new SettlementGymMember(
                        1000
                )
        };

        System.out.println(
                processWeeklyCheckIn(members)
        );
    }
}