package BOJ_1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Long> count1 = new HashMap<>();
        counting(count1, nums, 0, n >> 1, 0);
        count1.put(0, count1.get(0) - 1);
        if (count1.get(0) == 0L) {
            count1.remove(0);
        }

        Map<Integer, Long> count2 = new HashMap<>();
        counting(count2, nums, n >> 1, n, 0);
        count2.put(0, count2.get(0) - 1);
        if (count2.get(0) == 0L) {
            count2.remove(0);
        }

        long result = count2.getOrDefault(s, 0L);

        for (Entry<Integer, Long> entry : count1.entrySet()) {
            int anotherValue = s - entry.getKey();

            result += entry.getValue() * count2.getOrDefault(anotherValue, 0L);
            result += entry.getKey() == s ? entry.getValue() : 0;
        }

        System.out.println(result);
    }

    static void counting(Map<Integer, Long> count, int[] nums, int idx, int end, int sum) {
        if (idx == end) {
            count.put(sum, count.getOrDefault(sum, 0L) + 1);
            return;
        }

        counting(count, nums, idx + 1, end, sum);
        counting(count, nums, idx + 1, end, sum + nums[idx]);
    }
}
