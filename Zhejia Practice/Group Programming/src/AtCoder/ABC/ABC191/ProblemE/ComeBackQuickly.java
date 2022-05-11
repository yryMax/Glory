package AtCoder.ABC.ABC191.ProblemE;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ComeBackQuickly {
    static int N;
    static int M;
    static int[] heads;
    static int[] vers;
    static int[] edges;
    static int[] next;
    static int tot;

    private static void addEdge(int x, int y, int z) {
        vers[++tot] = y; edges[tot] = z;
        next[tot] = heads[x]; heads[x] = tot;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        M = in.nextInt();
        heads = new int[N + 1];
        vers = new int[M + 1];
        edges = new int[M + 1];
        next = new int[M + 1];
        tot = 0;
        for (int i = 1; i <= M; i++)
            addEdge(in.nextInt(), in.nextInt(), in.nextInt());

        Main:
        for (int currStart = 1; currStart <= N; currStart++) {
            boolean beginning = false;
            PriorityQueue<Pair> queue = new PriorityQueue<>();
            boolean[] inHeap = new boolean[N + 1];
            int[] distances = new int[N + 1];
            queue.offer(new Pair(0, currStart));
            inHeap[currStart] = true;
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[currStart] = 0;

            while (!queue.isEmpty()) {
                Pair front = queue.poll();
                int currVer = front.vertex;
                int currDis = front.distance;
                if (!inHeap[currVer]) continue;
                if (beginning && currVer == currStart) {
                    out.println(distances[currStart]);
                    out.flush();
                    continue Main;
                }
                beginning = true;
                if (distances[currStart] == 0) distances[currStart] = Integer.MAX_VALUE;
                inHeap[currVer] = false;
                for (int i = heads[currVer]; i > 0; i = next[i]) {
                    int neighbour = vers[i];
                    int newDis = currDis + edges[i];
                    if (newDis < distances[neighbour]) {
                        distances[neighbour] = newDis;
                        queue.offer(new Pair(distances[neighbour], neighbour));
                        inHeap[neighbour] = true;
                    }
                }
            }
            if (distances[currStart] == Integer.MAX_VALUE) {
                out.println(-1);
                out.flush();
            }
        }
    }
}


class Pair implements Comparable<Pair> {
    int distance;
    int vertex;

    public Pair(int distance, int vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }


    @Override
    public int compareTo(Pair pair) {
        return distance - pair.distance;
    }
}
