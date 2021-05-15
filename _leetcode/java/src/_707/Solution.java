package _707;


class MyLinkedList {

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    Node head;
    Node tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        Node node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }

        return node.val;
    }

    public void addAtHead(int val) {
        head = new Node(val, head);

        // 只有一个元素
        if (size++ == 0) {
            tail = head;
        }
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        if (size++ == 0) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        if (index == 0) {
            addAtHead(val);
            return;
        }

        if (index == size) {
            addAtTail(val);
            return;
        }

        Node dummy = new Node(0, head);
        Node prev = dummy;

        while (index > 0) {
            prev = prev.next;
            index--;
        }

        prev.next = new Node(val, prev.next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        Node dummy = new Node(0, head);
        Node prev = dummy;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = prev.next.next;
        if (index == 0) head = prev.next;
        // 使用size的好处，可以判断是不是最后一个
        if (index == size - 1) tail = prev;
        size--;
    }
}
