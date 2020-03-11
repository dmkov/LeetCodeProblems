package design.heap;

public class MinHeap {

    int capacity;
    int n;
    int[] arr;

    public MinHeap(int capacity) {
        this.arr = new int[capacity + 1];
        this.capacity = capacity + 1;
        this.n = 0;
    }

    public void insert(int val) {
        if (capacity == n - 1) {
            throw new RuntimeException("Out of bounds... do upsize");
        }
        n++;
        arr[n] = val;
        swim(n);
    }

    public void build(int[] arr) {
        this.arr = new int[arr.length + 1];
        System.arraycopy(arr, 0, this.arr, 1, arr.length);

        this.capacity = arr.length + 1;
        this.n = arr.length;

        for (int i = n / 2; i >= 1; i--) {
            sink(i);
        }
    }

    public int poll() {
        if (n == 0) {
            throw new RuntimeException("Out of bounds... do some inserts");
        }

        int res = arr[1];
        exchange(1, n);
        n--;
        sink(1);

        return res;
    }

    private void swim(int pos) {
        while (pos > 1 && (arr[pos / 2] > arr[pos])) {
            exchange(pos, pos / 2);
            pos = pos / 2;
        }
    }

    private void sink(int pos) {
        while (2 * pos < n) {
            int i = pos * 2;
            if (i + 1 <= n && (arr[i + 1] < arr[i])) {
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
