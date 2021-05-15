package _705.BST;


class MyHashSet {
    private Bucket[] buckets;
    private int mod;

    public MyHashSet() {
        this.mod = 769;
        this.buckets = new Bucket[this.mod];
        for (int i = 0; i < this.mod; ++i)
            this.buckets[i] = new Bucket();
    }

    protected int getHash(int key) {
        return (key % this.mod);
    }

    public void add(int key) {
        int bucketIndex = this.getHash(key);
        this.buckets[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this.getHash(key);
        this.buckets[bucketIndex].delete(key);
    }

    public boolean contains(int key) {
        int bucketIndex = this.getHash(key);
        return this.buckets[bucketIndex].exists(key);
    }
}


class Bucket {
    private BSTree tree;

    public Bucket() {
        tree = new BSTree();
    }

    public void insert(Integer key) {
        this.tree.root = this.tree.insertIntoBST(this.tree.root, key);
    }

    public void delete(Integer key) {
        this.tree.root = this.tree.deleteNode(this.tree.root, key);
    }

    public boolean exists(Integer key) {
        TreeNode node = this.tree.searchBST(this.tree.root, key);
        return (node != null);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class BSTree {
    TreeNode root = null;

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val) return root;

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val == root.val) {
            return root;
        } else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }
}
