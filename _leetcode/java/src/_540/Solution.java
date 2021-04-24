package _540;

public class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if (num != nums[mid - 1] && num != nums[mid + 1]) return num;

            if (mid % 2 == 0) {
                if (num == nums[mid - 1]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                if (num == nums[mid - 1]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }

        if (left == 0) return nums[0];
        return nums[nums.length - 1];
    }
}
