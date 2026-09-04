package session_three_oopintroclassesandobjects.class_problems;
public class PlacementRecordDemo {

    public static void main(String[] args) {

        // Create PlacementRecord objects
        PlacementRecord student1 =
                new PlacementRecord("Ravi", "TCS", 4.5);

        PlacementRecord student2 =
                new PlacementRecord("Anitha", "Zoho", 6.2);

        PlacementRecord student3 =
                new PlacementRecord("Karthik", "Infosys", 4.0);


        // Store objects in an array
        PlacementRecord[] placements = {
                student1,
                student2,
                student3
        };


        // Print every placement record
        for (int i = 0; i < placements.length; i++) {
            placements[i].printRecord();
        }
    }
}


// PlacementRecord class
class PlacementRecord {

    // Fields
    String studentName;
    String company;
    double packageLpa;


    // Constructor
    PlacementRecord(String studentName,
                    String company,
                    double packageLpa) {

        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }


    // Instance method
    void printRecord() {

        System.out.println(
                studentName + " -> " +
                        company + " @ " +
                        packageLpa + " LPA"
        );
    }
}