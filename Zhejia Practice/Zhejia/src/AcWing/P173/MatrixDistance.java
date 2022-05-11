package AcWing.P173;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MatrixDistance {
    static int N;
    static int M;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        M = in.nextInt();
        int[][] A = new int[N + 1][M + 1];
        int[][] B = new int[N + 1][M + 1];
        for (int[] row: B) Arrays.fill(row, -1);
        Queue<Pair> queue = new LinkedList<>();
        in.nextLine();
        for (int i = 1; i <= N; i++) {
            String line = in.nextLine();

            for (int j = 1; j <= M; j++) {
                A[i][j] = line.charAt(j - 1) - '0';
                if (A[i][j] == 1) {
                    B[i][j] = 0;
                    queue.offer(new Pair(i, j));
                }
            }
        }
        while (!queue.isEmpty()) {
            Pair head = queue.poll();
            int currX = head.x_coordinate;
            int currY = head.y_coordinate;

            for (int t = 0; t < 4; t++){
                double angle = t * Math.PI / 2;
                int r = (int) Math.sin(angle);
                int c = (int) Math.cos(angle);
                int newX = currX + r;
                int newY = currY + c;
                if (inMatrix(newX, newY) && B[newX][newY] == -1) {
                    B[newX][newY] = B[currX][currY] + 1;
                    queue.offer(new Pair(newX, newY));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++)
                out.print(B[i][j] + " ");
            out.print("\n");
        }
        out.flush();
    }

    private static boolean inMatrix(int row, int col) {
        return 1 <= row && row <= N && 1 <= col && col <= M;
    }
}

class Pair {
    int x_coordinate;
    int y_coordinate;

    public Pair(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }
}
