package _721.DFS;

import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();

        for (List<String> account : accounts) {
            String name = account.get(0);
            String rootEmail = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);

                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(rootEmail);

                graph.computeIfAbsent(rootEmail, x -> new ArrayList<>()).add(email);

                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();

        for (String email : graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);

                Queue<String> queue = new LinkedList<>();
                queue.offer(email);

                List<String> emails = new ArrayList();
                while (!queue.isEmpty()) {
                    String node = queue.poll();
                    emails.add(node);
                    for (String nei : graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
                Collections.sort(emails);
                emails.add(0, emailToName.get(email));
                ans.add(emails);
            }
        }
        return ans;
    }
}