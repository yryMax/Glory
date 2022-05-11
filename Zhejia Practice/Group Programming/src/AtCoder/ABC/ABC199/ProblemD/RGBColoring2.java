package AtCoder.ABC.ABC199.ProblemD;

import java.util.Arrays;
import java.util.Scanner;

public class RGBColoring2 {
    static int N;
    static int M;
    static int[] heads;
    static int[] vers;
    static int[] nexts;
    static int tot;
    static int[] colors;
    static int[] depth;

    private static void add(int x, int y) {
        vers[++tot] = y; nexts[tot] = heads[x]; heads[x] = tot;
        vers[++tot] = x; nexts[tot] = heads[y]; heads[y] = tot;
    }

    private static void dfs(int currVer) {
        for (int edge = heads[currVer]; edge > 0; edge = nexts[edge]) {
            int toVer = vers[edge];
            if (depth[toVer] != 0) continue;
            depth[toVer] = depth[currVer] + 1;
            dfs(toVer);
        }
    }

    private static long paint(int currVer) {
        // check whether there is contradiction
        for (int edge = heads[currVer]; edge > 0; edge = nexts[edge]) {
            int toVer = vers[edge];
            if (depth[toVer] <= depth[currVer] && colors[toVer] != -1 && colors[toVer] == colors[currVer]) return 0;
        }

        long out = 1;
        for (int edge = heads[currVer]; edge > 0; edge = nexts[edge]) {
            int toVer = vers[edge];
            if (depth[toVer] != depth[currVer] + 1) continue;
            long[] countPlan = new long[4];
            for (int nextColor = 1; nextColor <= 3; nextColor++) {
                if (nextColor == colors[currVer]) continue;
                colors[toVer] = nextColor;
                countPlan[nextColor] = paint(toVer);
            }
            out *= (countPlan[1] + countPlan[2] + countPlan[3]);
        }
        return out;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        if (M == 0) {
            System.out.println((long) Math.pow(3, N));
            return;
        }
        heads = new int[N + 1];
        vers = new int[2 * M + 2];
        nexts = new int[2 * M + 2];
        colors = new int[N + 1];
        depth = new int[N + 1];
        tot = 1;
        long ans = 1;
        for (int i = 1; i <= M; i++) add(in.nextInt(), in.nextInt());
        for (int currVer = 1; currVer <= N; currVer++) {
            if (depth[currVer] != 0) continue;
            depth[currVer] = 1;
            dfs(currVer);
        }

        for (int currVer = 1; currVer <= N; currVer++)
            if (colors[currVer] == 0) {
                colors[currVer] = 1;
                ans *= 3L * paint(currVer);
            }
        System.out.println(ans);
    }

}
