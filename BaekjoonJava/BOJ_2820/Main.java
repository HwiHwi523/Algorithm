package BOJ_2820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] subs = new ArrayList[n];
        int[] salaries = new int[n];
        inputEmployees(n, subs, salaries, br);

        SegmentTree segment = new SegmentTree(n, subs, salaries);
        StringBuilder result = new StringBuilder();

        while (m-- > 0) {
            String[] s = br.readLine().split(" ");

            if (s[0].equals("p")) {
                segment.update(Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
                continue;
            }

            result.append(segment.query(Integer.parseInt(s[1]) - 1)).append('\n');
        }

        System.out.print(result);
    }

    static class SegmentTree {

        private final int n;
        private final int[] tree;
        private final int[] lazy;
        private final int[] segSalaries;
        private final int[][] ranges;

        public SegmentTree(int n, List<Integer>[] subs, int[] salaries) {
            this.n = n;
            this.tree = new int[n << 2];
            this.lazy = new int[n << 2];
            this.segSalaries = new int[n];
            this.ranges = new int[n][2];

            setEulerTour(subs, 0, salaries, 0);
            initTree(1, 0, n - 1);
        }

        private int setEulerTour(List<Integer>[] subs, int idx, int[] salaries, int seq) {
            segSalaries[seq] = salaries[idx];
            ranges[idx][0] = seq;

            for (int next : subs[idx]) {
                seq = setEulerTour(subs, next, salaries, seq + 1);
            }

            return ranges[idx][1] = seq;
        }

        private void initTree(int tIdx, int begin, int end) {
            if (begin == end) {
                tree[tIdx] = segSalaries[begin];
                return;
            }

            int mid = (begin + end) >> 1;
            initTree(tIdx << 1, begin, mid);
            initTree(tIdx << 1 | 1, mid + 1, end);
        }

        public void update(int employee, int salary) {
            if (ranges[employee][0] == ranges[employee][1]) {
                return;
            }
            rUpdate(1, 0, n - 1, ranges[employee][0] + 1, ranges[employee][1], salary);
        }

        private void rUpdate(int tIdx, int fBegin, int fEnd, int tBegin, int tEnd, int salary) {
            if (tEnd < fBegin || fEnd < tBegin) {
                return;
            }

            if (tBegin <= fBegin && fEnd <= tEnd) {
                this.lazy[tIdx] += salary;
                return;
            }

            int mid = (fBegin + fEnd) >> 1;
            rUpdate(tIdx << 1, fBegin, mid, tBegin, tEnd, salary);
            rUpdate(tIdx << 1 | 1, mid + 1, fEnd, tBegin, tEnd, salary);
        }

        public int query(int employee) {
            return rQuery(1, 0, n - 1, ranges[employee][0]);
        }

        private int rQuery(int tIdx, int fBegin, int fEnd, int target) {
            propagate(tIdx);

            if (target < fBegin || fEnd < target) {
                return 0;
            }

            if (target == fBegin && fBegin == fEnd) {
                return tree[tIdx];
            }

            int mid = (fBegin + fEnd) >> 1;
            if (target <= mid) {
                return rQuery(tIdx << 1, fBegin, mid, target);
            }
            return rQuery(tIdx << 1 | 1, mid + 1, fEnd, target);
        }

        private void propagate(int tIdx) {
            if (lazy[tIdx] == 0) {
                return;
            }

            tree[tIdx] += lazy[tIdx];

            int left = tIdx << 1;
            if (left < lazy.length) {
                lazy[left] += lazy[tIdx];
            }

            int right = left + 1;
            if (right < lazy.length) {
                lazy[right] += lazy[tIdx];
            }

            lazy[tIdx] = 0;
        }
    }

    static void inputEmployees(int n, List<Integer>[] subs, int[] salaries, BufferedReader br)
            throws IOException {
        for (int i = 0; i < subs.length; i++) {
            subs[i] = new ArrayList<>();
        }

        salaries[0] = Integer.parseInt(br.readLine());
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int salary = Integer.parseInt(st.nextToken());
            int boss = Integer.parseInt(st.nextToken()) - 1;

            subs[boss].add(i);
            salaries[i] = salary;
        }
    }
}
