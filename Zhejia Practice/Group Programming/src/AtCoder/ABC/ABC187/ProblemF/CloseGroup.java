package AtCoder.ABC.ABC187.ProblemF;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CloseGroup {
    static int[][] matrix;
    static int[] tested;

    private static boolean isComplete(int mask) {
        if (tested[mask] != - 1) return tested[mask] != 0;
        List<Integer> oneBits = new ArrayList<>();
        int temp = mask;
        while (temp > 0) {
            oneBits.add((int) (Math.log10(temp & -temp) / Math.log10(2)) + 1);
            temp -= (temp & -temp);
        }
        for (int i = 0; i < oneBits.size(); i++) {
            for (int j = i + 1; j < oneBits.size(); j++) {
                if (matrix[oneBits.get(i)][oneBits.get(j)] == 0) {
                    tested[mask] = 0;
                    return false;
                }
            }
        }
        tested[mask] = 1;
        return true;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int M = in.nextInt();
        if (M == 0) {
            out.println(N);
            out.flush();
            return;
        }
        matrix = new int[N + 1][N + 1];
        tested = new int[1 << N];
        Arrays.fill(tested, -1);
        for (int i = 1; i <= M; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            matrix[v1][v2] = 1;
            matrix[v2][v1] = 1;
        }
        int[] dp = new int[1 << N]; // Each bitmask represents a subset of the graph;
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            if (isComplete(i)) {
                dp[i] = 1;
                continue;
            }
            for (int s = i; s > 0; s = (s - 1) & i) {
                dp[i] = Math.min(dp[i], dp[s] + dp[i - s]);
            }
        }
        out.println(dp[(1 << N) - 1]);
        out.flush();
    }
}
