package BOJ_2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());

        int[][] points = new int[6][];
        int r = 0, c = 0;
        int minR = 500, maxR = -500;
        int minC = 500, maxC = -500;

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            switch (direction) {
                case 1:
                    c += amount;
                    break;

                case 2:
                    c -= amount;
                    break;

                case 3:
                    r += amount;
                    break;

                case 4:
                    r -= amount;
                    break;
            }

            points[i] = new int[]{r, c};
            minR = Math.min(minR, r);
            maxR = Math.max(maxR, r);
            minC = Math.min(minC, c);
            maxC = Math.max(maxC, c);
        }

        int totalArea = getArea(new int[]{minR, minC}, new int[]{maxR, maxC});

        int[] midPoint = new int[2];
        for (int[] point : points) {
            if (minR < point[0] && point[0] < maxR && minC < point[1] && point[1] < maxC) {
                midPoint = point;
                break;
            }
        }

        int[][] emptyPoints = new int[2][];
        int idx = 0;
        for (int[] point : points) {
            if (point == midPoint) {
                continue;
            }

            if (point[0] == midPoint[0] || point[1] == midPoint[1]) {
                emptyPoints[idx++] = point;
            }
        }

        System.out.println(k * (totalArea - getArea(emptyPoints[0], emptyPoints[1])));
    }

    static int getArea(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]);
    }
}
