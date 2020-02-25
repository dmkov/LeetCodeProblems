package design.stack;

public class LinkedListStack {

    private Node first;
    private int size = 0;

    class Node {
        int val;
        Node next;
    }

    public void push(int num) {
        Node node = new Node();
        node.val = num;
        node.next = first;

        first = node;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        int res = first.val;
        first = first.next;
        size--;

        return res;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }

        return first.val;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("pop (3) = " + stack.pop());
        System.out.println("peek (2) = " + stack.peek());
        System.out.println("peek (2) = " + stack.peek());
        stack.push(3);
        System.out.println("pop (3) = " + stack.pop());
        System.out.println("pop (2) = " + stack.pop());
        stack.push(3);
        System.out.println("size (2) = " + stack.size());
        System.out.println("isEmpty (false) = " + stack.isEmpty());
        System.out.println("pop (3) = " + stack.pop());
        System.out.println("pop (1) = " + stack.pop());
        System.out.println("size (0) = " + stack.size());
        System.out.println("isEmpty (true) = " + stack.isEmpty());
    }

}
