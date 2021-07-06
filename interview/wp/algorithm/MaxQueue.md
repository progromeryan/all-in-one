> 设计并实现一个 MaxQueue 的 poll(), push(int a), pollMax()三个操作
> poll() 移除最先进入队列的元素并返回它的值
> pollMax() 移除队列中值最大的元素并返回它的值
> push(int a) 将 a 放入队尾
> 每个操作的 average time complexity O(logn)

类似 716 Max stack

```java
class MaxQueue {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxQueue() {
        map = new TreeMap();
        dll = new DoubleLinkedList();
    }

    public void offer(int x) {
        Node node = dll.add(x);
        // 新节点
        if (!map.containsKey(x)) {
            map.put(x, new ArrayList<>());
        }
        // 有可能同一个值多次加入
        map.get(x).add(node);
    }

    public int poll() {
        int val = dll.poll();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) {
            map.remove(val);
        }
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int pollMax() {
        // 当前最大值
        int max = peekMax();
        // 得到这个值的所有node
        List<Node> list = map.get(max);
        // 删除最后加入的
        Node node = list.remove(0);
        dll.remove(node);
        if (list.isEmpty()) {
            map.remove(max);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxQueue maxQ = new MaxQueue();
        maxQ.offer(5);
        maxQ.offer(3);
        maxQ.offer(4);
        maxQ.offer(9);
        maxQ.offer(25);
        maxQ.offer(1);
        maxQ.offer(0);
        System.out.println(maxQ.pollMax());
        System.out.println(maxQ.poll());
        System.out.println(maxQ.poll());
        maxQ.offer(25);
        maxQ.offer(25);
        System.out.println(maxQ.pollMax());
        System.out.println(maxQ.poll());
        System.out.println(maxQ.poll());
        System.out.println(maxQ.poll());
    }
}

class DoubleLinkedList {
    Node head;
    Node tail;

    public DoubleLinkedList() {
        // dummy head and tail
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node node = new Node(val);
        // 把node插入到tail和前一个点之间
        // 这样node就是真实的tail
        node.next = tail;
        // 连接node和之前的真实的tail
        node.prev = tail.prev;
        // 之前的真实的tail
        tail.prev.next = node;
        tail.prev = node;
        return node;
    }

    public int poll() {
        // 去掉当前真实的tail
        return remove(head.next).val;
    }

    public int peek() {
        return head.next.val;
    }

    public Node remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}


class Node {
    int val;
    Node prev;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}
```
