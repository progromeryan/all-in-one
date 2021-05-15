package _708.TwoPointers;

import _708.Node;

class Solution {
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        if (head == null) {
            head = newNode;
            newNode.next = head;
            return head;
        }

        Node prev = head;
        Node curr = head.next;

        while (curr != head) {
            // [3, 5] 4 or [3,3,3] 3 找到中间位置
            if (prev.val <= insertVal && curr.val >= insertVal) break;
            // [3, 5] 10 or [3, 5] 1 到了拐点的位置,插入的是最大值或者最小值
            if (prev.val > curr.val && (prev.val <= insertVal || curr.val >= insertVal)) break;
            prev = curr;
            curr = curr.next;
        }

        prev.next = newNode;
        newNode.next = curr;
        return head;
    }
}