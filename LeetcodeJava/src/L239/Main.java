package L239;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(
            Arrays.toString(solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));

        // 2
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1}, 1)));
    }
}
