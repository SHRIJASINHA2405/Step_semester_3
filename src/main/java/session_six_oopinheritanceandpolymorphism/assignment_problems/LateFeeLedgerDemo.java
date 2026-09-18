package session_six_oopinheritanceandpolymorphism.assignment_problems;
class FeeGymMember {

    String memberId;
    int monthlyFee;

    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    FeeGymMember(
            String memberId,
            int monthlyFee) {

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    protected void chargeLateFee(int amount) {

        if (feeCount < 10) {

            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    int[] getLateFeeHistory() {

        int[] copy =
                new int[feeCount];

        for (int i = 0; i < feeCount; i++) {

            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }

    int getTotalLateFees() {

        int total = 0;

        for (int i = 0; i < feeCount; i++) {

            total += lateFeeHistory[i];
        }

        return total;
    }
}


class FeePremiumMember
        extends FeeGymMember {

    String trainerName;

    FeePremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {

        super.chargeLateFee(
                amount / 2
        );
    }
}


public class LateFeeLedgerDemo {

    public static void main(String[] args) {

        FeePremiumMember p =
                new FeePremiumMember(
                        "MEM5",
                        2000,
                        "Coach Riya"
                );

        p.chargeLateFee(200);

        System.out.println(
                p.getTotalLateFees()
        );

        int[] history =
                p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
                p.getLateFeeHistory()[0]
        );
    }
}