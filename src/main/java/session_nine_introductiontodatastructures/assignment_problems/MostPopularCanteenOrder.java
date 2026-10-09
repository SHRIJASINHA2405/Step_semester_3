package session_nine_introductiontodatastructures.assignment_problems;
import java.util.*;

public class MostPopularCanteenOrder {

    static void mostPopular(List<String> orders) {
        HashMap<String, Integer> countMap = new HashMap<>();

        // Count each item
        for (String item : orders) {
            countMap.put(item, countMap.getOrDefault(item, 0) + 1);
        }

        // Find the item with the highest count
        String popularItem = orders.get(0);
        int maxCount = countMap.get(popularItem);

        for (String item : orders) {
            int count = countMap.get(item);

            if (count > maxCount) {
                popularItem = item;
                maxCount = count;
            }
        }

        System.out.println("(\"" + popularItem + "\", " + maxCount + ")");
    }

    public static void main(String[] args) {
        List<String> orders = Arrays.asList(
                "dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"
        );

        mostPopular(orders);
    }
}