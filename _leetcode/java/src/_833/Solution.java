package _833;

import java.util.*;

import structures.*;

class Solution {
    public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
        HashMap<Integer, String[]> map = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            map.put(index, new String[]{sources[i], targets[i]});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(i)) {
                String source = map.get(i)[0];
                String target = map.get(i)[1];

                if (s.substring(i, i + source.length()).equals(source)) {
                    sb.append(target);
                    i += source.length() - 1;
                } else {
                    sb.append(s.charAt(i));
                }
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}