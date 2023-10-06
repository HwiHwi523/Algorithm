package L3;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        Queue<Character> queue = new ArrayDeque<>();
        Set<Character> included = new HashSet<>();
        int maxQueueSize = 0;

        for (char ch : s.toCharArray()) {
            while (!queue.isEmpty() && included.contains(ch)) {
                char front = queue.poll();
                included.remove(front);
            }

            queue.offer(ch);
            included.add(ch);

            maxQueueSize = Math.max(maxQueueSize, queue.size());
        }

        return maxQueueSize;
    }
}
