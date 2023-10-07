package L15;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));

        // 2
        System.out.println(solution.threeSum(new int[]{0, 1, 1}));

        // 3
        System.out.println(solution.threeSum(new int[]{0, 0, 0}));
    }
}
