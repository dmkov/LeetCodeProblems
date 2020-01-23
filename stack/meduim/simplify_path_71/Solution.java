package stack.meduim.simplify_path_71;

import java.util.Stack;

/**
 71. Simplify Path
 https://leetcode.com/problems/simplify-path/

 Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

 In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

 Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.


 Example 1:
 Input: "/home/"
 Output: "/home"
 Explanation: Note that there is no trailing slash after the last directory name.

 Example 2:
 Input: "/../"
 Output: "/"
 Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

 Example 3:
 Input: "/home//foo/"
 Output: "/home/foo"
 Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

 Example 4:
 Input: "/a/./b/../../c/"
 Output: "/c"

 Example 5:
 Input: "/a/../../b/../c//.//"
 Output: "/c"

 Example 6:
 Input: "/a//b////c/d//././/.."
 Output: "/a/b/c"

---

 1. Complexity
    1.1 Time Complexity is O(n) where n is the number of "/" + 1
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The solution is based on the idea of splitting the string and using a stack to process directories
 3. Implementation
    3.1 Check if the input string is valid
    3.2 Split string into array using "/" as a separator
    3.3 Iterate received array:
        3.3.1 If word is empty or equals "." - do nothing
        3.3.2 If word equals to ".." and stack is not empty - pop the last element
        3.3.3 Otherwise, push the value to the stack
    3.4 Iterate received stack and prepend values to the result string.
 */

class Solution {
    public String simplifyPath(String path)
    {
        String result = "";
        if (path == null || path.length() == 0)
        {
            return result;
        }

        Stack<String> stack = new Stack<>();
        String[] folders = path.split("/");
        for (String folder : folders)
        {
            if ("".equals(folder) || ".".equals(folder))
            {
                continue;
            }
            else if ("..".equals(folder))
            {
                if (stack.size() > 0)
                {
                    stack.pop();
                }
            }
            else
            {
                stack.push(folder);
            }
        }

        while (stack.size() > 0) {
            result = "/" + stack.pop() + result;
        }
        if (result.length() == 0)
        {
            result = "/";
        }

        return result;
    }
}
