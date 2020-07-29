package design.tree;

public class MorrisInorderTraverse {

    public void action(TreeNode root) {

        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                // get predecessor
                TreeNode pre = node.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = node;

                TreeNode left = node.left;
                node.left = null;
                node = left;
            } else {
                System.out.println(node.val);
                node = node.right;
            }
        }
    }

    public static void main(String args[])
    {
        /* Constructed binary tree is
               1
             /   \
            2      3
          /  \
         4    5
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        MorrisInorderTraverse traverse = new MorrisInorderTraverse();
        traverse.action(root);
    }
}
