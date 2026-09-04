package session_four_constructorsandjavakeywords.assigment_problems;

class Participant {

    String name;
    String teamName;
    boolean registered;

    // Constructor for participants who already have a team
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    // Constructor for solo participants
    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printStatus() {
        System.out.println(
                name + " | " +
                        teamName + " | Registered: " +
                        registered
        );
    }
}


public class HackathonRegistrationDemo {

    public static void main(String[] args) {

        String[] names = {
                "Ravi",
                "Meera",
                "Karthik",
                "Divya"
        };

        String[] teamNames = {
                "ByteBusters",
                "",
                "CodeCrafters",
                ""
        };

        // Process all participants in one loop
        for (int i = 0; i < names.length; i++) {

            Participant participant;

            if (teamNames[i].isEmpty()) {
                participant = new Participant(names[i]);
            } else {
                participant = new Participant(names[i], teamNames[i]);
            }

            participant.printStatus();
        }
    }
}