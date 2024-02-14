package BOJ_1289;

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

        List<Integer>[] pred = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            pred[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pred[a].add(b);
        }

        int[] matched = new int[n + 1];
        int[] done = new int[n + 1];
        int doneFlag = 1;
        int result = 0;

        for (int i = 1; i <= n; i++) {
            result += matching(pred, i, matched, done, doneFlag++);
        }

        System.out.println(result);
    }

    static byte matching(List<Integer>[] pred, int node, int[] matched, int[] done, int doneFlag) {
        for (int laptop : pred[node]) {
            if (done[laptop] == doneFlag) {
                continue;
            }
            done[laptop] = doneFlag;

            if (matched[laptop] == 0 || matching(pred, matched[laptop], matched, done, doneFlag) == 1) {
                matched[laptop] = node;
                return 1;
            }
        }

        return 0;
    }
}
