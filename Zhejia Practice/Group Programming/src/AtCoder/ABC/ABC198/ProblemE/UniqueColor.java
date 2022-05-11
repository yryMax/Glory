package AtCoder.ABC.ABC198.ProblemE;

import java.io.PrintWriter;
import java.util.*;

public class UniqueColor {
    static int[] heads;
    static int[] vers;
    static int[] nexts;
    static int tot;
    static int[] colors;
    static boolean[] occupied;
    static boolean[] visited;
    static List<Integer> output;

    private static void addEdge(int x, int y) {
        vers[++tot] = y; nexts[tot] = heads[x]; heads[x] = tot;
        vers[++tot] = x; nexts[tot] = heads[y]; heads[y] = tot;
    }

    private static void dfs(int x) {
        boolean not = occupied[colors[x]];
        if (!occupied[colors[x]]) {
            occupied[colors[x]] = true;
            output.add(x);
        }
        visited[x] = true;
        for (int edge = heads[x]; edge > 0; edge = nexts[edge])
            if (!visited[vers[edge]]) dfs(vers[edge]);
        if (!not) occupied[colors[x]] = false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        colors = new int[N + 1];
        heads = new int[N + 1];
        visited = new boolean[N + 1];
        vers = new int[2 * N + 2];
        tot = 1;
        nexts = new int[2 * N + 2];
        occupied = new boolean[100005];
        for (int i = 1; i <= N; i++)
            colors[i] = in.nextInt();
        for (int i = 1; i < N; i++)
            addEdge(in.nextInt(), in.nextInt());
        output = new ArrayList<>();

        dfs(1);
        Collections.sort(output);
        for (Integer ans : output)
            out.println(ans);
        out.flush();
    }
}
