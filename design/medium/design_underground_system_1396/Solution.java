package design.medium.design_underground_system_1396;

import java.util.HashMap;
import java.util.Map;

/**
 1396. Design Underground System
 https://leetcode.com/problems/design-underground-system/

 Implement the class UndergroundSystem that supports three methods:

 1. checkIn(int id, string stationName, int t)
 A customer with id card equal to id, gets in the station stationName at time t.
 A customer can only be checked into one place at a time.

 2. checkOut(int id, string stationName, int t)
 A customer with id card equal to id, gets out from the station stationName at time t.

 3. getAverageTime(string startStation, string endStation)
 Returns the average time to travel between the startStation and the endStation.
 The average time is computed from all the previous traveling from startStation to endStation that happened directly.
 Call to getAverageTime is always valid.
 You can assume all calls to checkIn and checkOut methods are consistent. That is, if a customer gets in at time t1 at some station, then it gets out at time t2 with t2 > t1. All events happen in chronological order.

 Constraints:
 There will be at most 20000 operations.
 1 <= id, t <= 10^6
 All strings consist of uppercase, lowercase English letters and digits.
 1 <= stationName.length <= 10
 Answers within 10^-5 of the actual value will be accepted as correct.

 --------

 1. Complexity
     1.1 Time Complexity is O(1) for all methods
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to use two hash maps: 1) for tracking user start points and 2) for tracking the stats between
        any two stations. For the first one we will add a new element when user enters the station, then when he leaves
        we take it and get the difference in time and put it to the global map with the stats between two stations.
 */

class Solution {

    class StartStation {
        String station;
        int t;
        public StartStation(String station, int t) {
            this.station = station;
            this.t = t;
        }
    }

    class StationStats {
        double total;
        int cases;
        public StationStats() {
            this.total = 0;
            this.cases = 0;
        }
    }

    Map<Integer, StartStation> start = new HashMap<>();
    Map<String, StationStats> end = new HashMap<>();

    public void checkIn(int id, String stationName, int t) {
        start.put(id, new StartStation(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        StartStation st1 = start.get(id);
        String key = st1.station + "_" + stationName;
        StationStats stats = end.getOrDefault(key, new StationStats());

        stats.total = stats.total + (t - st1.t);
        stats.cases = stats.cases + 1;
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "_" + endStation;
        StationStats stats = end.getOrDefault(key, new StationStats());
        return stats.total / stats.cases;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
