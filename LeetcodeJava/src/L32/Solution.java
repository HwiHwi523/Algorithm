package L32;

public class Solution {

    public int longestValidParentheses(String s) {
        int leftMaxSize = 0;
        int left = 0;
        int stack = 0;

        for (int i = 0; i < s.length(); i++) {
            stack += s.charAt(i) == '(' ? 1 : -1;

            if (stack < 0) {
                stack = 0;
                left = i + 1;
            } else if (stack == 0 && leftMaxSize < i - left + 1) {
                leftMaxSize = i - left + 1;
            }
        }

        int rightMaxSize = 0;
        int right = s.length() - 1;
        stack = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            stack += s.charAt(i) == ')' ? 1 : -1;

            if (stack < 0) {
                stack = 0;
                right = i - 1;
            } else if (stack == 0 && rightMaxSize < right - i + 1) {
                rightMaxSize = right - i + 1;
            }
        }

        return Math.max(leftMaxSize, rightMaxSize);
    }
}
