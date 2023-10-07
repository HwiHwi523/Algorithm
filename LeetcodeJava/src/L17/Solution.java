package L17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();

        Map<Character, List<String>> toLetter = Map.of(
            '2', List.of("a", "b", "c"),
            '3', List.of("d", "e", "f"),
            '4', List.of("g", "h", "i"),
            '5', List.of("j", "k", "l"),
            '6', List.of("m", "n", "o"),
            '7', List.of("p", "q", "r", "s"),
            '8', List.of("t", "u", "v"),
            '9', List.of("w", "x", "y", "z")
        );

        setCombinations(result, new ArrayList<>(), digits, 0, toLetter);

        return result;
    }

    private void setCombinations(List<String> result, List<String> comb, String digits, int depth,
        Map<Character, List<String>> toLetter) {
        if (digits.length() == depth) {
            result.add(String.join("", comb));
            return;
        }

        for (String letter : toLetter.get(digits.charAt(depth))) {
            comb.add(letter);
            setCombinations(result, comb, digits, depth + 1, toLetter);
            comb.remove(comb.size() - 1);
        }
    }
}
