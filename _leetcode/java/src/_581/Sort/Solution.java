package _581.Sort;

import java.util.Arrays;

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] sorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sorted[i] = nums[i];
        }
        Arrays.sort(sorted);

        int start = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != sorted[i] && start == -1) {
                start = i;
            } else if (nums[i] != sorted[i]) {
                end = i;
            }
        }

        return start == -1 ? 0 : end - start + 1;
    }
}