package BOJ_14215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] edges = new int[] {
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };
        Arrays.sort(edges);

        int sum = Arrays.stream(edges).sum();

        if (edges[0] + edges[1] <= edges[2]) {
            sum -= edges[2] - edges[0] - edges[1] + 1;
        }

        System.out.println(sum);
    }
}
