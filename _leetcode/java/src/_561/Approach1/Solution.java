package _561.Approach1;

import java.util.Arrays;

class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int res = 0;
        for (int i = 0; i < nums.length;  i++) {
            if (i % 2 == 0) {
                res += nums[i];
            }
        }

        return res;
    }
}