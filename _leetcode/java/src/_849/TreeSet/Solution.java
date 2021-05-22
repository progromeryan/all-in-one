package _849.TreeSet;

import java.util.*;

import structures.*;

class Solution {
    public int maxDistToClosest(int[] seats) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] != 0) {
                set.add(i);
            }
        }


        int max = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                Integer lower = set.lower(i);
                Integer higher = set.higher(i);

                if (lower == null) {
                    max = Math.max(higher - i, max);
                } else if (higher == null) {
                    max = Math.max(i - lower, max);
                } else {
                    int close = Math.min(higher - i, i - lower);
                    max = Math.max(close, max);
                }
            }
        }

        return max;
    }
}