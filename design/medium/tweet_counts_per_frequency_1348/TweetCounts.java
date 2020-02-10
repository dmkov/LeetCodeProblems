package design.medium.tweet_counts_per_frequency_1348;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 1348. Tweet Counts Per Frequency
 https://leetcode.com/problems/tweet-counts-per-frequency/

 Implement the class TweetCounts that supports two methods:
 1. recordTweet(string tweetName, int time)

 Stores the tweetName at the recorded time (in seconds).
 2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)

 Returns the total number of occurrences for the given tweetName per minute, hour, or day (depending on freq) starting from the startTime (in seconds) and ending at the endTime (in seconds).
 freq is always minute, hour or day, representing the time interval to get the total number of occurrences for the given tweetName.

 The first time interval always starts from the startTime, so the time intervals are [startTime, startTime + delta*1>,  [startTime + delta*1, startTime + delta*2>, [startTime + delta*2, startTime + delta*3>, ... , [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)> for some non-negative number i and delta (which depends on freq).

 Example:
 Input
 ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]

 Output
 [null,null,null,null,[2],[2,1],null,[4]]

 Explanation
 TweetCounts tweetCounts = new TweetCounts();
 tweetCounts.recordTweet("tweet3", 0);
 tweetCounts.recordTweet("tweet3", 60);
 tweetCounts.recordTweet("tweet3", 10);
 // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
 tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59);
 // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
 tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60);
 // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
 tweetCounts.recordTweet("tweet3", 120);
 // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
 tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);
 // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.

 --------

 1. Complexity
     1.1 Time Complexity is O(n) -- in the worst case, if all times in one interval
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 To speed up the search, use precomputed buckets for the time interval in hash maps.
     2.2 Calculate integer value of day, month and hour for the given time.
     2.3 Add time to the list in the according integer interval in the map.
     2.4 When you need to count time events, retrieve items from all matched intervals (intervals where start time and end time
        placed + everything in between).
 */

class TweetCounts {

    Map<String, Map<Integer, List<Integer>>> days;
    Map<String, Map<Integer, List<Integer>>> hours;
    Map<String, Map<Integer, List<Integer>>> minutes;

    public TweetCounts() {
        days = new HashMap<>();
        hours = new HashMap<>();
        minutes = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        Map<Integer, List<Integer>> day = days.getOrDefault(tweetName, new HashMap<Integer, List<Integer>>());
        List<Integer> dayNum = day.getOrDefault((int)(time / 86400), new ArrayList<Integer>());
        dayNum.add(time);
        day.put((int)(time / 86400), dayNum);
        days.put(tweetName, day);

        Map<Integer, List<Integer>> hour = hours.getOrDefault(tweetName, new HashMap<Integer, List<Integer>>());
        List<Integer> hourNum = hour.getOrDefault((int)(time / 3600), new ArrayList<Integer>());
        hourNum.add(time);
        hour.put((int)(time / 3600), hourNum);
        hours.put(tweetName, hour);

        Map<Integer, List<Integer>> minute = minutes.getOrDefault(tweetName, new HashMap<Integer, List<Integer>>());
        List<Integer> minuteNum = minute.getOrDefault((int)(time / 60), new ArrayList<Integer>());
        minuteNum.add(time);
        minute.put((int)(time / 60), minuteNum);
        minutes.put(tweetName, minute);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        Map<Integer, List<Integer>> map = days.getOrDefault(tweetName, new HashMap<Integer, List<Integer>>());
        if (freq.equals("minute")) {
            map = minutes.getOrDefault(tweetName, new HashMap<Integer, List<Integer>>());
        }
        if (freq.equals("hour")) {
            map = hours.getOrDefault(tweetName, new HashMap<Integer, List<Integer>>());
        }

        int coeff = 86400;
        if (freq.equals("minute")) {
            coeff = 60;
        }
        if (freq.equals("hour")) {
            coeff = 3600;
        }

        int[] result = new int[(endTime - startTime) / coeff + 1];
        for (int i = (startTime / coeff); i <=  endTime / coeff; i++) {
            List<Integer> list = map.getOrDefault(i, new ArrayList<Integer>());
            for (int time : list) {
                if (time >= startTime && time <= endTime) {
                    int index = (time - startTime) / coeff;
                    result[index] += 1;
                }
            }
        }

        List<Integer> ob = new ArrayList<>();
        for (int var : result) {
            ob.add(var);
        }

        return ob;
    }

    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet0", 33);
        tweetCounts.recordTweet("tweet1", 89);
        tweetCounts.recordTweet("tweet2", 99);
        tweetCounts.recordTweet("tweet3", 53);
        tweetCounts.recordTweet("tweet4", 3);
        tweetCounts.getTweetCountsPerFrequency("hour", "tweet0", 89,3045);
    }

}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */
