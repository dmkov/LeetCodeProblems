package design.string;

public class SubstringKMPSearch {

    public boolean search(String str, String pattern) {
        if (str == null || pattern == null || str.length() < pattern.length()) {
            return false;
        }
        if (pattern.length() == 0) {
            return true;
        }

        int[] m = new int[pattern.length()];
        m[0] = 0;
        int i = 0;
        for (int j = 1; j < pattern.length(); j++) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                m[j] = i + 1;
                i++;
            } else {
                if (i > 0) {
                    i = m[i - 1];
                    j--;
                } else {
                    i = 0;
                    m[j] = 0;
                }
            }
        }

        int p = 0;
        for (int s = 0; s < str.length(); s++) {
            while (p != 0 && str.charAt(s) != pattern.charAt(p)) {
                p = m[p - 1];
            }
            if (str.charAt(s) == pattern.charAt(p)) {
                p++;
            }
            if (p == pattern.length()) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SubstringKMPSearch kmp = new SubstringKMPSearch();
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
