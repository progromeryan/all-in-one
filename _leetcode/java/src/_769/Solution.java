package _769;


class Solution {
    public int maxChunksToSorted(int[] arr) {
        int res = 0;
        int currMax = 0;
        for (int i = 0; i < arr.length; i++) {
            currMax = Math.max(currMax, arr[i]);
            if (currMax == i) res++;
        }

        return res;
    }
}