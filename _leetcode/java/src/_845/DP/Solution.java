package _845.DP;

class Solution {
    public int longestMountain(int[] arr) {
        int[] up = new int[arr.length];
        int[] down = new int[arr.length];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                up[i] = up[i - 1] + 1;
            }
        }

        int res = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                down[i] = down[i + 1] + 1;
            }

            if (up[i] != 0 && down[i] != 0) {
                res = Math.max(res, up[i] + down[i] + 1);
            }
        }

        return res;
    }
}
