给一个 binary tree 和一个 target node, construct root 到 target 的双向链表
input tree t: 1,2,3,4,5,null,null target: 5 要求返回 root 1 到 target 5 的路径的 doubly linked list,即 1<->2<->5

第二题是在一个 bst 里面找 root 到 target node 的 path，一开始我没看清，直接写了 general tree 里面找 node，后来他提醒了，就改过来了。写完了还早，他就让我完成之前 general tree 的代码。自己给自己 加难度算是。然后 run 了几个 test case，问了点问题就结束了。

BST

We can use the property of BST to search the target node and build the linked list while searching.

```java
public TreeNode findRoute(TreeNode root, TreeNode target) {
    traverse(root, target);
    TreeNode curr = root;
    while(curr.right != null){
        curr = curr.right;
    }

    root.left = curr;
    curr.right = root;
    return root;
}

public boolean traverse(TreeNode root, TreeNode target) {
    if (root == null) return false;
    if(root == target) {
        root.left = null;
        root.right = null;
        return true;
    } else {
        boolean left = traverse(root.left, target);
        if(left) {
            root.right = root.left;
            root.left.left = root;
            root.left = null;
            return true;
        }
        boolean right = traverse(root.right, target);
        if(right) {
            root.right.left = root;
            root.left = null;
            return true;
        }
        return false;
    }
}
```

from bst

```java
public Node getDDLFromBST(TreeNode root, TreeNode target) {
    Node dummy = new Node(-1);
    Node curr = dummy;

    while(root != null) {
        Node node = new Node(root.val);
        curr.next = node;
        node.prev = curr;
        curr = curr.next;
        if(root.val > target.val) {
            root = root.left;
        } else if (root.val < target.val) {
            root = root.right;
        } else {
            curr.next = dummy.next;
            dummy.next.prev = curr;
            break;
        }
    }

    return dummy.next;
}
```
