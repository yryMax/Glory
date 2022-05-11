package AcWing.P349;

import java.util.*;

public class LordLSP {
    static final long INF = Long.MAX_VALUE - 10000000;
    static final long LIMIT = (long) Math.pow(2, 31) - 1;
    static int N;
    static int M;
    static int[] heads;
    static int[] next;
    static int[] vers;
    static int[] edges;
    static int[][] adjMap;
    static int tot;
    static long[][] distances;
    static boolean[] inHeap;
    static PriorityQueue<Pair> queue;
    static long ans;


    public static void add(int x, int y, int len) {
        adjMap[x][y] = Math.min(adjMap[x][y], len); adjMap[y][x] = Math.min(adjMap[y][x], len);
        vers[++tot] = y; edges[tot] = len; next[tot] = heads[x]; heads[x] = tot;
        vers[++tot] = x; edges[tot] = len; next[tot] = heads[y]; heads[y] = tot;
    }


    private static void dijkstra() {
        for (int i = 2; i <= N; i++)
            distances[i][0] = INF;
        queue.offer(new Pair(0, 1));
        inHeap[1] = true;
        while (!queue.isEmpty()) {
            Pair head = queue.poll();
            int currIndex = head.index;
            long currDis = head.distance;
            if (!inHeap[currIndex]) continue;
            inHeap[currIndex] = false;
            for (int outEdge = heads[currIndex]; outEdge > 0; outEdge = next[outEdge]) {
                int neighbour = vers[outEdge];
                long newDis = edges[outEdge] + currDis;
                if (newDis < distances[neighbour][0]) {
                    distances[neighbour][0] = newDis;
                    queue.offer(new Pair(distances[neighbour][0], neighbour));
                    inHeap[neighbour] = true;
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        heads = new int[N + 1];
        next = new int[2 * M + 2];
        vers = new int[2 * M + 2];
        edges = new int[2 * M + 2];
        adjMap = new int[N + 1][N + 1];
        for (int[] row: adjMap) Arrays.fill(row, Integer.MAX_VALUE);
        tot = 1;
        distances = new long[N + 1][2];
        for (int i = 1; i <= N; i++)
            distances[i][1] = i;
        inHeap = new boolean[N + 1];
        queue = new PriorityQueue<>();
        for (int i = 1; i <= M; i++)
            add(in.nextInt(), in.nextInt(), in.nextInt());
        dijkstra();
        Arrays.sort(distances, new Comparator<long[]>() {
            @Override
            public int compare(long[] row1, long[] row2) {
                return Long.compare(row1[0], row2[0]);
            }
        });
        ans = 1;

        for (int i = 2; i < distances.length; i++) {
            int currVer = (int) distances[i][1];
            int count = 0;
            for (int pre = 1; pre < i; pre++) {
                if (adjMap[(int) distances[pre][1]][currVer] == Integer.MAX_VALUE) continue;
                if (distances[pre][0] + adjMap[(int) distances[pre][1]][currVer] == distances[i][0])
                    count++;
            }
            ans = (ans * count) % LIMIT;
        }
        System.out.println(ans % LIMIT);

    }


}

class Pair implements Comparable<Pair> {
    long distance;
    int index;

    public Pair(long distance, int index) {
        this.distance = distance;
        this.index = index;
    }


    @Override
    public int compareTo(Pair pair) {
        return Long.compare(distance, pair.distance);
    }
}
