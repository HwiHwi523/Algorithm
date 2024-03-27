package BOJ_1849;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        FenwickTree tree = new FenwickTree(n);

        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine());

            int idx = tree.findIdx(val);
            seq[idx] = i + 1;

            tree.update(idx + 1, -1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(seq[i]).append('\n');
        }
        System.out.print(sb);
    }

    static class FenwickTree {

        int[] tree;

        FenwickTree(int n) {
            this.tree = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                update(i, 1);
            }
        }

        void update(int idx, int amount) {
            while (idx < tree.length) {
                tree[idx] += amount;
                idx += idx & -idx;
            }
        }

        int findIdx(int value) {
            int begin = 1;
            int end = tree.length - 1;
            value++;

            while (begin <= end) {
                int mid = (begin + end) >> 1;
                int count = getCount(mid);
                int curValue = getCount(mid) - getCount(mid - 1);

                if (count == value && curValue == 1) {
                    return mid - 1;
                }

                if (count < value) {
                    begin = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            return -1;
        }

        int getCount(int idx) {
            int result = 0;

            while (idx > 0) {
                result += tree[idx];
                idx -= idx & -idx;
            }

            return result;
        }
    }
}
