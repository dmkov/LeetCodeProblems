package design.union_find;

public class QuickUnionWeighted {

    private int[] arr;
    private int[] size;

    public QuickUnionWeighted(int capacity) {
        this.arr = new int[capacity + 1];
        this.size = new int[capacity + 1];
        for (int i = 1; i <= capacity; i++) {
            this.arr[i] = i;
        }
    }

    public void union(int first, int second) {
        int i = parent(first);
        int j = parent(second);
        if (i == j) {
            return;
        }
        if (size[i] < size[j]) {
            size[j] += size[i];
            arr[i] = j;
        } else {
            size[i] += size[j];
            arr[j] = i;
        }
    }

    private int parent(int num) {
        while (arr[num] != num) {
            arr[num] = parent(arr[num]);
            num = parent(arr[num]);
        }
        return num;
    }

    public boolean find(int first, int second) {
        return parent(first) == parent(second);
    }


    public static void main(String[] args) {
        QuickUnionWeighted quickFind = new QuickUnionWeighted(10);
        quickFind.union(2, 3);
        quickFind.union(3, 10);
        quickFind.union(5, 8);
        quickFind.union(1, 10);
        quickFind.union(9, 7);
        System.out.println("2 and 3 (true) = " + quickFind.find(2, 3));
        System.out.println("2 and 1 (true) = " + quickFind.find(2, 1));
        System.out.println("9 and 7 (true) = " + quickFind.find(9, 7));
        System.out.println("7 and 10 (false) = " + quickFind.find(7, 10));
        System.out.println("4 and 10 (false) = " + quickFind.find(4, 10));
        System.out.println("2 and 7 (false) = " + quickFind.find(2, 7));
    }

}
