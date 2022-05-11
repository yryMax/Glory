package AcWing.P346;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class WaterFestival {
    static Edge[] edges;
    static int[] unionSet;
    static int[] rank;
    static int N;

    private static int find(int p) {
        if (p == unionSet[p]) return p;
        else {
            unionSet[p] = find(unionSet[p]);
            return unionSet[p];
        }
    }

    private static int union(int x, int y) {
        if (rank[x] >= rank[y]) {
            rank[x] += rank[y];
            unionSet[find(y)] = x;
            return x;
        } else {
            rank[y] += rank[x];
            unionSet[find(x)] = y;
            return y;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numTest = in.nextInt();
        while (numTest-- > 0) {
            N = in.nextInt();
            unionSet = new int[N + 1];
            rank = new int[N + 1];
            edges = new Edge[N - 1];
            Arrays.fill(rank, 1);
            for (int i = 0; i < N - 1; i++)
                edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
            Arrays.sort(edges);
            for (int i = 1; i <= N; i++)
                unionSet[i] = i;

            long ans = 0;
            for (int i = 0; i < N - 1; i++) {
                int newLength = edges[i].length + 1;
                int x_root = find(edges[i].end1);
                int y_root = find(edges[i].end2);
                ans += newLength * ((long) rank[x_root] * rank[y_root] - 1);
                union(x_root, y_root);
            }
            out.println(ans);
            out.flush();
        }
    }
}

class Edge implements Comparable<Edge> {
    int end1;
    int end2;
    int length;

    public Edge(int end1, int end2, int length) {
        this.end1 = end1;
        this.end2 = end2;
        this.length = length;
    }


    @Override
    public int compareTo(Edge other) {
        return Integer.compare(length, other.length);
    }
}
