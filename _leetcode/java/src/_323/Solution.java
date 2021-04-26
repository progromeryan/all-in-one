package _323;

import structures.UnionFind;

public class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }

        return unionFind.getGroupNum();
    }
}
