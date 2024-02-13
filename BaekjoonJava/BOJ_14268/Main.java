package BOJ_14268;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] edges;
    static int[][] ranges;
    static int seq = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        edges = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        st.nextToken();
        for (int i = 2; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            edges[parent].add(i);
        }

        ranges = new int[n + 1][2];
        setEulerTour(1);

        SegmentTree seg = new SegmentTree(n);
        StringBuilder sb = new StringBuilder();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());

            if (order == 1) {
                int w = Integer.parseInt(st.nextToken());
                seg.update(i, w);
            } else {
                sb.append(seg.query(i)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void setEulerTour(int node) {
        ranges[node][0] = seq++;
        edges[node].forEach(Main::setEulerTour);
        ranges[node][1] = seq - 1;
    }

    static class SegmentTree {

        int n;
        long[] tree;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            tree = new long[n << 2];
            lazy = new long[n << 2];
        }

        void update(int i, int w) {
            rUpdate(1, 0, n - 1, ranges[i][0], ranges[i][1], w);
        }

        void rUpdate(int tIdx, int fB, int fE, int tB, int tE, int w) {
            if (tE < fB || fE < tB) {
                return;
            }

            if (tB <= fB && fE <= tE) {
                lazy[tIdx] += w;
                return;
            }

            int mid = (fB + fE) >> 1;
            rUpdate(tIdx << 1, fB, mid, tB, tE, w);
            rUpdate(tIdx << 1 | 1, mid + 1, fE, tB, tE, w);
        }

        long query(int i) {
            return rQuery(1, 0, n - 1, ranges[i][0]);
        }

        long rQuery(int tIdx, int fB, int fE, int target) {
            propagate(tIdx);

            if (target < fB || fE < target) {
                return 0;
            }

            if (target == fB && fB == fE) {
                return tree[tIdx];
            }

            int mid = (fB + fE) >> 1;

            if (target <= mid) {
                return rQuery(tIdx << 1, fB, mid, target);
            }
            return rQuery(tIdx << 1 | 1, mid + 1, fE, target);
        }

        void propagate(int tIdx) {
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
}
