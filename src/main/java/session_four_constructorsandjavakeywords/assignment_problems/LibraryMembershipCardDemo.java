package session_four_constructorsandjavakeywords.assigment_problems;

class MembershipCard {

    // Static fields shared by all objects
    static String libraryName;
    static String validUntil;

    // Instance field
    String studentName;

    // Runs only once when the class is loaded
    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println("Library info loaded");
    }

    // Constructor
    public MembershipCard(String studentName) {
        this.studentName = studentName;
    }

    public void printConfirmation() {
        System.out.println(
                "Membership card issued: " + studentName
        );
    }
}

public class LibraryMembershipCardDemo {

    public static void main(String[] args) {

        String[] names = {
                "Ananya",
                "Rohan",
                "Priya",
                "Arjun",
                "Sneha"
        };

        // Create membership cards in a loop
        for (String name : names) {

            MembershipCard card = new MembershipCard(name);

            card.printConfirmation();
        }
    }
}