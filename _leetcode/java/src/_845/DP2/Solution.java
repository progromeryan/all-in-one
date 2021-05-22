package _845.DP2;

/**
 * 2 1 4 7 3 2 5
 * 0 0 1 2 2 2 1 up
 * 0 1 0 0 1 2 0 down
 */

class Solution {
    public int longestMountain(int[] arr) {
        int res = 0, up = 0, down = 0, n = arr.length;

        for (int i = 1; i < n; i++) {
            // 从递减变成递增,重新开始计数
            if ((down != 0 && arr[i] > arr[i - 1]) || (arr[i - 1] == arr[i])) {
                up = down = 0;
            }

            // 递增数列
            if (arr[i] > arr[i - 1]) {
                up++;
            }
            // 递减数列
            if (arr[i] < arr[i - 1]) {
                down++;
            }

            if (up > 0 && down > 0) {
                res = Math.max(res, up + down + 1);
            }
        }

        return res;
    }
}
