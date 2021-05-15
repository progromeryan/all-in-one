package _708.Loop;

import _708.Node;

class Solution {
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        int len = 1;
        Node node = head.next;
        while (node != head) {
            node = node.next;
            len++;
        }

        if (len == 1) {
            head.next = newNode;
            newNode.next = head;
            return head;
        }

        int prevVal = Integer.MIN_VALUE;
        // 有可能要插入的值是最小值
        int maxVal = head.val;
        node = head;

        for (int i = 0; i < len; i++) {
            if (node.val <= insertVal) {
                prevVal = Math.max(prevVal, node.val);
            }
            maxVal = Math.max(maxVal, node.val);
            node = node.next;
        }

        if (prevVal == Integer.MIN_VALUE) {
            prevVal = maxVal;
        }

        node = head;
        Node prevNode = node;

        for (int i = 0; i < len; i++) {
            if (node.val == prevVal) {
                prevNode = node;
            }
            node = node.next;
        }

        Node next = prevNode.next;
        prevNode.next = newNode;
        newNode.next = next;

        return head;
    }
}