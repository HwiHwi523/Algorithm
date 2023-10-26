package L76;

public class Solution {

    public String minWindow(String s, String t) {
        int[] tLowerCount = new int[26];
        int[] tUpperCount = new int[26];

        for (char ch : t.toCharArray()) {
            updateCount(tLowerCount, tUpperCount, ch, 1);
        }

        int[] sLowerCount = new int[26];
        int[] sUpperCount = new int[26];
        int left = 0;
        int resultLeft = 0;
        int resultRight = 0;
        int resultSize = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            updateCount(sLowerCount, sUpperCount, s.charAt(i), 1);

            while (left <= i && hasAllT(tLowerCount, tUpperCount, sLowerCount, sUpperCount)) {
                if (i - left + 1 < resultSize) {
                    resultLeft = left;
                    resultRight = i;
                    resultSize = i - left + 1;
                }
                updateCount(sLowerCount, sUpperCount, s.charAt(left), -1);
                left++;
            }
        }

        return resultSize == Integer.MAX_VALUE
            ? ""
            : s.substring(resultLeft, resultRight + 1);
    }

    private void updateCount(int[] lowerCount, int[] upperCount, char ch, int amount) {
        if ('a' <= ch && ch <= 'z') {
            lowerCount[ch - 'a'] += amount;
        } else {
            upperCount[ch - 'A'] += amount;
        }
    }

    private boolean hasAllT(int[] tLowerCount, int[] tUpperCount, int[] sLowerCount, int[] sUpperCount) {
        for (int i = 0; i < 26; i++) {
            if (tLowerCount[i] > sLowerCount[i] || tUpperCount[i] > sUpperCount[i]) {
                return false;
            }
        }

        return true;
    }
}
