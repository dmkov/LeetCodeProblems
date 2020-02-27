package design.union_find;

public class QuickFind {

    private int[] arr;

    public QuickFind(int capacity) {
        this.arr = new int[capacity + 1];
        for (int i = 1; i <= capacity; i++) {
            this.arr[i] = i;
        }
    }

    public void union(int first, int second) {
        int search = arr[second];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == search) {
                arr[i] = arr[first];
            }
        }
    }

    public boolean find(int first, int second) {
        return arr[first] == arr[second];
    }


    public static void main(String[] args) {
        QuickFind quickFind = new QuickFind(10);
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
