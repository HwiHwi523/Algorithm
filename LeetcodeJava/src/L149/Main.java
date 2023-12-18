package L149;

public class Main {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // 1
        System.out.println(solution.maxPoints(new int[][]{
            new int[]{1, 1},
            new int[]{2, 2},
            new int[]{3, 3}
        }));

        // 2
        System.out.println(solution.maxPoints(new int[][]{
            new int[]{1, 1},
            new int[]{3, 2},
            new int[]{5, 3},
            new int[]{4, 1},
            new int[]{2, 3},
            new int[]{1, 4}
        }));

        // 3
        System.out.println(solution.maxPoints(new int[][]{
            new int[]{1, 1}
        }));

        // 4
        System.out.println(solution.maxPoints(new int[][]{
            new int[]{0, 1},
            new int[]{0, 0},
            new int[]{0, 4},
            new int[]{0, -2}
        }));
    }
}
