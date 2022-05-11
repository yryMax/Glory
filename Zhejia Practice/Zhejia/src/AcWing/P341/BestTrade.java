package AcWing.P341;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BestTrade {
    static int N;
    static int M;
    static int[] prices;
    static int[] head1;
    static int[] head2;
    static int[] vers1;
    static int[] vers2;
    static int[] next1;
    static int[] next2;
    static int tot1;
    static int tot2;

    private static void addEdge(int x, int y, int z) {
        vers1[++tot1] = y; next1[tot1] = head1[x]; head1[x] = tot1;
        vers2[++tot2] = x; next2[tot2] = head2[y]; head2[y] = tot2;
        if (z == 2) {
            vers1[++tot1] = x; next1[tot1] = head1[y]; head1[y] = tot1;
            vers2[++tot2] = y; next2[tot2] = head2[x]; head2[x] = tot2;
        }

    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        prices = new int[N + 1];
        for (int i = 1; i <= N; i++)
            prices[i] = in.nextInt();

        head1 = new int[N + 1];
        head2 = new int[N + 1];
        vers1 = new int[2 * (M + 1)];
        vers2 = new int[2 * (M + 1)];
        next1 = new int[2 * (M + 1)];
        next2 = new int[2 * (M + 1)];
        tot1 = 1;
        tot2 = 1;

        for (int i = 1; i <= M; i++)
            addEdge(in.nextInt(), in.nextInt(), in.nextInt());

        int[][] differences = new int[2][N + 1];
        Arrays.fill(differences[0], 101);
        Arrays.fill(differences[1], -1);

        Queue<Integer> queue1 = new LinkedList<>();
        differences[0][1] = prices[1];
        queue1.offer(1);
        while (!queue1.isEmpty()) {
            int currVer = queue1.poll();
            int currMin = differences[0][currVer];
            for (int i = head1[currVer]; i > 0; i = next1[i]) {
                int neighbour = vers1[i];
                if (Math.min(currMin, prices[neighbour]) < differences[0][neighbour]) {
                    differences[0][neighbour] = Math.min(currMin, prices[neighbour]);
                    queue1.offer(neighbour);
                }
            }
        }

        Queue<Integer> queue2 = new LinkedList<>();
        differences[1][N] = prices[N];
        queue2.offer(N);
        while (!queue2.isEmpty()) {
            int currVer = queue2.poll();
            int currMax = differences[1][currVer];
            for (int i = head2[currVer]; i > 0; i = next2[i]) {
                int neighbour = vers2[i];
                if (Math.max(currMax, prices[neighbour]) > differences[1][neighbour]) {
                    differences[1][neighbour] = Math.max(currMax, prices[neighbour]);
                    queue2.offer(neighbour);
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        boolean[] visited = new boolean[2 * (M + 1)];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = head1[1]; i > 0; i = next1[i])
            queue.offer(i);
        while (!queue.isEmpty()) {
            int currEdge = queue.poll();
            if (visited[currEdge]) continue;
            visited[currEdge] = true;
            ans = Math.max(ans, differences[1][vers1[currEdge]] - differences[0][vers2[currEdge]]);
            for (int i = head1[vers1[currEdge]]; i > 0; i = next1[i])
                queue.offer(i);
        }

        System.out.println(Math.max(0, ans));
    }
}
