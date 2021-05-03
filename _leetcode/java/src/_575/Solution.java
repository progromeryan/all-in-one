package _575;

import java.util.HashSet;

class Solution {
    public int distributeCandies(int[] candyType) {
        HashSet<Integer> set = new HashSet<>();

        for (int can : candyType){
            set.add(can);
        }
        return Math.min(candyType.length / 2, set.size());
    }
}