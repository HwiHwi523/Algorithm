package L41;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.firstMissingPositive(new int[]{1, 1}));

        // 2
        System.out.println(solution.firstMissingPositive(new int[]{3, 4, -1, 1}));

        // 3
        System.out.println(solution.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }
}