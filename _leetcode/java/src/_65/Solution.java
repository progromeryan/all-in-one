package _65;

import java.util.*;

import structures.*;

class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        if (sum % 3 != 0) return false;

        int target = sum / 3;

        int curr = 0;
        int count = 0;
        for (int num : arr) {
            curr += num;
            if (curr == target) {
                curr = 0;
                count++;
            }
        }

        return count >= 3;
    }
}

