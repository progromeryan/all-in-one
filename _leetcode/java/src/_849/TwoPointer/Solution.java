package _849.TwoPointer;

/**
 * 把0都放在一起考虑
 * 需要考虑特殊情况: 0是空位;最后一个位置是空位
 */
class Solution {
    public int maxDistToClosest(int[] seats) {
        int index0 = -1;
        int max = 0;
        for (int i = 0; i <= seats.length; i++) {
            if (i == seats.length) {
                if (index0 != -1) {
                    max = Math.max(max, i - index0);
                }
                break;
            }

            if (seats[i] != 0) {
                if (index0 != -1) {
                    int dist = (i - index0 + 1) / 2;
                    // 0是空位,可以靠墙坐
                    if (index0 == 0) {
                        dist = i;
                    }

                    max = Math.max(max, dist);
                    index0 = -1;
                }
            } else {
                if (index0 == -1) {
                    index0 = i;
                }
            }
        }

        return max;
    }
}