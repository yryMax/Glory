package AtCoder.ABC.ABC189.ProblemE;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class RotateAndFlip {
    static int N;
    static long[][] points;
    static int M;
    static int[][] operations;
    static int Q;
    static int[][] queries;
    static long[][][] states;

    private static void init() {
        states = new long[M + 1][2][3];
        states[0] = new long[][] {{1, 0, 0}, {0, 1, 0}};
        for (int i = 1; i <= M; i++) {
            long[] newX;
            long[] newY;
            long p;
            switch (operations[i][0]) {
                case 1:
                    newX = Arrays.copyOf(states[i - 1][1], 3);
                    newY = Arrays.copyOf(states[i - 1][0], 3);
                    for (int j = 0; j < 3; j++)
                        newY[j] = -newY[j];
                    states[i] = new long[][]{newX, newY};
                    break;
                case 2:
                    newX = Arrays.copyOf(states[i - 1][1], 3);
                    newY = Arrays.copyOf(states[i - 1][0], 3);
                    for (int j = 0; j < 3; j++)
                        newX[j] = -newX[j];
                    states[i] = new long[][]{newX, newY};
                    break;
                case 3:
                    p = operations[i][1];
                    newX = Arrays.copyOf(states[i - 1][0], 3);
                    newY = Arrays.copyOf(states[i - 1][1], 3);
                    newX[0] = -newX[0];
                    newX[1] = -newX[1];
                    newX[2] = 2L * p - newX[2];
                    states[i] = new long[][] {newX, newY};
                    break;
                case 4:
                    p = operations[i][1];
                    newX = Arrays.copyOf(states[i - 1][0], 3);
                    newY = Arrays.copyOf(states[i - 1][1], 3);
                    newY[0] = -newY[0];
                    newY[1] = -newY[1];
                    newY[2] = 2L * p - newY[2];
                    states[i] = new long[][] {newX, newY};
                    break;
            }
        }
    }

    private static String calculate(int time, int point) {
        long x_coordinate = states[time][0][0] * points[point][0] + states[time][0][1] * points[point][1] + states[time][0][2];
        long y_coordinate = states[time][1][0] * points[point][0] + states[time][1][1] * points[point][1] + states[time][1][2];
        return x_coordinate + " " + y_coordinate;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        points = new long[N + 1][2];
        for (int i = 1; i <= N; i++) {
            points[i][0] = in.nextLong();
            points[i][1] = in.nextLong();
        }
        M = in.nextInt();
        operations = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            operations[i][0] = in.nextInt();
            operations[i][1] = (operations[i][0] <= 2) ? 0 : in.nextInt();
        }

        Q = in.nextInt();
        queries = new int[Q][2];

        for (int i = 0; i < Q; i++) {
            queries[i][0] = in.nextInt();
            queries[i][1] = in.nextInt();
        }

        init();

        for (int i = 0; i < Q; i++) {
            int time = queries[i][0];
            int point = queries[i][1];
            out.println(calculate(time, point));
        }
        out.flush();
    }
}
