package _547.DFS;

public class Solution {
    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        boolean[] visited = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                helper(isConnected, visited, i);
                count++;
            }
        }

        return count;
    }

    private void helper(int[][] matrix, boolean[] visited, int i) {
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                helper(matrix, visited, j);
            }
        }
    }
}
