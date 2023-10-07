package L1766;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(Arrays.toString(solution.getCoprimes(
            new int[]{2, 3, 3, 2},
            new int[][]{
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{1, 3}
            }))
        );

        // 2
        System.out.println(Arrays.toString(solution.getCoprimes(
            new int[]{5, 6, 10, 2, 3, 6, 15},
            new int[][]{
                new int[]{0, 1},
                new int[]{0, 2},
                new int[]{1, 3},
                new int[]{1, 4},
                new int[]{2, 5},
                new int[]{2, 6},
            }))
        );
    }
}