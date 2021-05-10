package _692;

import java.util.*;


class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> {
            int freqA = a.getValue();
            int freqB = b.getValue();
            if (freqA != freqB) {
                return freqB - freqA;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        });


        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }

        List<String> res = new ArrayList<>();
        while (k != 0 && !queue.isEmpty()) {
            res.add(queue.poll().getKey());
            k--;
        }
        return res;
    }
}