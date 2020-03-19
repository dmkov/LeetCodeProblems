package binary_search.medium.time_based_key_value_store_981;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 981. Time Based Key-Value Store
 https://leetcode.com/problems/time-based-key-value-store/

 Create a timebased key-value store class TimeMap, that supports two operations.
 1. set(string key, string value, int timestamp)

 Stores the key and value, along with the given timestamp.
 2. get(string key, int timestamp)

 Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 If there are multiple such values, it returns the one with the largest timestamp_prev.
 If there are no values, it returns the empty string ("").

 Example 1:
 Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 Output: [null,null,"bar","bar",null,"bar2","bar2"]

 Explanation:
 TimeMap kv;
 kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 kv.get("foo", 1);  // output "bar"
 kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 kv.set("foo", "bar2", 4);
 kv.get("foo", 4); // output "bar2"
 kv.get("foo", 5); //output "bar2"

 Example 2:
 Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 Output: [null,null,null,"","high","high","low","low"]

 Note:
 All key/value strings are lowercase.
 All key/value strings have length in the range [1, 100]
 The timestamps for all TimeMap.set operations are strictly increasing.
 1 <= timestamp <= 10^7
 TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

 --------

 1. Complexity
    1.1 Time Complexity is O(1) for set and O(logn) for set methods
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use the idea that all inserts are linked to real time and timestamp for every next set is bigger than the current one.
        It means that the list is automatically sorted and we can use binary search to get the result.
 3 Implementation
    3.1 With every set get the linked ArrayList for the key and insert a new node there.
    3.2 For every get, do a binary search in the ArrayList and return the result.
 */

class BinarySearchSolution {

    class TimeNode {
        String value;
        int timestamp;

        public TimeNode(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    Map<String, List<TimeNode>> map;

    /** Initialize your data structure here. */
    public BinarySearchSolution() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<TimeNode> list = map.get(key);
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }
        list.add(new TimeNode(value, timestamp));
    }

    public String get(String key, int timestamp) {
        List<TimeNode> list = map.get(key);
        if (list == null) {
            return "";
        }

        TimeNode result = search(list, timestamp, 0, list.size() - 1);
        return result != null ? result.value : "";
    }

    private TimeNode search(List<TimeNode> list, int timestamp, int left, int right) {
        TimeNode res = null;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).timestamp == timestamp) {
                res = list.get(mid);
                break;
            } else if (list.get(mid).timestamp < timestamp) {
                res = list.get(mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

}
