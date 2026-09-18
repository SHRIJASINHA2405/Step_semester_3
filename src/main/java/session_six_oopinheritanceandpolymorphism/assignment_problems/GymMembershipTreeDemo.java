package session_six_oopinheritanceandpolymorphism.assignment_problems;
class TreeGymMember {

    String memberId;
    int monthlyFee;
    int sessionsAttended;

    TreeGymMember(
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
                "Standard Member | Sessions: "
                        + sessionsAttended
        );
    }
}


class TreePremiumMember
        extends TreeGymMember {

    String trainerName;

    TreePremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Premium Member | Trainer: "
                        + trainerName
                        + " | Sessions: "
                        + sessionsAttended
        );
    }
}


class EliteTreeMember
        extends TreePremiumMember {

    String lockerNumber;

    EliteTreeMember(
            String memberId,
            int monthlyFee,
            String trainerName,
            String lockerNumber) {

        super(
                memberId,
                monthlyFee,
                trainerName
        );

        this.lockerNumber = lockerNumber;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Elite Member | Trainer: "
                        + trainerName
                        + " | Locker: "
                        + lockerNumber
                        + " | Sessions: "
                        + sessionsAttended
        );
    }
}


class GroupTreeMember
        extends TreeGymMember {

    String className;

    GroupTreeMember(
            String memberId,
            int monthlyFee,
            String className) {

        super(memberId, monthlyFee);

        this.className = className;
    }

    @Override
    void displayInfo() {

        System.out.println(
                "Group Class Member | Class: "
                        + className
                        + " | Sessions: "
                        + sessionsAttended
        );
    }
}


public class GymMembershipTreeDemo {

    static String classifyGeneration(
            TreeGymMember member) {

        if (member instanceof EliteTreeMember) {

            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupTreeMember) {

            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof TreePremiumMember) {

            return "Premium Member";
        }

        return "Standard Member";
    }


    static int getTotalSessionsAttended(
            TreeGymMember[] members) {

        int total = 0;

        for (TreeGymMember member : members) {

            total += member.getSessionsAttended();
        }

        return total;
    }


    public static void main(String[] args) {

        TreeGymMember standard =
                new TreeGymMember(
                        "MEM1",
                        1000
                );

        TreePremiumMember premium =
                new TreePremiumMember(
                        "MEM2",
                        2000,
                        "Coach Riya"
                );

        EliteTreeMember elite =
                new EliteTreeMember(
                        "MEM3",
                        3000,
                        "Coach Arjun",
                        "L12"
                );

        GroupTreeMember group =
                new GroupTreeMember(
                        "MEM4",
                        1500,
                        "Zumba"
                );

        premium.attendSession();
        premium.attendSession();
        premium.attendSession();

        elite.attendSession();
        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();

        standard.displayInfo();
        premium.displayInfo();
        elite.displayInfo();
        group.displayInfo();

        System.out.println(
                classifyGeneration(elite)
        );

        System.out.println(
                classifyGeneration(group)
        );

        TreeGymMember[] members = {
                premium,
                elite,
                group
        };

        System.out.println(
                getTotalSessionsAttended(members)
        );
    }
}