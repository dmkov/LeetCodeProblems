package design.hash;

public class OpenProbingMap {

    MapItem[] items;
    int size;

    public OpenProbingMap(int size) {
        this.items = new MapItem[size];
        this.size = size;
    }

    public void add(int key, String value) {
        int hash = hash(key);
        while (items[hash] != null && items[hash].key != key) {
            hash = (hash + 1) % size;
        }
        items[hash] = new MapItem(key, value);
    }

    public String get(int key) {
        int hash = hash(key);
        while (items[hash] != null && items[hash].key != key) {
            hash = (hash + 1) % size;
        }

        return (items[hash] != null) ? items[hash].value : null;
    }

    private int hash(int key) {
        // primitive hashing function, just to limit everything to the range of numbers
        return key % size;
    }

    public static void main(String[] args) {
        OpenProbingMap hashMap = new OpenProbingMap(747);
        hashMap.add(1111, "1111");
        hashMap.add(747, "747");
        hashMap.add(1, "1");
        hashMap.add(999, "999");
        hashMap.add(748, "748");

        System.out.println("1111 = " + hashMap.get(1111));
        System.out.println("747 = " + hashMap.get(747));
        System.out.println("1 = " + hashMap.get(1));
        System.out.println("999 = " + hashMap.get(999));
        System.out.println("748 = " + hashMap.get(748));
    }
}
