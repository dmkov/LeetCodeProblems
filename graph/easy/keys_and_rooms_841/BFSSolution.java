package graph.easy.keys_and_rooms_841;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 841. Keys and Rooms
 https://leetcode.com/problems/keys-and-rooms/

 There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.

 Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

 Initially, all the rooms start locked (except for room 0).
 You can walk back and forth between rooms freely.
 Return true if and only if you can enter every room.

 Example 1:
 Input: [[1],[2],[3],[]]
 Output: true
 Explanation:
 We start in room 0, and pick up key 1.
 We then go to room 1, and pick up key 2.
 We then go to room 2, and pick up key 3.
 We then go to room 3.  Since we were able to go to every room, we return true.

 Example 2:
 Input: [[1,3],[3,0,1],[2],[0]]
 Output: false
 Explanation: We can't enter the room with number 2.

 Note:
 1 <= rooms.length <= 1000
 0 <= rooms[i].length <= 1000
 The number of keys in all rooms combined is at most 3000.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Use LinkedList (queue) to do a level traverse and put keys to queue for every room.
     2.2 Also check with set already visited rooms to do not create loops
 */

class BFSSolution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return false;
        }

        int n = rooms.size();
        List<Integer>[] arr = new List[n];
        int i = 0;
        for (List<Integer> keys : rooms) {
            arr[i] = keys;
            i++;
        }

        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(queue.size() > 0) {
            Integer room = queue.remove();
            if (!visited.contains(room)) {
                List<Integer> keys = arr[room];
                for (Integer key : keys) {
                    queue.add(key);
                }
                visited.add(room);
            }
        }

        return visited.size() == n;
    }
}
