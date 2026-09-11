package session_five_accessmodifiersandencapsulationpracticeproblems.class_problems;

class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {

        name = "";
        confirmed = false;
        otp = "";
    }

    public MovieBookingProfile(String name) {

        this();

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean verifyOtp(String enteredOtp) {
        return otp.equals(enteredOtp);
    }
}

public class MovieBookingProfileDemo {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        MovieBookingProfile profile =
                new MovieBookingProfile(
                        "Rahul Dev"
                );

        System.out.println(
                "Name: " + profile.getName()
        );

        profile.setConfirmed(true);

        System.out.println(
                "Confirmed: "
                        + profile.isConfirmed()
        );

        profile.setOtp("4471");

        System.out.println(
                "OTP correct: "
                        + profile.verifyOtp("4471")
        );

        profile.setName("Rahul Sharma");

        System.out.println(
                "Updated Name: "
                        + profile.getName()
        );
    }
}