# Java Solutions for Leetcode tasks

| # | Title | Description | Basic idea (One line) |
|---| ----- | -------- | --------------------- |
| 692 | [Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/) | Return the k most frequent elements. If two words have the same frequency, then the word with the lower alphabetical order comes first | 1. Get number of occurrences for every element using hash map.<br>2. Use Priority Queue with custom comparator to poll required number of elements to the result list.<br>Note: To compare strings use compareTo() method. |
