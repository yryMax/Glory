package AcWing.P344;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SightseeingTrip {
    static final Long LIMIT = Long.MAX_VALUE / 3 - 1000;
    static int N;
    static int M;
    static long[][] adjMatrix;
    static long[][] distances;
    static int[][] positions;
    static long ans;
    static List<Integer> path;

    public static void getPath(int from, int to) {
        if (positions[from][to] == 0) return;
        getPath(from, positions[from][to]);
        path.add(positions[from][to]);
        getPath(positions[from][to], to);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        M = in.nextInt();
        adjMatrix = new long[N + 1][N + 1];
        distances = new long[N + 1][N + 1];
        positions = new int[N + 1][N + 1];
        ans = LIMIT;
        path = new ArrayList<>();

        for (long[] row: adjMatrix) Arrays.fill(row, LIMIT);
        for (long[] row: distances) Arrays.fill(row, LIMIT);
        for (int i = 1; i <= N; i++) {
            adjMatrix[i][i] = 0;
            distances[i][i] = 0;
        }
        for (int i = 1; i <= M; i++) {
            int ver1 = in.nextInt();
            int ver2 = in.nextInt();
            int currLen = in.nextInt();
            adjMatrix[ver1][ver2] = Math.min(adjMatrix[ver1][ver2], currLen);
            adjMatrix[ver2][ver1] = Math.min(adjMatrix[ver2][ver1], currLen);
            distances[ver1][ver2] = adjMatrix[ver1][ver2];
            distances[ver2][ver1] = adjMatrix[ver2][ver1];
        }

        for (int keyVer = 1; keyVer <= N; keyVer++) {
            for (int x_ver = 1; x_ver < keyVer; x_ver++) {
                for (int y_ver = x_ver + 1; y_ver < keyVer; y_ver++) {
                    long lenNewLoop = distances[x_ver][y_ver] + adjMatrix[x_ver][keyVer] + adjMatrix[keyVer][y_ver];
                    if (lenNewLoop < ans) {
                        ans = lenNewLoop;
                        path = new ArrayList<>();
                        path.add(x_ver);
                        getPath(x_ver, y_ver);
                        path.add(y_ver);
                        path.add(keyVer);
                    }
                }
            }

            for (int x_ver = 1; x_ver <= N; x_ver++) {
                for (int y_ver = 1; y_ver <= N; y_ver++) {
                    if (distances[x_ver][y_ver] > distances[x_ver][keyVer] + distances[keyVer][y_ver]) {
                        distances[x_ver][y_ver] = distances[x_ver][keyVer] + distances[keyVer][y_ver];
                        positions[x_ver][y_ver] = keyVer;
                    }
                }
            }
        }
        if (ans == LIMIT) {
            System.out.println("No solution.");
            return;
        }
        for (int i = 0; i < path.size(); i++)
            out.print(path.get(i) + " ");
        out.flush();

    }
}
