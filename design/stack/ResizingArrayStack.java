package design.stack;

public class ResizingArrayStack {

    private int capacity;
    private int size;
    private int[] arr;

    public ResizingArrayStack() {
        this.capacity = 1;
        this.size = 0;
        this.arr = new int[capacity];
    }

    public void push(int num) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        arr[size++] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        if (size == (int)(capacity * 0.3)) {
            resize(capacity / 2);
        }

        return arr[--size];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return arr[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int newSize) {
        // arr = Arrays.copyOf(arr, size);
        int[] a = new int[newSize];
        for (int i = 0; i < Math.min(newSize, size); i++) {
            a[i] = arr[i];
        }
        capacity = newSize;
        arr = a;
    }

    public static void main(String[] args) {
        ResizingArrayStack stack = new ResizingArrayStack();
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
