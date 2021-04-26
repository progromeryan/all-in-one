package _547;

public class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] graph = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    graph[i]++;
                    graph[j]++;
                }
            }
        }

        int group = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    graph[i]--;
                    graph[j]--;
                    if (graph[i] == 0 || graph[j] == 0) {
                        group--;
                    }
                }
            }
        }

        return group;
    }
}
