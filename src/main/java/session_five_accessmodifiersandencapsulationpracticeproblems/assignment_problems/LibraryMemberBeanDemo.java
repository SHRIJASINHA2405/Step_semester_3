package session_five_accessmodifiersandencapsulationpracticeproblems.assignment_problems;

class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMember() {

        membershipId = null;
        name = "";
        premiumMember = false;
        securityAnswerHash = null;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {

        securityAnswerHash =
                oneWayTransform(answer);
    }

    private String oneWayTransform(String answer) {

        int hash = 7;

        for (int i = 0; i < answer.length(); i++) {

            hash = 31 * hash
                    + answer.charAt(i);
        }

        return Integer.toHexString(hash);
    }
}

public class LibraryMemberBeanDemo {

    public static void main(String[] args) {

        LibraryMember member =
                new LibraryMember();

        member.setMembershipId(
                "LIB-8841"
        );

        member.setName(
                "Priya Nair"
        );

        member.setPremiumMember(
                true
        );

        member.setSecurityAnswer(
                "BlueMountain"
        );

        System.out.println(
                "Membership ID: "
                        + member.getMembershipId()
        );

        System.out.println(
                "Name: "
                        + member.getName()
        );

        System.out.println(
                "Premium: "
                        + member.isPremiumMember()
        );

        member.setMembershipId(
                "FAKE-0000"
        );

        System.out.println(
                "After second ID: "
                        + member.getMembershipId()
        );

        System.out.println(
                "Security answer stored securely; "
                        + "no getter exists."
        );
    }
}