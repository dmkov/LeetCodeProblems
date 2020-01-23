package design.medium.lru_cache_146;

import java.util.HashMap;
import java.util.Map;

/**
 146. LRU Cache
 https://leetcode.com/problems/lru-cache/

 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 The cache is initialized with a positive capacity.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 --------

 https://www.youtube.com/watch?v=S6IfqDXWa10

 1. Complexity
     1.1 Time Complexity is O(1)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Use HashMap to store key-Node pairs and LinkedList approach to store head to tail list of nodes. With every
        get() method we need to get Node from map and move it to the head in our list. Every new item is also should be added
        to the head of the list. After every adding - check the current size and remove tail element if it is bigger than needed.
 */

class LRUCache {

    class Node {
        Node prev;
        Node next;
        int key;
        int value;
    }

    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        //capacity should be greater than 0
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();

        this.tail.next = head;
        this.head.prev = tail;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            moveToTheHead(node);
            return node.value;
        }
        return -1;
    }

    private void moveToTheHead(Node node) {
        if (node.prev != null && node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.next = head;
        node.prev = head.prev;
        head.prev.next = node;
        head.prev = node;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node();
            node.key = key;
            size++;
        }
        node.value = value;

        moveToTheHead(node);
        map.put(key, node);

        if (size > capacity) {
            removeFromTail();
        }
    }

    private void removeFromTail() {
        Node node = tail.next;
        node.next.prev = tail;
        tail.next = node.next;

        map.remove(node.key);
        size--;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
