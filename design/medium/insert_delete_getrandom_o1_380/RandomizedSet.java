package design.medium.insert_delete_getrandom_o1_380;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 380. Insert Delete GetRandom O(1)
 https://leetcode.com/problems/insert-delete-getrandom-o1/

 Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 2 is the only number in the set, getRandom always return 2.
 randomSet.getRandom();

 --------

 https://leetcode.com/problems/insert-delete-getrandom-o1/solution/

 1. Complexity
     1.1 Time Complexity is O(1)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Combine map and array. The first contains position for every element, the second contains elements and can
        return random item at specified index. To get random index count the total inserted elements.
 */

class RandomizedSet {

    private Map<Integer, Integer> map;
    private List<Integer> list;
    private int counter;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        counter = 0;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean result = false;
        if (map.get(val) == null) {
            list.add(counter, val);
            map.put(val, counter);
            counter++;
            result = true;
        }
        return result;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean result = false;
        Integer pos = map.get(val);
        if (pos != null) {
            Integer temp = list.get(counter - 1);
            list.set(pos, temp);
            map.put(temp, pos);
            map.remove(val);
            counter--;
            result = true;
        }
        return result;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int result = 0;
        if (counter > 0) {
            int i = (int)(Math.random() * counter);
            result = list.get(i);
        }
        return result;
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

