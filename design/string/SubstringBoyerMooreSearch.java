package design.string;

import java.util.Arrays;

public class SubstringBoyerMooreSearch {

    public boolean search(String str, String pattern) {
        if (str == null || pattern == null || str.length() < pattern.length()) {
            return false;
        }
        if (pattern.length() == 0) {
            return true;
        }

        int pLength = pattern.length();
        int sLength = str.length();

        // KBBA
        // A -> 0, B -> 1
        int[] m = new int[256];
        Arrays.fill(m, pLength);
        for (int i = 0; i < pLength; i++) {
            if (m[pattern.charAt(pLength - i - 1)] == pLength) {
                m[pattern.charAt(pLength - i - 1)] = i;
            }
        }

        int i = pLength - 1; // 7
        while (i < sLength) {

            int p = 0;
            while (p < pLength && str.charAt(i - p) == pattern.charAt(pLength - p - 1)) {
                p++;
            }
            if (p == pLength) {
                return true;
            }

            i += Math.max(m[ str.charAt(i - p) ], 1);

        }

        return false;
    }

    public static void main(String[] args) {
        SubstringBoyerMooreSearch kmp = new SubstringBoyerMooreSearch();
        System.out.println("ABBCBABBAB & ABBA (true) = " + kmp.search("ABBCBABBAB", "ABBA"));
        System.out.println("ABBCBABBAKKKBBA & KBBA (true) = " + kmp.search("ABBCBABBAKKKBBA", "KBBA"));
        System.out.println("abcasdabcdabca & abcdabca (true) = " + kmp.search("abcasdabcdabca", "abcdabca"));
        System.out.println("adsgdsxdsgwadsgz & dsgdsgz (false) = " + kmp.search("adsgdsxdsgwadsgz", "dsgdsgz"));
        System.out.println("adsgwadsxdsgwadsgz & dsgwadsgz (true) = " + kmp.search("adsgwadsxdsgwadsgz", "dsgwadsgz"));
        System.out.println("ads & dsg (false) = " + kmp.search("ads", "dsg"));
        System.out.println("ads & empty (true) = " + kmp.search("ads", ""));
        System.out.println("ads & a (true) = " + kmp.search("ads", "a"));
    }


}
