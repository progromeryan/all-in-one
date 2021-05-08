package _665;

class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 1;

        for (int i = 1; i < nums.length; ++i) {
            // 需要进行修改了
            if (nums[i] < nums[i - 1]) {
                // 已经修改了一次
                if (count == 0) return false;
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
                count--;
            }
        }
        return true;
    }
}