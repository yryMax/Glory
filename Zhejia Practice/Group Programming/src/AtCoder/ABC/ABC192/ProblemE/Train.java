package AtCoder.ABC.ABC192.ProblemE;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Train {
    static int N;
    static int M;
    static int X;
    static int Y;
    static int[] heads;
    static int[] vers;
    static int[] durations;
    static int[] departures;
    static int[] next;
    static int tot;
    static boolean[] visited;

    private static void addEdge(int x, int y, int dur, int dep) {
        vers[++tot] = y; durations[tot] = dur; departures[tot] = dep;
        next[tot] = heads[x]; heads[x] = tot;
        vers[++tot] = x; durations[tot] = dur; departures[tot] = dep;
        next[tot] = heads[y]; heads[y] = tot;
    }




    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        X = in.nextInt();
        Y = in.nextInt();

        heads = new int[N + 1];
        vers = new int[2 * M + 2];
        durations = new int[2 * M + 2];
        departures = new int[2 * M + 2];
        next = new int[2 * M + 2];
        tot = 1;
        visited = new boolean[2 * M + 2];

        for (int i = 1; i <= M; i++)
            addEdge(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());

        PriorityQueue<Pair> queue = new PriorityQueue<>();
        boolean[] inHeap = new boolean[N + 1];
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        queue.offer(new Pair(0, X));
        inHeap[X] = true;
        distance[X] = 0;
        while (!queue.isEmpty()) {
            Pair top = queue.poll();
            int currVer = top.ver;
            long currTime = top.time;
            if (!inHeap[currVer]) continue;
            if (currVer == Y) {
                System.out.println(currTime);
                return;
            }
            for (int i = heads[currVer]; i > 0; i = next[i]) {
                if (visited[i]) continue;
                visited[i] = true;
                int toVer = vers[i];
                int duration = durations[i];
                int departure = departures[i];
                long nextDeparture = (currTime % departure == 0) ? currTime : currTime + (departure - currTime % departure);
                long nextArrival = nextDeparture + duration;
                if (nextArrival < distance[toVer]) {
                    distance[toVer] = nextArrival;
                    inHeap[toVer] = true;
                    queue.offer(new Pair(distance[toVer], toVer));
                }
            }
        }
        System.out.println(-1);
    }
}

class Pair implements Comparable<Pair>{
    long time;
    int ver;

    public Pair(long time, int ver) {
        this.time = time;
        this.ver = ver;
    }

    @Override
    public int compareTo(Pair p) {
        return Long.compare(time, p.time);
    }
}
