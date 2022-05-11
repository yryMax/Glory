package AcWing.P350;

import java.util.*;

public class Patrol {
    static int N;
    static int K;
    static int[] heads;
    static int[] vers;
    static int[] edges;
    static int[] next;
    static int tot;
    static int[] pre;
    static int[] distance;
    static int diameter2 = 0;


    public static void add(int x, int y) {
        vers[++tot] = y; edges[tot] = 1; next[tot] = heads[x]; heads[x] = tot;
        vers[++tot] = x; edges[tot] = 1; next[tot] = heads[y]; heads[y] = tot;
    }

    private static int breadthFirstSearch(int x) {
        distance = new int[N + 1];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        distance[x] = 0;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            for (int i = heads[top]; i > 0; i = next[i]) {
                int neighbour = vers[i];
                if (distance[neighbour] == -1) {
                    pre[neighbour] = i;
                    distance[neighbour] = distance[top] + 1;
                    queue.offer(neighbour);
                }
            }
        }
        int index = x;
        for (int i = 1; i <= N; i++)
            if (distance[i] > distance[index])
                index = i;
        return index;
    }

    private static void update(int q, int p) {
         while (q != p) {
            int preEdge = pre[q];
            edges[preEdge] = -1;
            edges[preEdge ^ 1] = -1;
            q = vers[preEdge ^ 1];
         }
    }

    private static void dp(int x, int father) {
        for (int i = heads[x]; i > 0; i = next[i]) {
            int neighbour = vers[i];
            if (neighbour == father) continue;
            dp(neighbour, x);
            diameter2 = Math.max(diameter2, distance[x] + distance[neighbour] + edges[i]);
            distance[x] = Math.max(distance[x], distance[neighbour] + edges[i]);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        heads = new int[N + 1];
        vers = new int[2 * N + 2];
        next = new int[2 * N + 2];
        edges = new int[2 * N + 2];
        pre = new int[N + 1];
        tot = 1;
        for (int i = 1; i < N; i++)
            add(in.nextInt(), in.nextInt());

        int p = breadthFirstSearch(1);
        int q = breadthFirstSearch(p);

        int ans = 2 * (N - 1);
        ans -= (distance[q] - 1);
        if (K == 1) {
            System.out.println(ans);
            return;
        }
        update(q, p);
        distance = new int[N + 1];
        dp(1, 0);
        ans -= (diameter2 - 1);
        System.out.println(ans);
    }

}
