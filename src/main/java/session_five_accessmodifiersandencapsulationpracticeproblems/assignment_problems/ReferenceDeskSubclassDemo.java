package session_five_accessmodifiersandencapsulationpracticeproblems.assignment_problems;

class ReferenceDeskAccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (accessorContext.equals("SAME_CLASS")) {

            if (fieldModifier.equals("private")
                    || fieldModifier.equals("default")
                    || fieldModifier.equals("protected")
                    || fieldModifier.equals("public")) {

                return "ALLOWED";
            }
        }

        if (accessorContext.equals("SAME_PACKAGE")) {

            if (fieldModifier.equals("default")
                    || fieldModifier.equals("protected")
                    || fieldModifier.equals("public")) {

                return "ALLOWED";
            }
        }

        if (accessorContext.equals("DIFFERENT_PACKAGE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("protected")
                    || fieldModifier.equals("public")) {

                return "ALLOWED";
            }
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result =
                    classifyAccess(
                            modifier,
                            context
                    );

            if (result.equals("DENIED")) {

                return modifier
                        + " via "
                        + context
                        + " (attempt #"
                        + (i + 1)
                        + ")";
            }
        }

        return "None Denied";
    }
}

public class ReferenceDeskSubclassDemo {

    public static void main(String[] args) {

        String[][] attempts = {

                {
                        "public",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                },

                {
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                },

                {
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                }
        };

        System.out.println(
                ReferenceDeskAccessChecker
                        .firstDeniedAttempt(attempts)
        );

        String[][] allowedAttempts = {

                {
                        "public",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                },

                {
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                }
        };

        System.out.println(
                ReferenceDeskAccessChecker
                        .firstDeniedAttempt(
                                allowedAttempts
                        )
        );
    }
}