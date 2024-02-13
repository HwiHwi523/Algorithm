package BOJ_14897;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> indexes = new HashMap<>();
        int newIdx = 0;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());

            if (!indexes.containsKey(val)) {
                indexes.put(val, newIdx++);
            }

            nums[i] = indexes.get(val);
        }

        int m = Integer.parseInt(br.readLine());
        int[][] queries = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][1] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][2] = i;
        }
        Arrays.sort(queries, (o1, o2) -> {
            int sq1 = (int) Math.sqrt(o1[0]);
            int sq2 = (int) Math.sqrt(o2[0]);

            if (sq1 == sq2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }

            return o1[0] - o2[0];
        });

        int[] result = new int[m];
        int[] counts = new int[newIdx];
        int curCount = 0;
        int left = 0, right = -1;
        for (int i = 0; i < m; i++) {
            while (right < queries[i][1]) {
                if (++counts[nums[++right]] == 1) {
                    curCount++;
                }
            }

            while (queries[i][1] < right) {
                if (--counts[nums[right--]] == 0) {
                    curCount--;
                }
            }

            while (queries[i][0] < left) {
                if (++counts[nums[--left]] == 1) {
                    curCount++;
                }
            }

            while (left < queries[i][0]) {
                if (--counts[nums[left++]] == 0) {
                    curCount--;
                }
            }

            result[queries[i][2]] = curCount;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(result[i]).append('\n');
        }
        System.out.print(sb);
    }
}
