package design.hash;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingMap {

    List<MapItem>[] items;
    int size;

    public SeparateChainingMap(int size) {
        this.items = new List[size];
        this.size = size;
    }

    public void add(int key, String value) {
        int hash = hash(key);
        if (items[hash] == null) {
            items[hash] = new LinkedList<>();
        }
        items[hash].add(new MapItem(key, value));
    }

    public String get(int key) {
        int hash = hash(key);
        MapItem hashMapItem = null;
        for (MapItem item : items[hash]) {
            hashMapItem = item;
            if (item.key == key) {
                break;
            }
        }
        return (hashMapItem != null) ? hashMapItem.value : null;
    }

    private int hash(int key) {
        // primitive hashing function, just to limit everything to the range of numbers
        return key % size;
    }


    public static void main(String[] args) {
        SeparateChainingMap hashMap = new SeparateChainingMap(747);
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
