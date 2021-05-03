package _621;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] dict = new int[26];
        for (char task : tasks) {
            dict[task - 'A']++;
        }

        int max = 0;
        int maxSame = 0;

        for (int num : dict) {
            if (num != 0) max = Math.max(max, num);
        }

        for (int num : dict) {
            if (num == max) maxSame++;
        }

        int res = (max - 1) * (n + 1) + maxSame;
        return Math.max(res, tasks.length);
    }
}