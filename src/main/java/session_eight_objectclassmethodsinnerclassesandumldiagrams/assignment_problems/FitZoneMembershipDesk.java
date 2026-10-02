package session_eight_objectclassmethodsinnerclassesandumldiagrams.assignment_problems;

// ================= MEMBER =================

class GymMember {
    private final String memberId;
    private final String name;

    public GymMember(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
}

// ================= MEMBERSHIP PLAN =================

abstract class MembershipPlan {

    protected static final double BASE_RATE = 1000.00;

    public abstract String getPlanName();

    public abstract int getDurationInMonths();

    public abstract double calculateFee();
}

// ================= MONTHLY PLAN =================

class MonthlyPlan extends MembershipPlan {

    @Override
    public String getPlanName() {
        return "Monthly";
    }

    @Override
    public int getDurationInMonths() {
        return 1;
    }

    @Override
    public double calculateFee() {
        return BASE_RATE;
    }
}

// ================= QUARTERLY PLAN =================

class QuarterlyPlan extends MembershipPlan {

    @Override
    public String getPlanName() {
        return "Quarterly";
    }

    @Override
    public int getDurationInMonths() {
        return 3;
    }

    @Override
    public double calculateFee() {
        return BASE_RATE * 3 * 0.90;
    }
}

// ================= ANNUAL PLAN =================

class AnnualPlan extends MembershipPlan {

    @Override
    public String getPlanName() {
        return "Annual";
    }

    @Override
    public int getDurationInMonths() {
        return 12;
    }

    @Override
    public double calculateFee() {
        return BASE_RATE * 12 * 0.75;
    }
}

// ================= MEMBERSHIP =================

class GymMembership {

    private final GymMember member;
    private final MembershipPlan plan;
    private final double fee;

    private MembershipStatus status;

    public GymMembership(
            GymMember member,
            MembershipPlan plan) {

        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
        this.status = MembershipStatus.ACTIVE;
    }

    public GymMember getMember() {
        return member;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public double getFee() {
        return fee;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    // Status can only be changed through controlled methods.

    public void checkIn() {

        if (status == MembershipStatus.ACTIVE) {
            System.out.println(
                    member.getName()
                            + " checked in successfully."
            );
        } else {
            System.out.println(
                    "Check-in denied: "
                            + member.getName()
                            + "'s membership is "
                            + status + "."
            );
        }
    }

    public void freeze() {

        if (status == MembershipStatus.EXPIRED) {
            System.out.println(
                    "Cannot freeze an Expired membership."
            );
            return;
        }

        if (status == MembershipStatus.FROZEN) {
            System.out.println(
                    member.getName()
                            + "'s membership is already Frozen."
            );
            return;
        }

        status = MembershipStatus.FROZEN;

        System.out.println(
                member.getName()
                        + "'s membership frozen. Status: "
                        + status + "."
        );
    }

    public void unfreeze() {

        if (status == MembershipStatus.EXPIRED) {
            System.out.println(
                    "Cannot unfreeze an Expired membership."
            );
            return;
        }

        if (status == MembershipStatus.ACTIVE) {
            System.out.println(
                    member.getName()
                            + "'s membership is already Active."
            );
            return;
        }

        status = MembershipStatus.ACTIVE;

        System.out.println(
                member.getName()
                        + "'s membership unfrozen. Status: "
                        + status + "."
        );
    }

    public void expire() {

        if (status == MembershipStatus.EXPIRED) {
            System.out.println(
                    member.getName()
                            + "'s membership is already Expired."
            );
            return;
        }

        status = MembershipStatus.EXPIRED;

        System.out.println(
                member.getName()
                        + "'s membership expired. Status: "
                        + status + "."
        );
    }
}

// ================= STATUS =================

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

// ================= MEMBERSHIP DESK =================

class MembershipDesk {

    public GymMembership buyMembership(
            GymMember member,
            MembershipPlan plan) {

        GymMembership membership =
                new GymMembership(member, plan);

        System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
                plan.getPlanName(),
                member.getName(),
                membership.getFee(),
                membership.getStatus()
        );

        return membership;
    }
}

// ================= MAIN CLASS =================

public class FitZoneMembershipDesk {

    public static void main(String[] args) {

        MembershipDesk desk = new MembershipDesk();

        // Members
        GymMember asha =
                new GymMember("M001", "Asha");

        GymMember ravi =
                new GymMember("M002", "Ravi");

        // Asha buys Quarterly membership
        MembershipPlan quarterlyPlan =
                new QuarterlyPlan();

        GymMembership ashaMembership =
                desk.buyMembership(
                        asha,
                        quarterlyPlan
                );

        System.out.println();

        // Ravi buys Monthly membership
        MembershipPlan monthlyPlan =
                new MonthlyPlan();

        GymMembership raviMembership =
                desk.buyMembership(
                        ravi,
                        monthlyPlan
                );

        System.out.println();

        // Asha checks in
        ashaMembership.checkIn();

        System.out.println();

        // Asha freezes membership
        ashaMembership.freeze();

        System.out.println();

        // Asha attempts to check in while frozen
        ashaMembership.checkIn();

        System.out.println();

        // Ravi's membership expires
        raviMembership.expire();

        System.out.println();

        // Ravi attempts to freeze expired membership
        raviMembership.freeze();
    }
}