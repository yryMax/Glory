package AcWing.P313;

import java.io.PrintWriter;
import java.util.Scanner;

public class FlowerWindowShop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int F = in.nextInt();
        int V = in.nextInt();
        int[][] beauty = new int[F + 1][V + 1];
        for (int f = 1; f <= F; f++)
            for (int v = 1; v <= V; v++)
                beauty[f][v] = in.nextInt();
        int[][] prev = new int[F + 1][V + 1];
        long[][] dp = new long[F + 1][V + 1];
        for (int v = 1; v <= V; v++)
            dp[1][v] = beauty[1][v];

        for (int r = 2; r <= F; r++) {
            long currMax = dp[r - 1][r - 1];
            int prevInd = r - 1;
            for (int c = r; c <= V; c++) {
                dp[r][c] = currMax + beauty[r][c];
                prev[r][c] = prevInd;

                if (dp[r - 1][c] > currMax) {
                    currMax = dp[r - 1][c];
                    prevInd = c;
                }
            }
        }

        int maxInd = 0;
        long currMax = Long.MIN_VALUE;
        for (int v = F; v <= V; v++) {
            if (dp[F][v] > currMax) {
                currMax = dp[F][v];
                maxInd = v;
            }
        }
        out.println(currMax);

        int[] output = new int[F + 1];
        int point = maxInd;
        int index = F;
        while (point != 0) {
            output[index] = point;
            point = prev[index--][point];
        }

        for (int f = 1; f <= F ; f++) out.print(output[f] + " ");
        out.flush();
    }
}
