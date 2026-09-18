package session_six_oopinheritanceandpolymorphism.assignment_problems;
class BasicGymMember {

    String memberId;
    int monthlyFee;
    int sessionsAttended;

    BasicGymMember(String memberId, int monthlyFee) {

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        sessionsAttended = 0;
    }

    void attendSession() {

        sessionsAttended++;
    }

    int getSessionsAttended() {

        return sessionsAttended;
    }

    static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {

            if (id == null ||
                    id.trim().isEmpty() ||
                    id.length() < 4) {

                rejected++;
            }
            else {

                BasicGymMember member =
                        new BasicGymMember(id, monthlyFee);

                signedUp++;
            }
        }

        return "Signed Up: " + signedUp
                + " | Rejected: " + rejected;
    }
}


class BasicPremiumMember extends BasicGymMember {

    String trainerName;

    BasicPremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }
}


public class GymMembershipDemo {

    public static void main(String[] args) {

        BasicPremiumMember p =
                new BasicPremiumMember(
                        "MEM01",
                        2000,
                        "Coach Riya"
                );

        p.attendSession();
        p.attendSession();

        System.out.println(
                p.getSessionsAttended()
        );

        String[] memberIds = {
                "MEM1",
                "GM1",
                "MEM2",
                " ",
                "MEM3"
        };

        System.out.println(
                BasicGymMember.signUpBatch(
                        memberIds,
                        1000
                )
        );
    }
}