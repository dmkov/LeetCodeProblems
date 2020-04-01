package tree.hard.serialize_and_deserialize_binary_tree_297;

/**
 297. Serialize and Deserialize Binary Tree
 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 Example:
 You may serialize the following tree:
        1
       / \
      2   3
         / \
        4   5
 as "[1,2,3,null,null,4,5]"

 Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 The idea is to use preorder traverse to create a string with string builder and then using the same approach decode
        the string into node objects.
 3. Implementation
     3.1 To serialize - create a StringBuilder and pass it to the traverse method
        where put all values as Strings in the preorder traverse
     3.2 To deserialize - split string using char-separator start traversing the array of strings. If string equals to
        special null character - return a null-node, otherwise, continue process for the lft and right nodes
 */

class PreorderTraverseSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorderEncode(root, sb);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int[] pos = new int[]{0};

        return preorderDecode(pos, arr);
    }

    // 1,2,null,null,3,4,null,null,5,null,null

    private void preorderEncode(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("|,");
        } else {
            sb.append(node.val + ",");
            preorderEncode(node.left, sb);
            preorderEncode(node.right, sb);
        }
    }

    private TreeNode preorderDecode(int[] pos, String[] arr) {
        TreeNode node = null;

        if (arr[pos[0]].equals("|")) {
            pos[0]++;
        } else {
            node = new TreeNode( Integer.valueOf(arr[pos[0]]) );
            pos[0]++;
            node.left = preorderDecode(pos, arr);
            node.right = preorderDecode(pos, arr);
        }

        return node;
    }
}
