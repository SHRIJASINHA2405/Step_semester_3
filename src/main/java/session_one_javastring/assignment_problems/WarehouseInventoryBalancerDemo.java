package session_one_javastring.assignment_problems;
public class WarehouseInventoryBalancerDemo {

    // Method to analyze inventory
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {

        int totalA = 0;
        int totalB = 0;

        // Assume the first item is the highest initially
        int highestQuantity = sectionA[0];
        String highestSection = "Section A";
        int highestIndex = 0;

        // Calculate Section A total and find highest quantity
        for (int i = 0; i < sectionA.length; i++) {

            totalA += sectionA[i];

            if (sectionA[i] > highestQuantity) {
                highestQuantity = sectionA[i];
                highestSection = "Section A";
                highestIndex = i;
            }
        }

        // Calculate Section B total and find highest quantity
        for (int i = 0; i < sectionB.length; i++) {

            totalB += sectionB[i];

            if (sectionB[i] > highestQuantity) {
                highestQuantity = sectionB[i];
                highestSection = "Section B";
                highestIndex = i;
            }
        }

        // Check whether both sections are balanced
        String status;

        if (totalA == totalB) {
            status = "Balanced";
        } else {
            status = "Not Balanced";
        }

        // Print the result
        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);
        System.out.println("Status: " + status);

        System.out.println(
                "Highest Quantity: " + highestQuantity +
                        " (" + highestSection +
                        ", Item " + (highestIndex + 1) + ")"
        );
    }


    public static void main(String[] args) {

        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};

        analyzeInventory(sectionA, sectionB);
    }
}