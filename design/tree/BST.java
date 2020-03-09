package design.tree;

public class BST {

    private TreeNode root;

    public void insert(int val) {
        root = insert(root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        }
        node.count = ((node.left != null) ? (node.left.count) : 0) + ((node.right != null) ? (node.right.count) : 0) + 1;
        return node;
    }

    public TreeNode search(int val) {
        return search(root, val);
    }

    private TreeNode search(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (val == node.val) {
            return node;
        } else if (val < node.val) {
            return search(node.left, val);
        } else {
            return search(node.right, val);
        }
    }

    public TreeNode max() {
        TreeNode node = root;
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
    }

    public TreeNode min() {
        return min(root);
    }

    private TreeNode min(TreeNode node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    public TreeNode floor(int val) {
        return floor(root, val);
    }

    private TreeNode floor(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val == val) {
            return node;
        } else if (val < node.val) {
            return floor(node.left, val);
        } else {
            return (node.right != null) ? floor(node.right, val) : node;
        }
    }

    public TreeNode ceil(int val) {
        return ceil(root, val);
    }

    private TreeNode ceil(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val == val) {
            return node;
        } else if (val > node.val) {
            return ceil(node.right, val);
        } else {
            return (node.left != null) ? ceil(node.left, val) : node;
        }
    }

    public void delete(int val) {
        root = delete(root, val);
    }

    private TreeNode delete(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (val < node.val) {
            node.left = delete(node.left, val);
        } else if (val > node.val) {
            node.right = delete(node.right, val);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            TreeNode t = node;
            node = min(node.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.count = ((node.left != null) ? (node.left.count) : 0)
                + ((node.right != null) ? (node.right.count) : 0) + 1;

        return node;
    }

    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);

        node.count = ((node.left != null) ? (node.left.count) : 0)
                + ((node.right != null) ? (node.right.count) : 0) + 1;
        return node;
    }

    public int rank(int val) {
        return rank(root, val);
    }

    private int rank(TreeNode node, int val) {
        if (node == null) {
            return 0;
        }
        if (val == node.val) {
            return (node.left != null) ? node.left.count : 0;
        } else if (val < node.val) {
            return rank(node.left, val);
        } else {
            return ((node.left != null) ? node.left.count : 0)
                    + rank(node.right, val) + 1;
        }
    }
}
