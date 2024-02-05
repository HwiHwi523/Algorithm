package BOJ_2295;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        Set<Integer> sums = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = nums[i] + nums[j];

                if (sum >= 200_000_000) {
                    continue;
                }

                sums.add(sum);
            }
        }

        for (int kNum = n - 1; kNum > 0; kNum--) {
            for (int cNum = kNum - 1; cNum >= 0; cNum--) {
                if (sums.contains(nums[kNum] - nums[cNum])) {
                    System.out.println(nums[kNum]);
                    return;
                }
            }
        }
    }
}
