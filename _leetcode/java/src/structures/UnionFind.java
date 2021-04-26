package structures;

public class UnionFind {
    int[] parents;
    int[] ranks;
    int groupNum = 0;

    // one dimension
    public UnionFind(int n) {
        parents = new int[n];
        ranks = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        groupNum = n;
    }

    // two dimensions
    public UnionFind(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        parents = new int[m * n];
        ranks = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    parents[i * n + j] = i * n + j;
                    groupNum++;
                }

                ranks[i * n + j] = 1;
            }
        }
    }

    public boolean union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);
        // same parent
        if (xParent == yParent) return false;
        // different parent
        groupNum--;
        if (ranks[xParent] > ranks[yParent]) {
            parents[yParent] = xParent;
        } else if (ranks[xParent] < ranks[yParent]) {
            parents[xParent] = yParent;
        } else {
            parents[yParent] = xParent;
            ranks[xParent]++;
        }

        return true;
    }

    public int find(int node) {
        while (node != parents[node]) {
            int parentNode = parents[parents[node]];

            parents[node] = parentNode;
            node = parentNode;
        }

        return node;
    }

    public int getGroupNum() {
        return groupNum;
    }
}
