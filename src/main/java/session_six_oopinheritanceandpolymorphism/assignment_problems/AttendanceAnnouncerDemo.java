package session_six_oopinheritanceandpolymorphism.assignment_problems;
class AnnouncementGymMember {

    String memberId;
    int monthlyFee;
    int sessionsAttended;

    AnnouncementGymMember(
            String memberId,
            int monthlyFee) {

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

    void displayInfo() {

        System.out.println(
                "Standard | Sessions: "
                        + sessionsAttended
        );
    }
}


class AnnouncementPremiumMember
        extends AnnouncementGymMember {

    String trainerName;

    AnnouncementPremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Premium | Trainer: "
                        + trainerName
                        + " | Sessions: "
                        + sessionsAttended
        );
    }
}


public class AttendanceAnnouncerDemo {

    static String batchPrint(
            AnnouncementGymMember[] members) {

        StringBuilder announcement =
                new StringBuilder();

        for (AnnouncementGymMember member : members) {

            member.displayInfo();

            if (member instanceof AnnouncementPremiumMember) {

                AnnouncementPremiumMember premium =
                        (AnnouncementPremiumMember) member;

                announcement.append(
                        "Premium | Trainer: "
                                + premium.trainerName
                                + " | Sessions: "
                                + premium.getSessionsAttended()
                );

                announcement.append(
                        " [Trainer via downcast: "
                                + premium.trainerName
                                + "] | "
                );

            } else {

                announcement.append(
                        "Standard | Sessions: "
                                + member.getSessionsAttended()
                                + " | "
                );
            }
        }

        return announcement.toString();
    }


    public static void main(String[] args) {

        AnnouncementGymMember standard =
                new AnnouncementGymMember(
                        "MEM6",
                        1000
                );

        AnnouncementPremiumMember premium =
                new AnnouncementPremiumMember(
                        "MEM7",
                        2000,
                        "Coach Riya"
                );

        AnnouncementGymMember[] members = {
                standard,
                premium
        };

        System.out.println(
                batchPrint(members)
        );
    }
}