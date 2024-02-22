package BOJ_3197;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Queue<Point> bfsQ = new ArrayDeque<>();
        boolean[][] visit = new boolean[r][c];
        Point[] birds = new Point[2];
        int birdIdx = 0;

        char[][] board = new char[r][];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (board[i][j] != 'X') {
                    // 빈 공간 큐에 넣기
                    bfsQ.add(new Point(i, j));
                    visit[i][j] = true;

                    // 백조 위치 저장
                    if (board[i][j] == 'L') {
                        birds[birdIdx++] = new Point(i, j);
                    }
                }
            }
        }

        int[][] parents = getParents(r, c);
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 큐 비우면서 초기 집합 설정 및 X 위치 추가
        for (int i = bfsQ.size(); i > 0; i--) {
            Point front = bfsQ.poll();

            for (int direc = 0; direc < 4; direc++) {
                int nextR = front.x + dr[direc];
                int nextC = front.y + dc[direc];

                if (nextR < 0 || r <= nextR || nextC < 0 || c <= nextC) {
                    continue;
                }

                if (board[nextR][nextC] != 'X') {
                    union(parents, front.x, front.y, nextR, nextC);
                }

                if (!visit[nextR][nextC]) {
                    bfsQ.add(new Point(nextR, nextC));
                    visit[nextR][nextC] = true;
                }
            }
        }

        int day = 0;
        while (!bfsQ.isEmpty()) {
            if (isReachable(parents, birds)) {
                break;
            }

            day++;

            int qSize = bfsQ.size();
            while (qSize-- > 0) {
                Point front = bfsQ.poll();

                board[front.x][front.y] = '.';

                for (int direc = 0; direc < 4; direc++) {
                    int nextR = front.x + dr[direc];
                    int nextC = front.y + dc[direc];

                    if (nextR < 0 || r <= nextR || nextC < 0 || c <= nextC) {
                        continue;
                    }

                    if (board[nextR][nextC] != 'X') {
                        union(parents, front.x, front.y, nextR, nextC);
                    }

                    if (!visit[nextR][nextC]) {
                        bfsQ.add(new Point(nextR, nextC));
                        visit[nextR][nextC] = true;
                    }
                }
            }
        }

        System.out.println(day);
    }

    static int[][] getParents(int r, int c) {
        int[][] parents = new int[r][c];
        int parentIdx = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                parents[i][j] = parentIdx++;
            }
        }

        return parents;
    }

    static int findParent(int[][] parents, int r, int c) {
        if (parents[r][c] == r * parents[0].length + c) {
            return parents[r][c];
        }
        return parents[r][c] = findParent(
                parents,
                parents[r][c] / parents[0].length,
                parents[r][c] % parents[0].length
        );
    }

    static void union(int[][] parents, int r1, int c1, int r2, int c2) {
        int parent1 = findParent(parents, r1, c1);
        int parent2 = findParent(parents, r2, c2);

        if (parent1 == parent2) {
            return;
        }

        parents[parent1 / parents[0].length][parent1 % parents[0].length] = parent2;
    }

    static boolean isReachable(int[][] parents, Point[] birds) {
        return findParent(parents, birds[0].x, birds[0].y)
                == findParent(parents, birds[1].x, birds[1].y);
    }
}
