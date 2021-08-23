import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Social {
    class Node {
        int index;
        int groupCount;

        public Node(int index, int groupCount) {
            this.index = index;
            this.groupCount = groupCount;
        }
    }

    public void socialGraphs(int[] counts) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> {
            if (a.groupCount != b.groupCount) {
                return a.groupCount - b.groupCount;
            } else {
                return b.index - a.index;
            }
        });

        List<String> res = new ArrayList<>();

        for (int i = 0; i < counts.length; i++) {
            queue.offer(new Node(i, counts[i]));
        }

        while (!queue.isEmpty()) {
            Node first = queue.peek();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < first.groupCount; i++) {
                Node curr = queue.poll();
                sb.insert(0, curr.index + " ");
            }

            res.add(0, sb.toString().trim());
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    public static void main(String[] args) {
        Social social = new Social();
        social.socialGraphs(new int[]{3, 3, 3, 3, 3, 1, 3});
        social.socialGraphs(new int[]{2, 2, 2, 2});
    }
}
