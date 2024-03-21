package BOJ_5073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            Triangle triangle = new Triangle(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            if (triangle.isEnd()) {
                break;
            }

            sb.append(triangle.getType()).append('\n');
        }

        System.out.println(sb);
    }

    static class Triangle {

        int[] edges;

        public Triangle(int a, int b, int c) {
            edges = new int[]{a, b, c};
            Arrays.sort(edges);
        }

        String getType() {
            if (isNotTriangle()) {
                return "Invalid";
            }

            switch (Arrays.stream(edges).boxed().collect(Collectors.toSet()).size()) {
                case 1:
                    return "Equilateral";

                case 2:
                    return "Isosceles";

                case 3:
                    return "Scalene";

                default:
                    return "";
            }
        }

        boolean isNotTriangle() {
            return edges[0] + edges[1] <= edges[2];
        }

        boolean isEnd() {
            return edges[2] == 0;
        }
    }
}
