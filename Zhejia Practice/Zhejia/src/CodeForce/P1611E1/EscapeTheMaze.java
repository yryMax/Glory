package CodeForce.P1611E1;

import java.io.PrintWriter;
import java.util.*;

public class EscapeTheMaze {
    static int[] head;
    static int[] next;
    static int[] vertexes;
    static int tot;

    public static void addEdge(int x, int y) {
        vertexes[++tot] = y; next[tot] = head[x]; head[x] = tot;
        vertexes[++tot] = x; next[tot] = head[y]; head[y] = tot;
    }

    public static boolean isLeaf(int x) {
        int count = 0;
        for (int i = head[x]; i > 0; i = next[i]) count++;
        return count == 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numTest = in.nextInt();
        Main:
        while (numTest-- > 0) {
            int numRoom = in.nextInt();
            int numFriend = in.nextInt();
            int[] initials = new int[numFriend];
            for (int i = 0; i < numFriend; i++)
                initials[i] = in.nextInt();

            // Initial the graph;
            head = new int[numRoom + 1];
            next = new int[2 * numRoom];
            vertexes = new int[2 * numRoom];
            tot = 1;

            for (int i = 1; i < numRoom; i++)
                addEdge(in.nextInt(), in.nextInt());

            Queue<Integer> queue = new LinkedList<>();
             // Multi-vertex BFS
            int[] dangers = new int[numRoom + 1];
            Arrays.fill(dangers, -1);
            for (int initial: initials) {
                queue.offer(initial);
                dangers[initial] = 0;
            }

            while (!queue.isEmpty()) {
                int ver = queue.poll();
                for (int edge = head[ver]; edge > 0; edge = next[edge]) {
                    int neighbour = vertexes[edge];
                    if (dangers[neighbour] == -1) {
                        dangers[neighbour] = dangers[ver] + 1;
                        queue.offer(neighbour);
                    }
                }
            }

            queue = new LinkedList<>();
            int[] depth = new int[numRoom + 1];
            Arrays.fill(depth, -1);
            queue.offer(1);
            depth[1] = 0;
            while (!queue.isEmpty()) {
                int ver = queue.poll();
                if (ver != 1 && isLeaf(ver)) {
                    out.println("YES");
                    out.flush();
                    continue Main;
                }

                for (int i = head[ver]; i > 0; i = next[i]) {
                    int neighbour = vertexes[i];
                    if (depth[neighbour] == -1) {
                        depth[neighbour] = depth[ver] + 1;
                        if (depth[neighbour] >= dangers[neighbour]) continue;
                        queue.offer(neighbour);
                    }
                }
            }
            out.println("NO");
            out.flush();
        }
    }
}
