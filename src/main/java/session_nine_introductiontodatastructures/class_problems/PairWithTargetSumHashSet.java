package session_nine_introductiontodatastructures.class_problems;

import java.util.HashSet;

public class PairWithTargetSumHashSet {

    static boolean hasPairWithSum(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;

            if (set.contains(complement)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 13;

        System.out.println(hasPairWithSum(nums, target));
    }
}