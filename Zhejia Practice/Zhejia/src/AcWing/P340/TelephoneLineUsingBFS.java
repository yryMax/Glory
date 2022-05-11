package AcWing.P340;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TelephoneLineUsingBFS {
    static int N;
    static int K;
    static int P;
    static int[] heads;
    static int[] ver;
    static int[] edges;
    static int[] next;
    static int tot;
    static int upperBound;

    private static void add(int x, int y, int z) {
        ver[++tot] = y; edges[tot] = z;
        next[tot] = heads[x]; heads[x] = tot;
        ver[++tot] = x; edges[tot] = z;
        next[tot] = heads[y]; heads[y] = tot;
        upperBound = Math.max(upperBound, z);
    }

    private static boolean check(int current) {
        int[][] count = new int[N + 1][2];
        for (int[] row: count) Arrays.fill(row, -1);
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.offer(1);
        count[1][0] = 0;
        queue2.offer(N);
        count[N][1] = 0;
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            if (!queue1.isEmpty()) {
                int currVer = queue1.poll();
                int currDis = count[currVer][0];
                for (int i = heads[currVer]; i > 0; i = next[i]) {
                    int neighbour = ver[i];
                    int newLength = currDis + getLength(current, edges[i]);
                    if (currVer != neighbour && (count[neighbour][0] == -1 || newLength < count[neighbour][0])) {
                        count[neighbour][0] = newLength;
                        if (neighbour == N && count[neighbour][0] <= K)
                            return true;
                        else if (count[neighbour][1] != -1 && count[neighbour][0] + count[neighbour][1] <= K)
                            return true;
                        else queue1.offer(neighbour);
                    }
                }
            }

            if (!queue2.isEmpty()) {
                int currVer = queue2.poll();
                int currDis = count[currVer][1];
                for (int i = heads[currVer]; i > 0; i = next[i]) {
                    int neighbour = ver[i];
                    int newLength = currDis + getLength(current, edges[i]);
                    if (currVer != neighbour && (count[neighbour][1] == -1 || newLength < count[neighbour][1])) {
                        count[neighbour][1] = newLength;
                        if (neighbour == 1 && count[neighbour][1] <= K)
                            return true;
                        else if (count[neighbour][0] != -1 && count[neighbour][0] + count[neighbour][1] <= K)
                            return true;
                        else queue2.offer(neighbour);
                    }

                }
            }
        }
        return false;
    }

    private static int getLength(int current, int length) {
        return (length > current) ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        P = in.nextInt();
        K = in.nextInt();
        heads = new int[N + 1];
        ver = new int[2 * P + 2];
        edges = new int[2 * P + 2];
        next = new int[2 * P + 2];
        tot = 1;
        upperBound = Integer.MIN_VALUE;
        for (int i = 1; i <= P; i++) add(in.nextInt(), in.nextInt(), in.nextInt());

        int left = 0;
        int right = upperBound;
        while (left < right) {
            int current = (left + right) >> 1;
            if (check(current)) right = current; else left = current + 1;
        }
        System.out.println(((left == upperBound && !check(upperBound) || left > upperBound)) ? -1 : left);
    }
}
