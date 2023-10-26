package L76;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));

        // 2
        System.out.println(solution.minWindow("a", "a"));

        // 3
        System.out.println(solution.minWindow("a", "aa"));
    }
}
