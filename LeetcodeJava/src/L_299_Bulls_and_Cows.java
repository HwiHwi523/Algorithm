import java.util.HashMap;
import java.util.Map;

public class L_299_Bulls_and_Cows {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.getHint("1807", "7810"));

        // 2
        System.out.println(solution.getHint("1123", "0111"));
    }

    static class Solution {
        public String getHint(String secret, String guess) {
            int x = 0;
            int y = 0;

            Map<Character, Integer> secretMap = new HashMap<>();
            Map<Character, Integer> guessMap = new HashMap<>();
            for (int i = 0; i < secret.length(); i++) {
                // 일치할 경우 x 증가
                if (secret.charAt(i) == guess.charAt(i))
                    x++;
                else {
                    // 정답 문자 빈도 갱신
                    if (!secretMap.containsKey(secret.charAt(i)))
                        secretMap.put(secret.charAt(i), 0);
                    secretMap.put(secret.charAt(i), secretMap.get(secret.charAt(i)) + 1);

                    // 추측 문자 빈도 갱신
                    if (!guessMap.containsKey(guess.charAt(i)))
                        guessMap.put(guess.charAt(i), 0);
                    guessMap.put(guess.charAt(i), guessMap.get(guess.charAt(i)) + 1);
                }
            }

            // 추측 문자에 대해서 정답 문자와 교집합 개수 구하기
            for (Character guessChar : guessMap.keySet()) {
                // 추측 문자가 정답 문자에 없을 경우 건너뛰기
                if (!secretMap.containsKey(guessChar))
                    continue;
                // y 갱신
                y += Math.min(
                        guessMap.get(guessChar),
                        secretMap.get(guessChar)
                );
            }

            return x + "A" + y + "B";
        }
    }
}
