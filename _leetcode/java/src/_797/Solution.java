package _797;

import java.util.*;


class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < graph.length; i++) {
            map.put(i, new ArrayList<>());
            for (int nei : graph[i]) {
                map.get(i).add(nei);
            }
        }

        List<Integer> path = new ArrayList<>();
        path.add(0);
        helper(map, 0, graph.length - 1, path);
        return res;
    }

    private void helper(HashMap<Integer, List<Integer>> map, int curr, int target, List<Integer> path) {
        if (!map.containsKey(curr)) return;
        if (curr == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        List<Integer> neis = map.get(curr);
        for (int nei : neis) {
            path.add(nei);
            helper(map, nei, target, path);
            path.remove(path.size() - 1);
        }
    }
}
