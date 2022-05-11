package AcWing.P340;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TelephoneLine {
    static int N;
    static int K;
    static int P;
    static int[] heads;
    static int[] ver;
    static int[] edges;
    static int[] next;
    static int tot;
    static PriorityQueue<Tuple> queue;
    static long[][] dp;
    static boolean[][] inGraph;


    private static void add(int x, int y, int z) {
        ver[++tot] = y; edges[tot] = z;
        next[tot] = heads[x]; heads[x] = tot;
        ver[++tot] = x; edges[tot] = z;
        next[tot] = heads[y]; heads[y] = tot;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        P = in.nextInt();
        K = in.nextInt();
        heads = new int[N + 1];
        ver = new int[2 * P + 2];
        edges = new int[2 * P + 2];
        next = new int[2 * P + 2];
        tot = 1;
        for (int i = 1; i <= P; i++) add(in.nextInt(), in.nextInt(), in.nextInt());
        queue = new PriorityQueue<>();
        dp = new long[N + 1][K + 1];
        inGraph = new boolean[N + 1][K + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
        dp[1][0] = 0L;
        inGraph[1][0] = true;
        queue.offer(new Tuple(0, 1, 0));
        while (!queue.isEmpty()) {
            Tuple out = queue.poll();
            long currDis = out.distance;
            int currVer = out.vertex;
            int currLevel = out.level;
            if (!inGraph[currVer][currLevel]) continue;
            inGraph[currVer][currLevel] = false;
            for (int i = heads[currVer]; i > 0; i = next[i]) {
                int neighbour = ver[i];
                int edge = edges[i];
                if (Math.max(edge, currDis) < dp[neighbour][currLevel]) {
                    dp[neighbour][currLevel] = Math.max(edge, currDis);
                    queue.offer(new Tuple(dp[neighbour][currLevel], neighbour, currLevel));
                    inGraph[neighbour][currLevel] = true;
                }

                if (currLevel + 1 <= K && currDis < dp[neighbour][currLevel + 1]) {
                    dp[neighbour][currLevel + 1] = currDis;
                    queue.offer(new Tuple(dp[neighbour][currLevel + 1], neighbour, currLevel + 1));
                    inGraph[neighbour][currLevel + 1] = true;
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) ans = Math.min(ans, dp[N][i]);
        System.out.println((ans == Long.MAX_VALUE) ? -1 : ans);
    }
}

class Tuple implements Comparable<Tuple> {
    long distance;
    int vertex;
    int level;

    public Tuple(long distance, int vertex, int level) {
        this.distance = distance;
        this.vertex = vertex;
        this.level = level;
    }

    @Override
    public int compareTo(Tuple tuple) {
        return Long.compare(distance, tuple.distance);
    }


}

