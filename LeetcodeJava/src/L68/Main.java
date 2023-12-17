package L68;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.fullJustify(
            new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));

        // 2
        System.out.println(solution.fullJustify(
            new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));

        // 3
        System.out.println(solution.fullJustify(
            new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to",
                "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"},
            20));
    }
}
