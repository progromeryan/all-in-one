package _531;

public class Solution {
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = picture[i][j];
                if (c == 'B') {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    if (rows[i] == 1 && cols[j] == 1) res++;
                }
            }
        }

        return res;
    }
}
