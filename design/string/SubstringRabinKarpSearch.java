package design.string;

public class SubstringRabinKarpSearch {

    public boolean search(String str, String pattern) {
        if (str == null || pattern == null || str.length() < pattern.length()) {
            return false;
        }
        if (pattern.length() == 0) {
            return true;
        }

        int pLength = pattern.length();
        int sLength = str.length();

        // get hash of pattern
        int patternHash = 0;
        int power = 1;
        for (char ch : pattern.toCharArray()) {
            patternHash += power * ch; // 26 + 27*10 + 27*100 + 26*1000
            power *= 10;
        }

        // get hash of first substring
        int stringHash = 0;
        power = 1;
        for (int s = 0; s < pLength; s++) {
            stringHash += power * str.charAt(s);  // 26 + 27*10 + 27*100 + 28*1000
            power *= 10;
        }

        if (patternHash == stringHash && checkSubstring(pattern, str.substring(0, pLength))) {
            return true;
        }

        // ABBCBABBAB & ABBA

        // continue checking the string hash
        int maxPower = (int)Math.pow(10, pLength - 1);
        for (int i = pLength; i < sLength; i++) {
            // remove first char and reduce power
            stringHash = (stringHash - str.charAt(i - pLength)) / 10; // 27 + 27*10 + 28*100
            // add the next element multiplied on max power coefficient
            stringHash = stringHash + str.charAt(i) * maxPower; // 27 + 27*10 + 28*100 + 27*1000

            if (stringHash == patternHash && checkSubstring(pattern, str.substring(i - pLength + 1, i + 1))) {
                return true;
            }
        }

        return false;
    }

    private boolean checkSubstring(String pattern, String substring) {
        return pattern.equals(substring);
    }

    public static void main(String[] args) {
        SubstringRabinKarpSearch kmp = new SubstringRabinKarpSearch();
        System.out.println("ABBCBABBAB & ABBA (true) = " + kmp.search("ABBCBABBAB", "ABBA"));
        System.out.println("ABBCBABBAKKKBBA & KBBA (true) = " + kmp.search("ABBCBABBAKKKBBA", "KBBA"));
        System.out.println("abcasdabcdabca & abcdabca (true) = " + kmp.search("abcasdabcdabca", "abcdabca"));
        System.out.println("adsgdsxdsgwadsgz & dsgdsgz (false) = " + kmp.search("adsgdsxdsgwadsgz", "dsgdsgz"));
        System.out.println("adsgwadsxdsgwadsg & dsgwadsg (true) = " + kmp.search("adsgwadsxdsgwadsg", "dsgwadsg"));
        System.out.println("ads & dsg (false) = " + kmp.search("ads", "dsg"));
        System.out.println("ads & empty (true) = " + kmp.search("ads", ""));
        System.out.println("ads & a (true) = " + kmp.search("ads", "a"));
    }


}
