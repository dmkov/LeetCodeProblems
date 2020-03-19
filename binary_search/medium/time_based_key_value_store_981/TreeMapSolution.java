package binary_search.medium.time_based_key_value_store_981;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 981. Time Based Key-Value Store
 https://leetcode.com/problems/time-based-key-value-store/

 Create a timebased key-value store class TimeMap, that supports two operations.
 1. set(string key, string value, int timestamp)

 Stores the key and value, along with the given timestamp.
 2. get(string key, int timestamp)

 Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 If there are multiple such values, it returns the one with the largest timestamp_prev.
 If there are no values, it returns the empty string ("").

 Example 1:
 Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 Output: [null,null,"bar","bar",null,"bar2","bar2"]

 Explanation:
 TimeMap kv;
 kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 kv.get("foo", 1);  // output "bar"
 kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 kv.set("foo", "bar2", 4);
 kv.get("foo", 4); // output "bar2"
 kv.get("foo", 5); //output "bar2"

 Example 2:
 Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 Output: [null,null,null,"","high","high","low","low"]

 Note:
 All key/value strings are lowercase.
 All key/value strings have length in the range [1, 100]
 The timestamps for all TimeMap.set operations are strictly increasing.
 1 <= timestamp <= 10^7
 TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

 --------

 1. Complexity
    1.1 Time Complexity is O(logn) for both get and set methods
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use floor method in the BST to get need value.
    2.2 There is own implementation but it is not very efficient since it is not a red-black tree. So TreeMap is used.
 3 Implementation
    3.1 With every set get the linked TreeMap for the key and insert a new node there.
    3.2 For every get, check the linked TreeMap, then get floorEntity() and return value or "" if it does not exists.
 */

class TreeMapSolution {

    Map<String, TreeMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public TreeMapSolution() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> tree = map.get(key);
        if (tree == null) {
            tree = new TreeMap<>();
            map.put(key, tree);
        }
        tree.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> tree = map.get(key);
        if (tree == null) {
            return "";
        }

        Map.Entry<Integer, String> entry = tree.floorEntry(timestamp);
        if (entry == null) {
            return "";
        }

        return entry.getValue();
    }


    class TreeNode {
        TreeNode left, right;
        int timestamp;
        String value;

        public TreeNode(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

//    Own implementation of floor method
//
//    Map<String, TreeNode> map;
//
//    /** Initialize your data structure here. */
//    public TimeMap() {
//        map = new HashMap<>();
//    }
//
//    public void set(String key, String value, int timestamp) {
//        TreeNode root = map.get(key);
//        map.put(key, insert(root, value, timestamp));
//    }
//
//    public TreeNode insert(TreeNode node, String value, int timestamp) {
//        if (node == null) {
//            return new TreeNode(timestamp, value);
//        }
//
//        if (timestamp == node.timestamp) {
//            node.value = value;
//        } else if (timestamp < node.timestamp) {
//            node.left = insert(node.left, value, timestamp);
//        } else if (timestamp > node.timestamp) {
//            node.right = insert(node.right, value, timestamp);
//        }
//
//        return node;
//    }
//
//    public String get(String key, int timestamp) {
//        TreeNode root = map.get(key);
//        if (root == null) {
//            return "";
//        }
//
//        return floor(root, timestamp);
//    }
//
//    public String floor(TreeNode node, int timestamp) {
//        if (node == null) {
//            return "";
//        }
//
//        if (timestamp < node.timestamp) {
//            return floor(node.left, timestamp);
//        } else if (timestamp > node.timestamp
//                && node.right != null && timestamp >= node.right.timestamp) {
//            return floor(node.right, timestamp);
//        } else {
//            return node.value;
//        }
//    }

}
