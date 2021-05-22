package _849.TwoPointer2;

class Solution {
    public int maxDistToClosest(int[] seats) {
        int count = 0;
        int ans = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                count = 0;
            } else {
                count++;
                ans = Math.max(ans, (count + 1) / 2);
            }
        }

        // 靠墙坐
        for (int i = 0; i < seats.length; i++)
            if (seats[i] == 1) {
                ans = Math.max(ans, i);
                break;
            }

        // 靠墙坐
        for (int i = seats.length - 1; i >= 0; i--)
            if (seats[i] == 1) {
                ans = Math.max(ans, seats.length - 1 - i);
                break;
            }

        return ans;
    }
}