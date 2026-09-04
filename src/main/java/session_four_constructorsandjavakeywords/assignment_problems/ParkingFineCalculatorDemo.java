package session_four_constructorsandjavakeywords.assigment_problems;

class ParkingTicket {

    String vehicleNo;
    double ratePerMinute;

    public ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    // Cannot be overridden by a subclass
    public final double calculateFine(int overstayMinutes) {
        return overstayMinutes * ratePerMinute;
    }

    // Cannot be overridden by a subclass
    public final void printReceipt(int overstayMinutes) {

        double fine = calculateFine(overstayMinutes);

        System.out.println(
                vehicleNo + " - Fine: Rs " + fine
        );
    }
}

public class ParkingFineCalculatorDemo {

    public static void main(String[] args) {

        String[] vehicleNos = {
                "TN09AB1234",
                "TN22CD5678",
                "TN09EF9012",
                "TN10GH3456"
        };

        double[] ratePerMinute = {
                2,
                2,
                3,
                2
        };

        int[] overstayMinutes = {
                15,
                0,
                -5,
                8
        };

        // Process all tickets
        for (int i = 0; i < vehicleNos.length; i++) {

            ParkingTicket ticket =
                    new ParkingTicket(vehicleNos[i], ratePerMinute[i]);

            if (overstayMinutes[i] > 0) {

                ticket.printReceipt(overstayMinutes[i]);

            } else {

                System.out.println(
                        vehicleNos[i] +
                                " - No fine, within allotted time"
                );
            }
        }
    }
}