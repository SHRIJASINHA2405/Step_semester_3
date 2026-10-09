package session_nine_introductiontodatastructures.assignment_problems;

public class HotWeatherAlertWindows {

    static int countAlerts(int[] readings, int k, int threshold) {
        int sum = 0;
        int count = 0;

        // Calculate the sum of the first k readings
        for (int i = 0; i < k; i++) {
            sum += readings[i];
        }

        // Check the first window
        if (sum >= k * threshold) {
            count++;
        }

        // Slide the window forward
        for (int i = k; i < readings.length; i++) {
            sum = sum + readings[i] - readings[i - k];

            if (sum >= k * threshold) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        System.out.println(countAlerts(readings, k, threshold));
    }
}