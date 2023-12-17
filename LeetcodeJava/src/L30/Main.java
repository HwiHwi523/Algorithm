package L30;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(
            solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));

        // 2
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword",
            new String[]{"word", "good", "best", "word"}));

        // 3
        System.out.println(
            solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));

        // 4
        System.out.println(solution.findSubstring("a", new String[]{"a", "a"}));

        // 5
        System.out.println(solution.findSubstring("a", new String[]{"a"}));
    }
}
