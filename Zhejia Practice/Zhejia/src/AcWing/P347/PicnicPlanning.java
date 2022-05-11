package AcWing.P347;

import java.util.*;

public class PicnicPlanning {
    public static final String OUTPUT = "Total miles driven: ";
    static Scanner in;
    static int N;
    static int limit;
    static Map<String, Integer> names;
    static long[][] adjMatrix;
    static long[] distance;
    static boolean[] visited;
    static int[] connection;
    static long[][] prim;
    static long ans = 0;
    static int degree1 = 0;


    private static void read() {
        in = new Scanner(System.in);
        N = in.nextInt();
        names = new HashMap<>();
        adjMatrix = new long[23][23];
        int ind = 2;
        for (long[] row: adjMatrix) Arrays.fill(row, Long.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            String ver1 = in.next();
            String ver2 = in.next();
            long len = in.nextLong();
            if (ver1.equals("Park") && !names.containsKey(ver1))
                names.put("Park", 1);
            else if (!names.containsKey(ver1))
                names.put(ver1, ind++);

            if (ver2.equals("Park") && !names.containsKey(ver2))
                names.put("Park", 1);
            else if (!names.containsKey(ver2))
                names.put(ver2, ind++);
            adjMatrix[names.get(ver1)][names.get(ver2)] = Math.min(adjMatrix[names.get(ver1)][names.get(ver2)], len);
            adjMatrix[names.get(ver2)][names.get(ver1)] = Math.min(adjMatrix[names.get(ver2)][names.get(ver1)], len);
        }
        limit = in.nextInt();
    }

    private static void prim() {
        distance = new long[23];
        visited = new boolean[23];
        connection = new int[23];
        prim = new long[23][23];

        Arrays.fill(distance, Long.MAX_VALUE);
        for (long[] row: prim) Arrays.fill(row, Long.MAX_VALUE);
        distance[1] = 0;

        for (int i = 1; i < distance.length; i++) {
            int ind = 0;
            for (int current = 1; current < distance.length; current++)
                if (!visited[current] && (ind == 0 || distance[current] < distance[ind]))
                    ind = current;
            visited[ind] = true;
            for (int ver = 1; ver < distance.length; ver++)
                if (!visited[ver] && distance[ver] > adjMatrix[ind][ver]) {
                    distance[ver] = adjMatrix[ind][ver];
                    connection[ver] = ind;
                }
        }

        for (int ver = 2; ver < distance.length; ver++) {
            if (distance[ver] == Long.MAX_VALUE) continue;
            prim[ver][connection[ver]] = distance[ver];
            prim[connection[ver]][ver] = distance[ver];
            if (connection[ver] == 1) degree1++;
            ans += distance[ver];
        }
    }

    private static void optimise() {
        long optimalDelta = Long.MAX_VALUE;
        int optimalDeleteVer = 0;
        int optimalAddVer1 = 0;
        int optimalAddVer2 = 0;
        for (int i = 1; i < prim[0].length; i++) {
            Arrays.fill(visited, false);
            visited[1] = true;
            if (prim[1][i] == Long.MAX_VALUE) continue;
            long deleteEdge = prim[1][i];
            colorGraph(i);
            AddEdge addEdge = calculate();
            long currentDelta = Long.MAX_VALUE;
            if (addEdge.end1 != 0 && addEdge.end2 != 0 && addEdge.length != Long.MAX_VALUE) {
                currentDelta = addEdge.length - deleteEdge;
                if (currentDelta < optimalDelta) {
                    optimalDelta  = currentDelta;
                    optimalDeleteVer = i;
                    optimalAddVer1 = addEdge.end1;
                    optimalAddVer2 = addEdge.end2;
                }
            }
        }
        prim[1][optimalDeleteVer] = Long.MAX_VALUE;
        prim[optimalDeleteVer][1] = Long.MAX_VALUE;
        prim[optimalAddVer1][optimalAddVer2] = adjMatrix[optimalAddVer1][optimalAddVer2];
        prim[optimalAddVer2][optimalAddVer1] = adjMatrix[optimalAddVer2][optimalAddVer1];
        ans += optimalDelta;
    }

    private static AddEdge calculate() {
        AddEdge outEdge = new AddEdge(0, 0, Long.MAX_VALUE);
        for (int end1 = 2; end1 < visited.length; end1++) if (visited[end1])
            for (int end2 = 2; end2 < visited.length; end2++) if (!visited[end2])
                if (adjMatrix[end1][end2] < outEdge.length) {
                    outEdge.end1 = end1;
                    outEdge.end2 = end2;
                    outEdge.length = adjMatrix[end1][end2];
                }
        return outEdge;
    }

    private static void colorGraph(int ver) {
        visited[ver] = true;
        for (int i = 2; i < visited.length; i++)
            if (!visited[i] && prim[ver][i] != Long.MAX_VALUE)
                colorGraph(i);
    }



    public static void main(String[] args) {
        read();
        prim();
        while (degree1 > limit) {
            optimise();
            degree1--;
        }
        System.out.println(OUTPUT + ans);
    }
}

class AddEdge {
    int end1;
    int end2;
    long length;

    public AddEdge(int end1, int end2, long length) {
        this.end1 = end1;
        this.end2 = end2;
        this.length = length;
    }
}
