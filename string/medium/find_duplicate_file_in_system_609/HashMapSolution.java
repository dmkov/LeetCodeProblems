package string.medium.find_duplicate_file_in_system_609;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 609. Find Duplicate File in System
 https://leetcode.com/problems/find-duplicate-file-in-system/

 Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.

 A group of duplicate files consists of at least two files that have exactly the same content.
 A single directory info string in the input list has the following format:
 "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

 It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

 The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:
 "directory_path/file_name.txt"

 Example 1:
 Input:
 ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
 Output:
 [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

 Note:
 1. No order is required for the final output.
 2. You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
 3. The number of files given is in the range of [1,20000].
 4. You may assume no files or directories share the same name in the same directory.
 5. You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.

 Follow-up beyond contest:
 1. Imagine you are given a real file system, how will you search files? DFS or BFS?
 2. If the file content is very large (GB level), how will you modify your solution?
 3. If you can only read the file by 1kb each time, how will you modify your solution?
 4. What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
 5. How to make sure the duplicated files you find are not false positive?

---

 1. Complexity
    1.1 Time Complexity is O(n*m), where n is number of strings and m is files in the string
    1.2 Space Complexity is O(n*m)
 2. Approach
    2.1 Use hash map to store content (or its hash in the advanced version) against the list of files. Return
        the list for the content that has more than one file.
 3. Implementation
    3.1 Check if the input string is valid
    3.2 Split string using " " character. The first element contains the path, all others - files with content
    3.3 For every next element - get substring before "(" and after it (filename and content)
    3.4 Store filename to the list in the map with content as a key
    3.5 Iterate all entries in the map and store to the result everything bigger than 1 element.

    Follow-up questions - https://leetcode.com/problems/find-duplicate-file-in-system/discuss/104120/Follow-up-questions-discussion
 */

class HashMapSolution {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new LinkedList<>();
        if (paths == null || paths.length == 0) {
            return result;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] str = path.split(" ");
            for (int i = 1; i < str.length; i++) {
                int pos = str[i].indexOf('(');
                String file = str[i].substring(0, pos);
                String content = str[i].substring(pos + 1, str[i].length() - 1);

                List<String> list = map.getOrDefault(content, new LinkedList<>());
                list.add(str[0] + "/" + file);
                map.put(content, list);
            }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            if (list.size() > 1) {
                result.add(list);
            }
        }

        return result;
    }
}
