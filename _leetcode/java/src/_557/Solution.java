package _557;

public class Solution {
    public String reverseWords(String s) {
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();

        for (String word : words) {
            res.append(new StringBuffer(word).reverse() + " ");
        }

        return res.toString().trim();
    }
}
