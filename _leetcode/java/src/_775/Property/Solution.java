package _775.Property;

import java.util.*;

import structures.*;

/**
 * 1. global >= local
 * 2. if abs(nums[i] - i) > 1, must be a global inv that is not a local inv
 */
class Solution {
    public boolean isIdealPermutation(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - i) > 1) return false;
        }

        return true;
    }
}