package L2390;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public String removeStars(String s) {
        List<Character> stack = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                stack.remove(stack.size() - 1);
            } else {
                stack.add(ch);
            }
        }

        StringBuilder answer = new StringBuilder();
        for (char ch : stack) {
            answer.append(ch);
        }
        return answer.toString();
    }
}
