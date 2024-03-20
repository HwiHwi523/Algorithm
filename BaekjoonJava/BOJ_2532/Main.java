package BOJ_2532;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();

        int n = fr.nextInt();

        Set<Section> uniqSections = new HashSet<>();
        for (int i = 0; i < n; i++) {
            fr.nextInt();
            int begin = fr.nextInt();
            int end = fr.nextInt();

            uniqSections.add(new Section(begin, end));
        }

        List<Section> sections = new ArrayList<>(uniqSections);
        sections.sort((o1, o2) -> {
            if (o1.begin == o2.begin) {
                return o2.end - o1.end;
            }
            return o1.begin - o2.begin;
        });

        List<Section> lis = new ArrayList<>();
        for (Section section : sections) {
            if (lis.isEmpty() || lis.get(lis.size() - 1).end >= section.end) {
                lis.add(section);
                continue;
            }
            lis.set(findIndex(lis, section.end), section);
        }

        System.out.println(lis.size());
    }

    static int findIndex(List<Section> lis, int target) {
        int index = 0;
        int begin = 0;
        int end = lis.size() - 1;

        while (begin <= end) {
            int mid = (begin + end) >> 1;

            if (lis.get(mid).end >= target) {
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

        public Section(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Section section = (Section) o;

            if (begin != section.begin) {
                return false;
            }
            return end == section.end;
        }

        @Override
        public int hashCode() {
            int result = begin;
            result = 31 * result + end;
            return result;
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
