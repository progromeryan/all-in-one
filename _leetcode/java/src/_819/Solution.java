package _819;

import java.util.*;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> bannedWords = new HashSet();
        for (String word : banned) {
            bannedWords.add(word);
        }

        String ans = "";
        int maxCount = 0;
        Map<String, Integer> wordCount = new HashMap();

        StringBuilder sb = new StringBuilder();

        char[] chars = paragraph.toCharArray();

        for (int i = 0; i < chars.length; ++i) {
            char currChar = chars[i];
            // 使用isLetter判断
            if (Character.isLetter(currChar)) {
                sb.append(Character.toLowerCase(currChar));
                if (i != chars.length - 1) {
                    continue;
                }
            }

            if (sb.length() > 0) {
                String word = sb.toString();

                if (!bannedWords.contains(word)) {
                    int newCount = wordCount.getOrDefault(word, 0) + 1;
                    wordCount.put(word, newCount);
                    if (newCount > maxCount) {
                        ans = word;
                        maxCount = newCount;
                    }
                }

                sb = new StringBuilder();
            }
        }

        return ans;
    }
}