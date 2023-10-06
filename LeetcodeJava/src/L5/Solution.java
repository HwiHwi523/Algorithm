package L5;

public class Solution {

    public String longestPalindrome(String s) {
        // 1 : true
        // 0 : unknown
        // -1 : false
        byte[][] palindromeStatus = getPalindromeStatus(s.length());

        for (int size = s.length(); size >= 2; size--) {
            for (int endIndex = size - 1; endIndex < s.length(); endIndex++) {
                if (isPalindrome(s, endIndex - size + 1, endIndex, palindromeStatus)) {
                    return s.substring(endIndex - size + 1, endIndex + 1);
                }
            }
        }

        return s.substring(0, 1);
    }

    private byte[][] getPalindromeStatus(int size) {
        byte[][] palindromeStatus = new byte[size][size];

        for (int i = 0; i < size; i++) {
            palindromeStatus[i][i] = 1;
        }

        return palindromeStatus;
    }

    private boolean isPalindrome(String s, int begin, int end, byte[][] palindromeStatus) {
        if (begin >= end) {
            return true;
        }

        if (palindromeStatus[begin][end] != 0) {
            return palindromeStatus[begin][end] == 1;
        }

        boolean result = false;

        if (s.charAt(begin) == s.charAt(end)) {
            result = isPalindrome(s, begin + 1, end - 1, palindromeStatus);
        }

        palindromeStatus[begin][end] = (byte) (result ? 1 : -1);

        return result;
    }
}
