package BOJ_22963;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();

        int n = fr.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = fr.nextInt();
        }

        List<Integer> lis = new ArrayList<>();
        List<List<Integer>> history = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) <= nums[i]) {
                lis.add(nums[i]);
                history.add(new ArrayList<>());
                history.get(history.size() - 1).add(i);
                continue;
            }

            int index = findIndex(lis, nums[i]);
            lis.set(index, nums[i]);
            history.get(index).add(i);
        }

        if (n - 3 > lis.size()) {
            System.out.println("NO");
            return;
        }

        List<Integer> reversed = new ArrayList<>(lis.size());
        int index = history.size() - 1;
        int maxIndex = n;

        while (index >= 0) {
            List<Integer> curHistory = history.get(index);
            for (int i = curHistory.size() - 1; i >= 0; i--) {
                if (curHistory.get(i) < maxIndex) {
                    maxIndex = curHistory.get(i);
                    reversed.add(nums[maxIndex]);
                    break;
                }
            }
            index--;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("YES\n");
        sb.append(n - lis.size()).append('\n');

        for (int i = 0; i < n; i++) {
            if (!reversed.isEmpty() && reversed.get(reversed.size() - 1) == nums[i]) {
                reversed.remove(reversed.size() - 1);
                continue;
            }

            nums[i] = i > 0 ? nums[i - 1] : 1;
            sb.append(i + 1).append(' ').append(nums[i]).append('\n');
        }

        System.out.print(sb);
    }

    static int findIndex(List<Integer> lis, int target) {
        int index = 0;
        int begin = 0;
        int end = lis.size() - 1;

        while (begin <= end) {
            int mid = (begin + end) >> 1;

            if (target < lis.get(mid)) {
                end = mid - 1;
                index = mid;
            } else {
                begin = mid + 1;
            }
        }

        return index;
    }

    static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 16;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = (ret << 3) + (ret << 1) + (c & 15);
            } while ((c = read()) > 32);

            return neg ? ~ret + 1 : ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
    }
}
