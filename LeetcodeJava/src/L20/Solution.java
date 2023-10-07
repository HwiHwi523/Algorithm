package L20;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public boolean isValid(String s) {
        List<Character> stack = new ArrayList<>();

        for (char ch : s.toCharArray()) {
            if (isOpenParentheses(ch)) {
                stack.add(ch);
            } else if (isCloseParentheses(ch)) {
                if (stack.isEmpty() || !isPair(topStack(stack), ch)) {
                    return false;
                }
                popStack(stack);
            }
        }

        return stack.isEmpty();
    }

    private boolean isOpenParentheses(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    private boolean isCloseParentheses(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    private boolean isPair(char openCh, char closeCh) {
        return ((openCh == '(' && closeCh == ')')
            || (openCh == '{' && closeCh == '}')
            || (openCh == '[' && closeCh == ']'));
    }

    private char topStack(List<Character> stack) {
        return stack.get(stack.size() - 1);
    }

    private void popStack(List<Character> stack) {
        stack.remove(stack.size() - 1);
    }
}
