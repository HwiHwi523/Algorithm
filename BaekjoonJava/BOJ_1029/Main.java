package BOJ_1029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n][n];
        int[][][] memo = new int[10][n][1 << n];

        for (int i = 0; i < n; i++) {
            int j = 0;
            for (char ch : br.readLine().toCharArray()) {
                costs[i][j++] = ch - '0';
            }
        }

        System.out.println(getMaxCustomer(costs, 0, 0, 0, memo));
    }

    static int getMaxCustomer(int[][] costs, int idx, int curCost, int visit, int[][][] memo) {
        visit |= 1 << idx;

        if (memo[curCost][idx][visit] > 0) {
            return memo[curCost][idx][visit];
        }

        int maxCustomer = memo[curCost][idx][visit];

        for (int i = 0; i < costs.length; i++) {
            int bit = 1 << i;

            if ((visit & bit) != 0) {
                continue;
            }

            if (costs[idx][i] < curCost) {
                continue;
            }

            maxCustomer = Math.max(
                    maxCustomer,
                    getMaxCustomer(costs, i, costs[idx][i], visit, memo)
            );
        }

        return memo[curCost][idx][visit] = maxCustomer + 1;
    }
}
