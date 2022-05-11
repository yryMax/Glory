package AcWing.P342;

import java.io.PrintWriter;
import java.util.*;

public class RoadsAndRoutes {
    static final Long LIMIT = Long.MAX_VALUE - 10000;
    static int numTowns;
    static int numRoads;
    static int numRoutes;
    static int center;

    static int[] heads;
    static int[] vers;
    static int[] edges;
    static int[] next;
    static int[] kinds;
    static int tot;

    static int[] colors;
    static List<List<Integer>> allChunks;
    static int currColor;

    static int[] inDegrees;
    static long[] distances;

    public static void addEdge(int x, int y, int z, int kind) {
        vers[++tot] = y; edges[tot] = z;
        next[tot] = heads[x]; heads[x] = tot;
        kinds[tot] = kind;
        if (kind == 1) {
            vers[++tot] = x; edges[tot] = z;
            next[tot] = heads[y]; heads[y] = tot;
            kinds[tot] = kind;
        }
    }

    public static void colorVertexes() {
        for (int i = 1; i <= numTowns; i++) {
            if (colors[i] != 0) continue;
            ++currColor;
            allChunks.add(new ArrayList<>());
            colors[i] = currColor;
            allChunks.get(currColor).add(i);
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            while (!stack.isEmpty()) {
                int currVer = stack.pop();
                for (int e = heads[currVer]; e > 0; e = next[e]) {
                    if (kinds[e] == 2) continue;
                    int neighbour = vers[e];
                    if (colors[neighbour] != 0) continue;
                    colors[neighbour] = currColor;
                    allChunks.get(currColor).add(neighbour);
                    stack.push(neighbour);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        numTowns = in.nextInt();
        numRoads = in.nextInt();
        numRoutes = in.nextInt();
        center = in.nextInt();


        heads = new int[numTowns + 1];
        vers = new int[numRoads * 2 + numRoutes + 1];
        edges = new int[numRoads * 2 + numRoutes + 1];
        next = new int[numRoads * 2 + numRoutes + 1];
        kinds = new int[numRoads * 2 + numRoutes + 1];
        tot = 0;

        for (int i = 1; i <= numRoads; i++)
            addEdge(in.nextInt(), in.nextInt(), in.nextInt(), 1);

        for (int i = 1; i <= numRoutes; i++)
            addEdge(in.nextInt(), in.nextInt(), in.nextInt(), 2);


        colors = new int[numTowns + 1];
        allChunks = new ArrayList<>();
        allChunks.add(new ArrayList<>());
        currColor = 0;
        colorVertexes();

        inDegrees = new int[currColor + 1];
        distances = new long[numTowns + 1];
        Arrays.fill(distances, LIMIT);
        distances[center] = 0;



        for (int e = 1; e <= numRoads * 2 + numRoutes; e++) {
            if (kinds[e] == 1) continue;
            int toVer = vers[e];
            inDegrees[colors[toVer]]++;
        }

        Queue<Integer> topoChunks = new LinkedList<>();

        for (int c = 1; c <= currColor; c++)
            if (inDegrees[c] == 0)
                topoChunks.offer(c);


        while (!topoChunks.isEmpty()) {
            int currChunk = topoChunks.poll();
            List<Integer> versIn = allChunks.get(currChunk);

            long min = LIMIT;
            for (Integer ver: versIn)
                if (distances[ver] < min)
                    min = distances[ver];



            if (min != LIMIT) {
                PriorityQueue<Pair> dijkstra = new PriorityQueue<>();
                boolean[] inHeap = new boolean[numTowns + 1];
                Arrays.fill(inHeap, true);
                for (int ver: versIn) dijkstra.offer(new Pair(distances[ver], ver));

                while (!dijkstra.isEmpty()) {
                    Pair top = dijkstra.poll();
                    int currVer = top.vertex;
                    long currDis = top.distance;
                    if (!inHeap[currVer]) continue;
                    inHeap[currVer] = false;
                    for (int e = heads[currVer]; e > 0; e = next[e]) {
                        int to = vers[e];
                        long newDis = currDis + edges[e];
                        if (newDis < distances[to]) {
                            distances[to] = newDis;
                            if (kinds[e] == 1) {
                                dijkstra.offer(new Pair(distances[to], to));
                                inHeap[to] = true;
                            }
                        }
                    }
                }
            }

            for (Integer ver: versIn) {
                for (int e = heads[ver]; e > 0; e = next[e]) {
                    if (kinds[e] == 2) {
                        int to = vers[e];
                        inDegrees[colors[to]]--;
                        if (inDegrees[colors[to]] == 0) topoChunks.offer(colors[to]);
                    }
                }
            }

        }

        for (int i = 1; i <= numTowns; i++)
            out.println((distances[i] == LIMIT) ? "NO PATH" : distances[i]);
        out.flush();
    }
}

class Pair implements Comparable<Pair> {
    long distance;
    int vertex;

    public Pair(long distance, int vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }

    @Override
    public int compareTo(Pair pair) {
        return Long.compare(distance, pair.distance);
    }
}