package session_one_javastring.class_problems;
import java.util.Random;

public class BMICalculatorDemo {

    // Method to determine BMI status
    public static String getBmiStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }


    // Method to calculate BMI and print the complete report
    public static void printWellnessReport(
            double[] heights,
            double[] weights
    ) {

        System.out.println("\n========== WELLNESS REPORT ==========");

        System.out.printf(
                "%-10s %-15s %-15s %-10s %-15s%n",
                "Person",
                "Height (m)",
                "Weight (kg)",
                "BMI",
                "Status"
        );

        System.out.println(
                "---------------------------------------------------------------"
        );

        // Loop through both parallel arrays
        for (int i = 0; i < heights.length; i++) {

            double height = heights[i];
            double weight = weights[i];

            // BMI = weight / (height × height)
            double bmi = weight / (height * height);

            // Get BMI category
            String status = getBmiStatus(bmi);

            // Print one person's details
            System.out.printf(
                    "%-10d %-15.2f %-15.2f %-10.2f %-15s%n",
                    i + 1,
                    height,
                    weight,
                    bmi,
                    status
            );
        }
    }


    public static void main(String[] args) {

        int numberOfPeople = 10;

        // Parallel arrays
        double[] heights = new double[numberOfPeople];
        double[] weights = new double[numberOfPeople];

        Random random = new Random();

        // Generate random height and weight values
        for (int i = 0; i < numberOfPeople; i++) {

            // Random height between approximately 1.50 and 1.90 meters
            heights[i] = 1.50 + random.nextDouble() * 0.40;

            // Random weight between approximately 45 and 100 kg
            weights[i] = 45 + random.nextDouble() * 55;
        }

        // Print complete wellness report
        printWellnessReport(heights, weights);
    }
}