package _746.DPArray;

import java.util.*;

import structures.*;

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len == 1) return cost[0];
        if (len == 2) return Math.min(cost[0], cost[1]);

        int[] dp0 = new int[len];
        dp0[0] = cost[0];
        dp0[1] = cost[0] + cost[1];

        int[] dp1 = new int[len];
        dp1[0] = cost[1];
        dp1[1] = cost[1] + cost[2];

        for (int i = 2; i < len; i++) {
            dp0[i] = Math.min(dp0[i - 1], dp0[i - 2]) + cost[i];
            if (i + 1 < len) {
                dp1[i] = Math.min(dp1[i - 1], dp1[i - 2]) + cost[i + 1];
            }
        }

        return Math.min(Math.min(dp0[len - 1], dp0[len-2]), Math.min(dp1[len - 2], dp1[len - 3]));
    }
}