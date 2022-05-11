package AcWing.P345;

import java.util.Arrays;
import java.util.Scanner;

public class CowRelays {
    static final long LIMIT = Long.MAX_VALUE - 1000000;
    static int N;
    static int T;
    static int S;
    static int E;
    static int[][] edges;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        T = in.nextInt();
        S = in.nextInt();
        E = in.nextInt();
        edges = new int[T + 1][3];
        int verMax = Integer.MIN_VALUE;
        for (int i = 1; i <= T; i++) {
            edges[i][2] = in.nextInt();
            edges[i][0] = in.nextInt();
            verMax = Math.max(verMax, edges[i][0]);
            edges[i][1] = in.nextInt();
            verMax = Math.max(verMax, edges[i][1]);
        }
        long[] distances = new long[verMax + 1];
        long[] previous = new long[verMax + 1];
        Arrays.fill(previous, LIMIT);
        Arrays.fill(distances, LIMIT);
        distances[S] = 0;
        previous[S] = 0;

        for (int pass = 1; pass <= N; pass++) {
            System.arraycopy(distances, 0, previous, 0, verMax + 1);
            Arrays.fill(distances, Long.MAX_VALUE);
            for (int e = 1; e <= T; e++) {
                distances[edges[e][1]] = Math.min(distances[edges[e][1]], previous[edges[e][0]] + edges[e][2]);
                distances[edges[e][0]] = Math.min(distances[edges[e][0]], previous[edges[e][1]] + edges[e][2]);
            }
        }
        System.out.println(distances[E]);
    }
}
