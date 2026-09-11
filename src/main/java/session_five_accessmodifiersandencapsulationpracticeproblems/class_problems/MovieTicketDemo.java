package session_five_accessmodifiersandencapsulationpracticeproblems.class_problems;

class MovieTicketAccess {

    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicketAccess(String seatNumber,
                             String screenId,
                             double ticketPrice,
                             String movieTitle) {

        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}

public class MovieTicketDemo {

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

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result = classifyAccess(
                    attempt[0],
                    attempt[1]
            );

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed
                + " | Denied: " + denied;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        MovieTicketAccess ticket =
                new MovieTicketAccess(
                        "A1",
                        "SCREEN-1",
                        250.0,
                        "Avengers"
                );

        System.out.println("Movie: " + ticket.movieTitle);
        System.out.println("Screen: " + ticket.screenId);
        System.out.println("Seat: " + ticket.getSeatNumber());
        System.out.println("Price: " + ticket.getTicketPrice());

        System.out.println();

        System.out.println(
                classifyAccess(
                        "private",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                classifyAccess(
                        "protected",
                        "DIFFERENT_PACKAGE"
                )
        );

        String[][] attempts = {
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                summarizeBatch(attempts)
        );
    }
}