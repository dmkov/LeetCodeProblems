package design.heap;

public class MinHeap {

    int capacity;
    int length;
    int[] arr;

    public MinHeap(int capacity) {
        this.arr = new int[capacity + 1];
        this.capacity = capacity + 1;
        this.length = 1;
    }

    public void insert(int val) {
        if (capacity == length) {
            throw new RuntimeException("Out of bounds... do upsize");
        }
        arr[length] = val;
        swim(length);
        length++;
    }

    public int poll() {
        if (length == 0) {
            throw new RuntimeException("Out of bounds... do some inserts");
        }

        exchange(1, length - 1);
        length--;
        sink(1);

        return arr[length];
    }

    private void swim(int pos) {
        while (pos > 1 && (arr[pos / 2] > arr[pos])) {
            exchange(pos, pos / 2);
            pos = pos / 2;
        }
    }

    private void sink(int pos) {
        while (2 * pos < length - 1) {
            int i = pos * 2;
            if (i + 1 < length - 1 && (arr[i + 1] < arr[i])) {
                i = i + 1;
            }
            if (arr[i] > arr[pos]) {
                break;
            }
            exchange(pos, i);
            pos = i;
        }
    }

    private void exchange(int j, int i) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
