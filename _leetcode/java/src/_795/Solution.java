package _795;


class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int dp = 0;
        int prev = -1;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                dp = i - prev;
            }

            if (nums[i] > right) {
                dp = 0;
                prev = i;
            }

            if (nums[i] < left && i > 0) {
                dp = dp;
            }


            res += dp;
        }

        return res;
    }
}