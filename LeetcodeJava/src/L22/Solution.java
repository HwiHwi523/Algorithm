package L22;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        List<String> generating = new ArrayList<>(List.of("("));

        generate(result, generating, n << 1, 1, n - 1);

        return result;
    }

    private void generate(List<String> result, List<String> generating, int length, int validStack, int n) {
        if (generating.size() == length) {
            result.add(String.join("", generating));
            return;
        }

        if (n > 0) {
            generating.add("(");
            generate(result, generating, length, validStack + 1, n - 1);
            generating.remove(generating.size() - 1);
        }

        if (validStack > 0) {
            generating.add(")");
            generate(result, generating, length, validStack - 1, n);
            generating.remove(generating.size() - 1);
        }
    }
}
