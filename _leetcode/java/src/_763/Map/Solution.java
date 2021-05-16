package _763.Map;

import java.util.*;

import structures.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Integer, String> acc = new HashMap<>();
        int[] visit = new int[26];

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
            visit[s.charAt(i) - 'a']++;
            acc.put(i, helper(visit));
        }

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((a, b) -> {
            return a.getValue() - b.getValue();
        });

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }


        int[] lastAcc = new int[26];
        List<Integer> res = new ArrayList<>();
        int start = 0;
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            char c = entry.getKey();
            lastAcc[c - 'a']++;
            int lastIndex = entry.getValue();
            String accStr = acc.get(lastIndex);
            String lastAccStr = helper(lastAcc);

            if (accStr.equals(lastAccStr)) {
                res.add(lastIndex - start + 1);
                start = lastIndex + 1;
            }
        }

        return res;
    }

    private String helper(int[] visit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visit.length; i++) {
            if (visit[i] != 0) {
                sb.append('a' + i);
            }
        }

        return sb.toString();
    }
}