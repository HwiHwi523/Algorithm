import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class L_2390_Removing_Stars_From_a_String {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Solution solution = new Solution();
        System.out.println(solution.removeStars(s));
    }
}

class Solution {
    public String removeStars(String s) {
        List<Character> stack = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (ch == '*')
                stack.remove(stack.size() - 1);
            else
                stack.add(ch);
        }

        StringBuilder answer = new StringBuilder();
        for (char ch : stack)
            answer.append(ch);
        return answer.toString();
    }
}