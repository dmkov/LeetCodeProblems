package design.string;

public class TernarySearchTree {

    class TreeNode {
        TreeNode left, middle, right;
        char ch;
        boolean complete;

        public TreeNode(char ch, boolean complete) {
            this.ch = ch;
            this.complete = complete;
        }
    }

    TreeNode root;

    public void insert(String str) {
        root = insert(root, str, 0);
    }

    private TreeNode insert(TreeNode node, String str, int pos) {
        if (pos >= str.length()) {
            return null;
        }
        if (node == null) {
            node = new TreeNode(str.charAt(pos), pos == str.length() - 1);
        }

        if (node.ch == str.charAt(pos)) {
            if (pos == str.length() - 1) {
                node.complete = true;
            } else {
                node.middle = insert(node.middle, str, pos + 1);
            }
        } else if (str.charAt(pos) < node.ch) {
            node.left = insert(node.left, str, pos);
        } else if (str.charAt(pos) > node.ch) {
            node.right = insert(node.right, str, pos);
        }

        return node;
    }

    public boolean search(String str) {
        return search(root, str, 0);
    }

    private boolean search(TreeNode node, String str, int pos) {
        if (node == null) {
            return false;
        }

        if (node.ch == str.charAt(pos)) {
            if (pos == str.length() - 1) {
                return node.complete;
            }

            return search(node.middle, str, pos + 1);
        } else if (str.charAt(pos) < node.ch) {
            return search(node.left, str, pos);
        } else if (str.charAt(pos) > node.ch) {
            return search(node.right, str, pos);
        }

        return false;
    }

    public static void main(String[] args) {
        TernarySearchTree tree = new TernarySearchTree();
        tree.insert("ABA");
        tree.insert("ABC");
        tree.insert("ABB");
        tree.insert("ABF");
        tree.insert("AK");

        System.out.println("Looking for ABB (true) = " + tree.search("ABB"));
        System.out.println("Looking for ABK (false) = " + tree.search("ABK"));
        System.out.println("Looking for AK (true) = " + tree.search("AK"));
        System.out.println("Looking for ABA (true) = " + tree.search("ABA"));
        System.out.println("Looking for ABBA (false) = " + tree.search("ABBA"));
    }

}
