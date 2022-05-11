package AcWing.P383;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sightseeing {
    static int T;
    static int N;
    static int M;
    static int[] heads;
    static int[] vers;
    static int[] edges;
    static int[] next;
    static int tot;
    static Queue<Pair> queue;
    static int[][] count; // 0 represents shortest; 1 represents second shortest :); 2 represents shortest length

    private static void addEdge(int x, int y, int z) {
        vers[++tot] = y; edges[tot] = z;
        next[tot] = heads[x]; heads[x] = tot;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        T = in.nextInt();
        while (T-- > 0) {
            int countFirst = 0;
            int countSecond = 0;
            N = in.nextInt();
            M = in.nextInt();
            heads = new int[N + 1];
            vers = new int[M + 1];
            edges = new int[M + 1];
            next = new int[M + 1];
            tot = 0;
            for (int i = 1; i <= M; i++) addEdge(in.nextInt(), in.nextInt(), in.nextInt());
            int S = in.nextInt();
            int F = in.nextInt();
            queue = new LinkedList<>();
            count = new int[N + 1][3];
            for (int i = 1; i <= N; i++) count[i][2] = Integer.MAX_VALUE - 5;
            queue.offer(new Pair(0, S));
            count[S][0] = 1;
            while (!queue.isEmpty()) {
                Pair near = queue.poll();
                int currVer = near.index;
                int currDis = near.distance;
                if (currDis > count[F][2] + 1) continue;
                if (count[currVer][0] == 0 && count[currVer][1] == 0) continue;
                if (currDis == count[currVer][0]) count[currVer][0]--; else count[currVer][1]--;
                if (currVer == F && (count[F][2] == Integer.MAX_VALUE - 5 || Math.abs(count[F][2] - currDis) <= 1)) {
                    if (countFirst == 0) countFirst = 1;
                    else {
                        if (currDis > count[F][2]) countSecond++;
                        else if (currDis == count[F][2]) countFirst++;
                        else {
                            count[F][2] = currDis;
                            countSecond = countFirst;
                            countFirst = 1;
                        }
                    }
                    continue;
                }
                for (int i = heads[currVer]; i > 0; i = next[i]) {
                    int neighbour = vers[i];
                    int edge = edges[i];
                    int newDis = currDis + edge;
                    if (newDis < count[neighbour][2] - 1) {
                        count[neighbour][2] = newDis;
                        count[neighbour][0] = 1;
                        count[neighbour][1] = 0;
                        queue.offer(new Pair(newDis, neighbour));
                    } else if (newDis == count[neighbour][2] - 1) {
                        count[neighbour][2] = newDis;
                        count[neighbour][1] = count[neighbour][0];
                        count[neighbour][0] = 1;
                        queue.offer(new Pair(newDis, neighbour));
                    } else if (newDis == count[neighbour][2]) {
                        count[neighbour][0]++;
                        queue.offer(new Pair(newDis, neighbour));
                    } else if (newDis == count[neighbour][2] + 1) {
                        count[neighbour][1]++;
                        queue.offer(new Pair(newDis, neighbour));
                    }
                }
            }
            out.println(countFirst + countSecond);
            out.flush();
        }
    }
}

class Pair implements Comparable<Pair> {
    int distance;
    int index;

    public Pair(int distance, int index) {
        this.distance = distance;
        this.index = index;
    }


    @Override
    public int compareTo(Pair pair) {
        return distance - pair.distance;
    }
}
