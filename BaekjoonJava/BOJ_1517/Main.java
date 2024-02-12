package BOJ_1517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        MergeSortTree mergeSortTree = new MergeSortTree(n, nums);

        long result = 0;

        for (int i = 1; i < n; i++) {
            result += mergeSortTree.query(i, nums[i]);
        }

        System.out.println(result);
    }

    static class MergeSortTree {

        int n;
        List<Integer>[] tree;

        public MergeSortTree(int n, int[] nums) {
            this.n = n;
            initTree(nums);
        }

        void initTree(int[] nums) {
            tree = new ArrayList[n << 1];
            for (int i = 1; i < tree.length; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < nums.length; i++) {
                tree[i + n].add(nums[i]);
            }

            for (int i = n - 1; i > 0; i--) {
                List<Integer> lNums = tree[i << 1];
                List<Integer> rNums = tree[i << 1 | 1];
                int lIdx = 0, rIdx = 0;

                while (lIdx < lNums.size() && rIdx < rNums.size()) {
                    if (lNums.get(lIdx) <= rNums.get(rIdx)) {
                        tree[i].add(lNums.get(lIdx++));
                    } else {
                        tree[i].add(rNums.get(rIdx++));
                    }
                }

                while (lIdx < lNums.size()) {
                    tree[i].add(lNums.get(lIdx++));
                }

                while (rIdx < rNums.size()) {
                    tree[i].add(rNums.get(rIdx++));
                }
            }
        }

        long query(int to, int std) {
            int left = n;
            int right = to + n;
            long result = 0;

            while (left < right) {
                if ((left & 1) != 0) {
                    result += getCount(tree[left++], std);
                }

                if ((right & 1) != 0) {
                    result += getCount(tree[--right], std);
                }

                left >>= 1;
                right >>= 1;
            }

            return result;
        }

        int getCount(List<Integer> nums, int std) {
            int idx = nums.size();
            int begin = 0;
            int end = nums.size() - 1;

            while (begin <= end) {
                int mid = (begin + end) >> 1;

                if (std < nums.get(mid)) {
                    idx = mid;
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            }

            return nums.size() - idx;
        }
    }
}
