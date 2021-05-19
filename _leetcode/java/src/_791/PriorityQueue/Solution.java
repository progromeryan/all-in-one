package _791.PriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;


class Solution {
    class LetterOrder {
        String letter;
        int order;

        LetterOrder(String letter, int order) {
            this.letter = letter;
            this.order = order;
        }
    }

    public String customSortString(String order, String str) {
        int[] dict = new int[26];
        Arrays.fill(dict, Integer.MAX_VALUE);
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            dict[c - 'a'] = i;
        }

        PriorityQueue<LetterOrder> queue = new PriorityQueue<>((a, b) -> {
            return a.order - b.order;
        });

        for (char c : str.toCharArray()) {
            LetterOrder letterOrder = new LetterOrder(String.valueOf(c), dict[c - 'a']);
            queue.offer(letterOrder);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll().letter);
        }

        return sb.toString();
    }
}