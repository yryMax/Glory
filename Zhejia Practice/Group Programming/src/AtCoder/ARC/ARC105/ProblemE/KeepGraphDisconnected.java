package AtCoder.ARC.ARC105.ProblemE;

import java.util.Scanner;

public class KeepGraphDisconnected {
    static final String first = "First";
    static final String second = "Second";


    static int N;
    static int M;
    static int[] heads;
    static int[] vers;
    static int[] nexts;
    static int tot;
    static boolean[] visited;
    static int count = 0;

    private static void add(int x, int y) {
        vers[++tot] = y; nexts[tot] = heads[x]; heads[x] = tot;
        vers[++tot] = x; nexts[tot] = heads[y]; heads[y] = tot;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        Main:
        while (T-- > 0) {
            N = in.nextInt();
            M = in.nextInt();
            heads = new int[N + 1];
            vers = new int[2 * M + 2];
            nexts = new int[2 * M + 2];
            tot = 1;
            for (int i = 1; i <= M; i++)
                add(in.nextInt(), in.nextInt());

            if (N % 2 == 1) {
                System.out.println(((N * (N - 1) / 2 - M) % 2 == 1) ? first : second);
                continue Main;
            }

            visited = new boolean[N + 1];
            count = 1;
            visited[1] = true;
            dfs(1);
            int x = count;

            visited = new boolean[N + 1];
            count = 1;
            visited[N] = true;
            dfs(N);
            int y = count;

            if (x % 2 != y % 2) {
                System.out.println(first);
                continue Main;
            }

            System.out.println(((N * (N - 1) / 2 - x * y - M) % 2 == 1) ? first : second);

        }
    }

    private static void dfs(int x) {
        for (int edge = heads[x]; edge > 0; edge = nexts[edge]) {
            int neighbour = vers[edge];
            if (visited[neighbour]) continue;
            count++;
            visited[neighbour] = true;
            dfs(neighbour);
        }
    }
}
