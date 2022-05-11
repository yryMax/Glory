package AtCoder.ABC.ABC190.ProblemE;

import java.util.*;

public class MagicalOrnament {
    static int N;
    static int M;
    static int[] head;
    static int[] ver;
    static int[] next;
    static int tot;
    static int K;
    static int[] kinds;
    static long[][] graphs;
    static long[][] distance;

    public static void addEdge(int x, int y) {
        ver[++tot] = y; next[tot] = head[x]; head[x] = tot;
        ver[++tot] = x; next[tot] = head[y]; head[y] = tot;
    }

    public static long distance(int x, int y) {
        if (x == y) return 0;
        long[] min = new long[N + 1];
        Arrays.fill(min, -1);
        min[x] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        while (!queue.isEmpty()) {
            int out = queue.poll();
            for (int i = head[out]; i > 0; i = next[i]) {
                int neighbour = ver[i];
                if (min[neighbour] == -1) {
                    if (neighbour == y) {
                        return min[out] + 1;
                    }
                    min[neighbour] = min[out] + 1;
                    queue.offer(neighbour);
                }
            }
        }
        return Long.MAX_VALUE;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        head = new int[N + 1];
        ver = new int[2 * M + 2];
        next = new int[2 * M + 2];
        tot = 1;

        for (int i = 1; i <= M; i++) addEdge(in.nextInt(), in.nextInt());

        K = in.nextInt();
        kinds = new int[K];
        for (int i = 0; i < K; i++) kinds[i] = in.nextInt();
        graphs = new long[1 << 20][20];

        distance = new long[K][K];
        for (int i = 0; i < K; i++)
            for (int j = i; j < K; j++)
                distance[i][j] = distance(kinds[i], kinds[j]);


        for (long[] row: graphs) Arrays.fill(row, Long.MAX_VALUE);
        for (int i = 0; i < K; i++) graphs[1 << i][i] = 0;
        for (int mask = 1; mask < (1 << K); mask++)
            for (int i = 0; i < K; i++)
                if ((mask & (1 << i)) != 0)
                    for (int j = 0; j < K; j++)
                        if ((mask & (1 << j)) != 0)
                            graphs[mask][i] = Math.min(graphs[mask][i], graphs[mask ^ (1 << i)][j] + distance[Math.min(j, i)][Math.max(j, i)]);

        long best = Long.MAX_VALUE;
        for (int i = 0; i < K; i++)
            best = Math.min(best, graphs[(1 << K) - 1][i]);
        System.out.println((best + 1) >= 0 ? best + 1 : -1);
    }
}
