package _622;

class MyCircularQueue {

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    int size;
    int currSize;
    Node head;
    Node tail;

    public MyCircularQueue(int k) {
        this.size = k;
        this.currSize = 0;
    }

    public boolean enQueue(int value) {
        if (currSize == size) return false;
        if (currSize == 0) {
            head = new Node(value);
            tail = head;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
        }
        currSize++;
        return true;
    }

    public boolean deQueue() {
        if (currSize == 0) return false;
        if (currSize == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }

        currSize--;

        return true;
    }

    public int Front() {
        if (currSize == 0) return -1;
        return head.val;
    }

    public int Rear() {
        if (currSize == 0) return -1;
        return tail.val;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public boolean isFull() {
        return currSize == size;
    }
}