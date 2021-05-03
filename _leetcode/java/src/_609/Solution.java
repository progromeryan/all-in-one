package _609;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] splits = path.split(" ");
            for (int i = 1; i < splits.length; i++) {
                String[] nameContent = splits[i].split("\\(");
                nameContent[1] = nameContent[1].replace(")", "");
                List<String> list = map.getOrDefault(nameContent[1], new ArrayList<>());
                list.add(splits[0] + "/" + nameContent[0]);
                map.put(nameContent[1], list);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1)
                res.add(map.get(key));
        }

        return res;
    }
}