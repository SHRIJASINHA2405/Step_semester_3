package session_nine_introductiontodatastructures.class_problems;
public class MaxContainerAreaBruteForce {

    static int maxContainerArea(int[] heights) {
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {

                int height = Math.min(heights[i], heights[j]);
                int width = j - i;
                int area = height * width;

                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(maxContainerArea(heights));
    }
}