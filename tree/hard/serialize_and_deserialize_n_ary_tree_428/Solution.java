package tree.hard.serialize_and_deserialize_n_ary_tree_428;

import java.util.ArrayList;
import java.util.List;

/**
 428. Serialize and Deserialize N-ary Tree
 https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 You do not necessarily need to follow the above suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 The idea is to keep number of children for every node and pointer for active node in deserialization process
 */

class Solution {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val + ":" + root.children.size());
        for (Node child : root.children) {
            sb.append(",");
            sb.append(serialize(child));
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] arr = str.split(",");
        int[] p = new int[1];
        p[0] = 0;

        Node root = deserialize(arr, p);
        return root;
    }

    private Node deserialize(String[] arr, int[] p) {
        String str = arr[p[0]];
        String[] val = str.split(":");
        p[0] = p[0] + 1;

        Node node = new Node(Integer.valueOf(val[0]));
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(val[1]); i++) {
            children.add(deserialize(arr, p));
        }
        node.children = children;

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
