package session_five_accessmodifiersandencapsulationpracticeproblems.assignment_problems;

class LibraryMemberAccess {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMemberAccess(String membershipPin,
                               String branchCode,
                               double finesOwed,
                               String displayName) {

        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipPin() {
        return membershipPin;
    }
}

class AccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        switch (fieldModifier) {

            case "private":
                if (accessorContext.equals("SAME_CLASS")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "protected":
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            String result =
                    classifyAccess(modifier, context);

            boolean allowed =
                    result.equals("ALLOWED");

            switch (modifier) {

                case "private":
                    if (allowed) {
                        privateAllowed++;
                    } else {
                        privateDenied++;
                    }
                    break;

                case "default":
                    if (allowed) {
                        defaultAllowed++;
                    } else {
                        defaultDenied++;
                    }
                    break;

                case "protected":
                    if (allowed) {
                        protectedAllowed++;
                    } else {
                        protectedDenied++;
                    }
                    break;

                case "public":
                    if (allowed) {
                        publicAllowed++;
                    } else {
                        publicDenied++;
                    }
                    break;
            }
        }

        return "private: "
                + privateAllowed + " allowed / "
                + privateDenied + " denied | default: "
                + defaultAllowed + " allowed / "
                + defaultDenied + " denied | protected: "
                + protectedAllowed + " allowed / "
                + protectedDenied + " denied | public: "
                + publicAllowed + " allowed / "
                + publicDenied + " denied";
    }
}

public class MembershipFieldReachDemo {

    public static void main(String[] args) {

        LibraryMemberAccess member =
                new LibraryMemberAccess(
                        "PIN-1234",
                        "BR-01",
                        25.50,
                        "Priya Nair"
                );

        System.out.println(
                "Member: " + member.displayName
        );

        System.out.println(
                "Branch: " + member.branchCode
        );

        System.out.println(
                "Fines: " + member.finesOwed
        );

        System.out.println(
                "PIN: " + member.getMembershipPin()
        );

        System.out.println();

        System.out.println(
                AccessChecker.classifyAccess(
                        "private",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                AccessChecker.classifyAccess(
                        "protected",
                        "DIFFERENT_PACKAGE"
                )
        );

        String[][] attempts = {

                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println();

        System.out.println(
                AccessChecker.summarizeByModifier(
                        attempts
                )
        );
    }
}