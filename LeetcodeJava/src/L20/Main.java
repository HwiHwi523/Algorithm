package L20;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.isValid("()"));

        // 2
        System.out.println(solution.isValid("()[]{}"));

        // 3
        System.out.println(solution.isValid("(]"));
    }
}
