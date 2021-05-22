package _849.Array;

/**
 * 需要先找出最大的连续空位长度
 * 若连续空位靠着墙了
 * 那么就直接挨着墙坐
 * 若两边都有人
 * 那么就坐到空位的中间位置 (除2)
 */
class Solution {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int prev = -1;
        int ans = 0;

        for (int i = 0; i <= n; ++i) {
            // 靠墙位置
            if (i == n) {
                ans = Math.max(ans, n - prev - 1);
            } else if (seats[i] != 0) {
                if (prev == -1) {
                    ans = i;
                } else {
                    ans = Math.max(ans, (i - prev) / 2);
                }
                prev = i;
            }
        }
        return ans;
    }
}