package BOJ_2995;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();

        int n = fr.nextInt();

        Section[] sections = new Section[n];
        for (int i = 0; i < n; i++) {
            sections[i] = new Section(
                    fr.nextInt(),
                    fr.nextInt()
            );
        }

        Arrays.sort(sections, (o1, o2) -> {
            if (o1.begin == o2.begin) {
                return o2.end - o1.end;
            }
            return o1.begin - o2.begin;
        });

        for (int i = 0; i < n; i++) {
            sections[i].index = i;
        }

        List<Section>[] history = new List[n];
        for (int i = 0; i < n; i++) {
            history[i] = new ArrayList<>();
        }

        List<Section> lis = new ArrayList<>();
        for (Section section : sections) {
            if (lis.isEmpty() || lis.get(lis.size() - 1).end >= section.end) {
                history[lis.size()].add(section);
                lis.add(section);
                continue;
            }
            int index = findIndex(lis, section.end);
            history[index].add(section);
            lis.set(index, section);
        }

        Section[] result = new Section[lis.size()];
        int index = n;

        for (int i = lis.size() - 1; i >= 0; i--) {
            Section section = null;

            for (int j = history[i].size() - 1; j >= 0; j--) {
                if (history[i].get(j).index < index) {
                    section = history[i].get(j);
                    break;
                }
            }

            result[i] = section;
            index = section.index;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.length).append('\n');
        for (Section section : result) {
            if (section == null) {
                section = new Section(-1, -1);
            }
            sb.append(section.begin).append(' ').append(section.end).append('\n');
        }

        System.out.println(sb);
    }

    static int findIndex(List<Section> lis, int target) {
        int index = 0;
        int begin = 0;
        int end = lis.size() - 1;

        while (begin <= end) {
            int mid = (begin + end) >> 1;

            if (target <= lis.get(mid).end) {
                begin = mid + 1;
            } else {
                end = mid - 1;
                index = mid;
            }
        }

        return index;
    }

    static class Section {

        int begin;
        int end;
        int index;

        public Section(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
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
