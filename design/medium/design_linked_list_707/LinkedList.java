package design.medium.design_linked_list_707;

public class LinkedList {
    class Node {
        Node prev;
        Node next;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    Node head;
    Node tail;
    int size;

    /** Initialize your data structure here. */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index > size - 1 || index < 0) {
            return -1;
        }
        Node node = getNode(index);

        return node.val;
    }

    private Node getNode(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node node = head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }

        return node;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        if (head != null) {
            node.next = head;
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }

        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (tail != null) {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else if (index > 0 && index < size) {
            Node cur = getNode(index);
            Node node = new Node(val);

            node.prev = cur.prev;
            cur.prev.next = node;

            node.next = cur;
            cur.prev = node;

            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index > size - 1 || index < 0) {
            return;
        }
        if (index == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        } else {
            Node node = getNode(index);
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
    }

    public static void main(String[] args) {
        LinkedList obj = new LinkedList();
        obj.addAtHead(1);
        obj.addAtTail(3);
        obj.addAtIndex(1, 2);
        obj.get(1);
        obj.deleteAtIndex(1);
        obj.get(1);
    }
}
