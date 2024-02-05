package BOJ_14939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int N = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[N][];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            answer = Math.min(answer, comb(board, 0, i, i));
        }

        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }

    private static int comb(char[][] board, int idx, int remain, int count) {
        if (board.length - idx < remain) {
            return Integer.MAX_VALUE;
        }

        if (remain == 0) {
            int clickCount = simulate(deepCopy(board));

            if (clickCount == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            return count + clickCount;
        }

        click(board, 0, idx);
        int clicked = comb(board, idx + 1, remain - 1, count);

        click(board, 0, idx);
        int nonClicked = comb(board, idx + 1, remain, count);

        return Math.min(clicked, nonClicked);
    }

    private static int simulate(char[][] board) {
        int clickCount = 0;

        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i - 1][j] == 'O') {
                    click(board, i, j);
                    clickCount++;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[board.length - 1][i] == 'O') {
                return Integer.MAX_VALUE;
            }
        }

        return clickCount;
    }

    private static void click(char[][] board, int i, int j) {
        board[i][j] = board[i][j] == '#' ? 'O' : '#';
        if (i > 0) {
            board[i - 1][j] = board[i - 1][j] == '#' ? 'O' : '#';
        }
        if (i + 1 < board.length) {
            board[i + 1][j] = board[i + 1][j] == '#' ? 'O' : '#';
        }
        if (j > 0) {
            board[i][j - 1] = board[i][j - 1] == '#' ? 'O' : '#';
        }
        if (j + 1 < board.length) {
            board[i][j + 1] = board[i][j + 1] == '#' ? 'O' : '#';
        }
    }

    private static char[][] deepCopy(char[][] board) {
        char[][] copied = new char[N][];

        for (int i = 0; i < N; i++) {
            copied[i] = board[i].clone();
        }

        return copied;
    }
}
