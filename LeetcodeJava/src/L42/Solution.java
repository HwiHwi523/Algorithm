package L42;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int trap(int[] height) {
        Stack stack = new Stack();
        int answer = 0;

        for (int h : height) {
            if (!stack.isEmpty() && stack.bottom() <= h) {
                while (!stack.isEmpty()) {
                    answer += stack.bottom() - stack.pop();
                }
            }

            stack.push(h);
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            while (!stack.isEmpty() && stack.top() >= top) {
                top = stack.pop();
            }

            while (!stack.isEmpty() && stack.top() < top) {
                answer += top - stack.pop();
            }
        }

        return answer;
    }

    class Stack {

        private final List<Integer> stack;

        public Stack() {
            stack = new ArrayList<>();
        }

        public void push(int height) {
            stack.add(height);
        }

        public int pop() {
            return stack.remove(stack.size() - 1);
        }

        public int top() {
            return stack.get(stack.size() - 1);
        }

        public int bottom() {
            return stack.get(0);
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
}
