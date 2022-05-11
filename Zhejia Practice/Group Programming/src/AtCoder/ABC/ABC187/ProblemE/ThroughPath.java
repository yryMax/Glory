package AtCoder.ABC.ABC187.ProblemE;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ThroughPath {
    static long[] values;
    static int[] head;
    static int[] vertexes;
    static int[] next;
    static int[] depth;
    static int tot;

    private static void addEdge(int x, int y) {
        vertexes[++tot] = y; next[tot] = head[x]; head[x] = tot; // add an edge from x to y
        vertexes[++tot] = x; next[tot] = head[y]; head[y] = tot; // add an edge from y to x
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numVertex = in.nextInt();

        head = new int[numVertex + 1];
        vertexes = new int[2 * numVertex]; // totalSize = 1 (index 0) + 1 (index 1, useless) + 2 * (numVertex - 1) = 2 * numVertex
        next = new int[2 * numVertex];
        depth = new int[numVertex + 1];
        tot = 1;

        for (int i = 0; i < numVertex - 1; i++)
            addEdge(in.nextInt(), in.nextInt());

        // BFS
        depth[1] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            Integer out = queue.poll();
            for (int i = head[out]; i > 0; i = next[i]) {
                int neighbour = vertexes[i];
                if (depth[neighbour] == 0) {
                    depth[neighbour] = depth[out] + 1;
                    queue.offer(neighbour);
                }
            }
        }

        values = new long[numVertex + 1];
        int numQuery = in.nextInt();
        while (numQuery-- > 0) {
            int type = in.nextInt();
            int edge = in.nextInt();
            int delta = in.nextInt();
            int center = (type == 1) ? vertexes[(2 * edge) ^ 1] : vertexes[2 * edge];
            int avoid = (type == 1) ? vertexes[2 * edge] : vertexes[(2 * edge) ^ 1];
            if (depth[center] > depth[avoid]) {
                values[center] += delta;
            } else {
                values[avoid] += (-delta);
                values[1] += delta;
            }
        }

        // Accumulation from the root by BFS
        queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int out = queue.poll();
            for (int i = head[out]; i > 0; i = next[i]) {
                int neighbour = vertexes[i];
                if (depth[neighbour] > depth[out]) {
                    values[neighbour] += values[out];
                    queue.offer(neighbour);
                }
            }
        }
        for (int i = 1; i <= numVertex; i++)
            System.out.println(values[i]);
    }


}
