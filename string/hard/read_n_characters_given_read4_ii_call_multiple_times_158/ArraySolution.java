package string.hard.read_n_characters_given_read4_ii_call_multiple_times_158;

/**
 158. Read N Characters Given Read4 II - Call multiple times
 https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/

 Given a file and assume that you can only read the file using a given method read4, implement a method read to read n characters. Your method read may be called multiple times.

 Method read4:
 The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
 The return value is the number of actual characters read.
 Note that read4() has its own file pointer, much like FILE *fp in C.

 Definition of read4:
 Parameter:  char[] buf4
 Returns:    int

 Note: buf4[] is destination not source, the results from read4 will be copied to buf4[]
 Below is a high level example of how read4 works:

 File file("abcde"); // File is "abcde", initially file pointer (fp) points to 'a'
 char[] buf = new char[4]; // Create buffer with enough space to store characters
 read4(buf4); // read4 returns 4. Now buf = "abcd", fp points to 'e'
 read4(buf4); // read4 returns 1. Now buf = "e", fp points to end of file
 read4(buf4); // read4 returns 0. Now buf = "", fp points to end of file

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Save prev array as a copy and use it for the later iteration
    2.2 As alternative we can push all result to the queue and pop from it before the next call
 */

class ArraySolution {

    char[] prevBuf = new char[4];
    int prevSize = 0;
    int prevIndex = 0;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        // n - is the total length to read
        // read4(buf) - reading by 4 characters into buf and return number of read characters

        int p = 0;
        while (p < n) {
            if (prevSize > prevIndex) {
                buf[p++] = prevBuf[prevIndex++];
            } else {
                char[] b = new char[4];
                prevSize = read4(b);
                if (prevSize == 0) {
                    break;
                }

                prevBuf = b;
                prevIndex = 0;
            }
        }

        return p;
    }

    private int read4(char[] arr) {
        return 4;
    }
}

/**

 1. So the word itself is not a palindrome but if we add another word to it, it becames palindrome?
 2. And we add only one word, not two or three?
 3. Are all words have the same length?
 4. And palindrome - the word that looks from both directions identically?
 5. Is it ASCII string? What is the possible range of characters?
 - What is the max possible length of the string? Can we have empty strings?
 6. What is the max/min length of the given list?
 7. All indexes start from 0?


 1. brute force - O(n^2 * str.length) -

 2. for every string add element to the end - check if it is palindrome
 str.length * str.length * n  - more efficient with small words
 --- does not work because of "s" -> "lls" = slls ---

 3.   abc -> "ba", "cba", "..cba"
 "cb", "cba", "cb..." <- abc

 1. iterate strings - put to hash map <string, position>
 2. iterate strings:
     from end to start:
        if passed string is palindrome - reverse remaining part and check values from map
     from start to end:
        if passed string is palindrome - reverse remaining part and check values from map
     O(str1.length*str1.length*words)

 4. trie approach
     1. put reverse words into a trie, if word is not ended - keep list of indexes
     2. lookup word in the trie:
         - if there is no char: continue to the next word
         - otherwise:
             if char is end position:
                 if the current pos matches the length -> add pair
                 if rest of the word is palindrome -> add pair
         - no more chars in the word:
         look at list of followed indexes -> if substring(0, length-k) is palindrome -> add a pair

 */
