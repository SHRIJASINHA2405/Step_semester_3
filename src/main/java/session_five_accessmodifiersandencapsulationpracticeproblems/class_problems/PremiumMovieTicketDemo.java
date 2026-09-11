package session_five_accessmodifiersandencapsulationpracticeproblems.class_problems;
class PremiumTicketBase {

    private double ticketPrice;
    String screenId;
    protected double price;
    public String movieTitle;

    public PremiumTicketBase(String movieTitle,
                             String screenId,
                             double ticketPrice) {

        this.movieTitle = movieTitle;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.price = ticketPrice;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}

class PremiumMovieTicket extends PremiumTicketBase {

    public PremiumMovieTicket(String movieTitle,
                              String screenId,
                              double ticketPrice) {

        super(movieTitle, screenId, ticketPrice);
    }

    public void accessProtectedField() {
        price = 500.0;
    }

    public double getPremiumPrice() {
        return price;
    }
}

public class PremiumMovieTicketDemo {

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

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        PremiumMovieTicket ticket =
                new PremiumMovieTicket(
                        "Avengers",
                        "SCREEN-2",
                        400.0
                );

        System.out.println("Movie: " + ticket.movieTitle);
        System.out.println("Screen: " + ticket.screenId);

        System.out.println(
                "Original Price: "
                        + ticket.getTicketPrice()
        );

        ticket.accessProtectedField();

        System.out.println(
                "Premium Price: "
                        + ticket.getPremiumPrice()
        );

        System.out.println();

        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );
    }
}