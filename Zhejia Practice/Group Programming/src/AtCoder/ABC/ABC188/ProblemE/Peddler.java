package AtCoder.ABC.ABC188.ProblemE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Peddler {
    static int N;
    static int M;
    static int[] prices;
    static int[] head;
    static int[] next;
    static int[] vertexes;
    static int[] inDegree;
    static int tot;

    private static void addEdge(int x, int y) {
        vertexes[++tot] = y;
        next[tot] = head[x];
        head[x] = tot;
        inDegree[y]++;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        prices = new int[N + 1];

        prices[0] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++)
            prices[i] = in.nextInt();

        //Initial the graph
        head = new int[N + 1];
        next = new int[M + 1];
        vertexes = new int[M + 1];
        inDegree = new int[N + 1];
        tot = 0;
        for (int i = 1; i <= M; i++) addEdge(in.nextInt(), in.nextInt());

        int[] dp = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        int[] countIn = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        for (int city = 1; city <= N; city++) {
            if (!visited[city]) {
                queue.offer(city);
                visited[city] = true;
                while (!queue.isEmpty()) {
                    Integer out = queue.poll();
                    for (int i = head[out]; i > 0; i = next[i]) {
                        int neighbour = vertexes[i];
                        dp[neighbour] = Math.min(dp[neighbour], Math.min(dp[out], prices[out]));
                        visited[neighbour] = true;
                        countIn[neighbour]++;
                        if (countIn[neighbour] == inDegree[neighbour]) {
                            queue.offer(neighbour);
                        }
                    }
                }
            }
        }

        int profit = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++)
            profit = Math.max(profit, prices[i] - dp[i]);
        System.out.println(profit);
    }
}
