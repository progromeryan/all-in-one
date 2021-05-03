package _581.Array;

public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;

        int end = -2;
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);

            if (max > nums[i]) {
                end = i;
            }
        }

        int start = -1;
        int min = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            min = Math.min(min, nums[i]);

            if (min < nums[i]) {
                start = i;
            }
        }


        return end - start + 1;
    }
}