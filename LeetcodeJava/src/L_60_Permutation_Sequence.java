public class L_60_Permutation_Sequence {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.getPermutation(3, 3));

        // 2
        System.out.println(solution.getPermutation(4, 9));

        // 3
        System.out.println(solution.getPermutation(1, 1));
    }

    static class Solution {
        public String getPermutation(int n, int k) {
            int fact = getFactorial(n);
            return rGetPermutation(1, fact, n, k, fact, 0);
        }

        // 팩토리얼 구하기
        private int getFactorial(int n) {
            int fact = 1;
            for (int i = 2; i <= n; i++)
                fact *= i;
            return fact;
        }

        // 재귀 방식으로 K번째 순열의 원소 구하기
        private String rGetPermutation(int begin, int end, int n, int k, int fact, int visit) {
            if (n == 0)
                return "";

            int unit = fact / n;  // k가 속할 수 있는 각 그룹의 범위

            // num : 각 그룹의 시작점
            // count : 각 그룹의 순서 (1 ~)
            for (int num = begin, count = 1; num <= end; num += unit, count++) {
                // k가 현재 그룹에 속한다면,
                if (num <= k && k < num + unit) {
                    // 1 ~ n 중에서 할당될 수 있는 수 찾기, 비트마스킹 사용
                    // (아직 할당되지 않은 그룹에서 count 번째 그룹 찾기)
                    int bit = 0;  // {bit + 1}은 1 ~ n 중 이번에 할당될 수를 의미
                    while (count > 0) {
                        if ((visit & (1 << bit)) == 0)
                            count--;
                        bit++;
                    }

                    // 이번에 할당될 수를 붙이고 뒤이어 다른 수 찾기
                    return bit + rGetPermutation(num, num + unit, n - 1, k, unit, visit | (1 << bit - 1));
                }
            }

            return "";
        }
    }
}
