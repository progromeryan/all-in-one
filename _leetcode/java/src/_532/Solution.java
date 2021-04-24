package _532;

import java.util.HashMap;

public class Solution {
    public int findPairs(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int res = 0;
        for (int key : map.keySet()) {
            int num1 = key - k;
            int num2 = key + k;
            if (k == 0) {
                if (map.get(key) >= 2) {
                    res += 2;
                }
            } else {
                if (map.containsKey(num1)) res++;
                if (map.containsKey(num2)) res++;
            }
        }

        return res / 2;
    }
}
