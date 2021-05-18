package _785.DFS;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0) {
                // 相邻的点染成不同的颜色，这样就可以把他们分到不同的组了
                // 1和-1表示两种颜色,0表示还没有染色
                if (!dfs(graph, colors, 1, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int color, int curr) {
        colors[curr] = color;
        for (int next : graph[curr]) {
            // 相邻的点具有相同的颜色
            if (colors[next] == color) return false;
            // 相邻的点还没有染色,但是不能染成相反的颜色
            if (colors[next] == 0 && !dfs(graph, colors, -color, next)) {
                return false;
            }
        }

        return true;
    }
}