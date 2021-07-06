就是把一颗二叉树上所有的叶子节点连接成一个双向链表，注意要求有序!也就是按照他们各自 的位置顺序。

新建 node

```java
public Node linkLeaf(TreeNode root) {
    Node tail = null;
    tail = traverse(root, tail);
    Node curr = tail;
    while(curr.prev != null) {
        curr = curr.prev;
    }

    curr.prev = tail;
    tail.next = curr;
    return curr;
}

public Node traverse(TreeNode root, Node tail) {
    if(root == null) return tail;
    if(root.left == null && root.right == null) {
        if(tail == null) {
            tail = new Node(root.val);
        } else {
            Node node = new Node(root.val);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return tail;
    }

    tail = traverse(root.left, tail);
    tail = traverse(root.right, tail);
    return tail;
}
```

in place

```java
public TreeNode linkLeaf(TreeNode root) {
    TreeNode prev = null;
    TreeNode tail = traverse(root, prev);
    prev = tail;
    while(prev.left != null) {
        prev = prev.left;
    }

    prev.left = tail;
    tail.right = prev;
    return prev;
}

public Node traverse(TreeNode root, TreeNode prev) {
    if(root == null) return prev;

    if(root.left == null && root.right == null) {
        if(prev == null) {
            prev = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        return prev;
    }

    prev = traverse(root.left, prev);
    prev = traverse(root.right, prev);
    return prev;
}
```
