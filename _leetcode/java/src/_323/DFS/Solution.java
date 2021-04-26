package _323.DFS;

class Solution {
    public int countComponents(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                helper(edges, visited, i);
                count++;
            }
        }
        return count;
    }

    private void helper(int[][] edges, boolean[] visited, int node) {
        if (visited[node]) return;
        visited[node] = true;
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (x == node && !visited[y]) {
                helper(edges, visited, y);
            }

            if (y == node && !visited[x]) {
                helper(edges, visited, x);
            }
        }
    }
}