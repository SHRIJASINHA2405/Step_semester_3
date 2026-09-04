package session_two_stringoperationsandperformance.assignment_programs;
import java.util.Scanner;

public class ProductInventoryCSVParserDemo {

    // Method to parse and display inventory record
    public static void parseInventoryRecord(String csvLine) {

        // Split the CSV line using comma
        String[] fields = csvLine.split(",");

        // Validate that exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        // Extract individual fields
        String productName = fields[0];
        String sku = fields[1];
        String quantity = fields[2];

        // Print formatted record
        System.out.println(
                "Product: " + productName +
                        " | SKU: " + sku +
                        " | Qty: " + quantity
        );
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print(
                "Enter inventory record (ProductName,SKU,Quantity): "
        );

        String csvLine = scanner.nextLine();

        parseInventoryRecord(csvLine);

        scanner.close();
    }
}