package AcWing.P345;

import java.util.*;

public class CowRelaysMatrix {
    static final Long LIMIT = Long.MAX_VALUE / 2 - 10000;
    static int N;
    static int T;
    static int S;
    static int E;
    static long[][] edges;
    static List<Integer> vertexes;
    static Map<Integer, Integer> original;
    static int numVer;
    static long[][] adjacencyMap;
    static long[][] ansMap;

    private static int discrete() {
        Collections.sort(vertexes);
        original = new HashMap<>();
        for (int i = 0; i < vertexes.size(); i++)
            if (!original.containsKey(vertexes.get(i)))
                original.put(vertexes.get(i), i + 1);
        return vertexes.size();
    }

    private static int getIndex(int oldIndex) {
        return original.get(oldIndex);
    }

    private static void matrixPower() {
        while (N > 0) {
            if ((N & 1) == 1)
                ansMap = matrixMultiplication(ansMap, adjacencyMap);
            adjacencyMap = matrixMultiplication(adjacencyMap, adjacencyMap);
            N >>= 1;
        }
    }

    private static long[][] matrixMultiplication(long[][] matrix1, long[][] matrix2) {
        long[][] ans = new long[numVer + 1][numVer + 1];
        for (long[] row: ans) Arrays.fill(row, LIMIT);
        for (int r = 1; r <= numVer; r++)
            for (int c = 1; c <= numVer; c++)
                for (int k = 1; k <= numVer; k++)
                    ans[r][c] = Math.min(ans[r][c], matrix1[r][k] + matrix2[k][c]);
        return ans;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        T = in.nextInt();
        S = in.nextInt();
        E = in.nextInt();
        edges = new long[T + 1][3];
        vertexes = new ArrayList<>();
        for (int i = 1; i <= T; i++) {
            edges[i][2] = in.nextLong();
            edges[i][0] = in.nextInt();
            vertexes.add((int) edges[i][0]);
            edges[i][1] = in.nextInt();
            vertexes.add((int) edges[i][1]);
        }

        numVer = discrete();
        adjacencyMap = new long[numVer + 1][numVer + 1];
        for (long[] row: adjacencyMap) Arrays.fill(row, LIMIT);
        ansMap = new long[numVer + 1][numVer + 1];
        for (long[] row: ansMap) Arrays.fill(row, LIMIT);
        for (int i = 1; i <= numVer; i++)
            ansMap[i][i] = 0;
        for (int i = 1; i <= T; i++) {
            adjacencyMap[getIndex((int) edges[i][0])][getIndex((int) edges[i][1])] = Math.min(adjacencyMap[getIndex((int) edges[i][0])][getIndex((int) edges[i][1])], edges[i][2]);
            adjacencyMap[getIndex((int) edges[i][1])][getIndex((int) edges[i][0])] = Math.min(adjacencyMap[getIndex((int) edges[i][1])][getIndex((int) edges[i][0])], edges[i][2]);
        }
        S = getIndex(S);
        E = getIndex(E);

        matrixPower();
        System.out.println(ansMap[S][E]);
    }
}
