package _416;

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        sum /= 2;

        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : nums) {
            // 从后往前遍历
            for (int currSum = sum; currSum > 0; currSum--) {
                if (currSum - num >= 0) {
                    // 如果当前和 - 当前数字 = true
                    // 表示用之前的数字可以组成这个（当前和 - 当前数字）
                    // 那么在加上当前数字，就可以组成当前和了
                    // 因此当前dp设置为true
                    dp[currSum] = dp[currSum] || dp[currSum - num];
                }
            }
        }

        return dp[sum];
    }
}