package _721.UnionFind;

import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind unionFind = new UnionFind(10000);
        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailToID = new HashMap();

        int id = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            String rootEmail = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);

                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                // use first email as the root
                // union all emails for this account
                unionFind.union(emailToID.get(rootEmail), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> res = new HashMap();

        for (String email : emailToName.keySet()) {
            // get root email
            int index = unionFind.find(emailToID.get(email));
            if (!res.containsKey(index)) {
                res.put(index, new ArrayList<>());
            }

            res.get(index).add(email);
        }

        for (List<String> emails : res.values()) {
            Collections.sort(emails);
            emails.add(0, emailToName.get(emails.get(0)));
        }

        return new ArrayList(res.values());
    }
}

class UnionFind {
    int[] parents;
    int[] ranks;

    public UnionFind(int n) {
        parents = new int[n];
        ranks = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public boolean union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);
        // same parent
        if (xParent == yParent) return false;
        // different parent
        if (ranks[xParent] > ranks[yParent]) {
            parents[yParent] = xParent;
        } else if (ranks[xParent] < ranks[yParent]) {
            parents[xParent] = yParent;
        } else {
            parents[yParent] = xParent;
            ranks[xParent]++;
        }

        return true;
    }

    public int find(int node) {
        while (node != parents[node]) {
            int parentNode = parents[parents[node]];
            // update parents array
            parents[node] = parentNode;
            node = parentNode;
        }

        return node;
    }
}
