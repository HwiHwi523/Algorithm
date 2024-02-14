package BOJ_1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        List<Integer> lGroup = new ArrayList<>();
        List<Integer> rGroup = new ArrayList<>();
        int lMax = 0, rMax = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if ((nums[i] & 1) == 1) {
                lGroup.add(nums[i]);
                lMax = Math.max(lMax, nums[i]);
            } else {
                rGroup.add(nums[i]);
                rMax = Math.max(rMax, nums[i]);
            }
        }

        if (lGroup.size() != rGroup.size()) {
            System.out.println(-1);
            return;
        }

        if (rGroup.get(0) == nums[0]) {
            rGroup = swap(lGroup, lGroup = rGroup);
            rMax = swap(lMax, lMax = rMax);
        }

        boolean[] isPrime = getIsPrime(lMax + rMax);
        List<Integer>[] graph = new List[lGroup.size()];
        for (int i = 0; i < lGroup.size(); i++) {
            graph[i] = new ArrayList<>();
            for (int rVal : rGroup) {
                if (isPrime[lGroup.get(i) + rVal]) {
                    graph[i].add(rVal);
                }
            }
        }
        Collections.sort(graph[0]);

        StringBuilder sb = new StringBuilder();
        for (int withFirst : graph[0]) {
            int matchingCount = 1;

            int[] matched = new int[rMax + 1];
            for (int i = 1; i < lGroup.size(); i++) {
                boolean[] done = new boolean[rMax + 1];
                done[withFirst] = true;
                if (matching(graph, i, done, matched)) {
                    matchingCount++;
                }
            }

            if (matchingCount == n >> 1) {
                sb.append(withFirst).append(' ');
            }
        }

        System.out.println(sb.toString().isEmpty() ? -1 : sb);
    }

    static <T> T swap(T o1, T o2) {
        return o1;
    }

    static boolean[] getIsPrime(int size) {
        final int SQ = (int) Math.sqrt(size) + 1;

        boolean[] isPrime = new boolean[size + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= SQ; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i + i; j <= size; j += i) {
                isPrime[j] = false;
            }
        }

        return isPrime;
    }

    static boolean matching(List<Integer>[] graph, int node, boolean[] done, int[] matched) {
        for (int rVal : graph[node]) {
            if (done[rVal]) {
                continue;
            }
            done[rVal] = true;

            if (matched[rVal] == 0 || matching(graph, matched[rVal], done, matched)) {
                matched[rVal] = node;
                return true;
            }
        }

        return false;
    }
}
