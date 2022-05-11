package AtCoder.ABC183H;

import java.util.Scanner;

public class QueenOnGrid {
    static long EXTREME = (long) Math.pow(10, 9) + 7;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt(), W = in.nextInt();
        char[][] grids = new char[H + 1][W + 1];
        in.nextLine();
        for (int h = 1; h <= H; h++) {
            String line = in.nextLine();
            for (int w = 1; w <= W; w++) grids[h][w] = line.charAt(w - 1);
        }

        long[][][] dp = new long[H + 1][W + 1][4];
        dp[1][1][0] = 1;
        for (int h = 1; h <= H; h++) {
            for (int w = 1; w <= W; w++) {
                if (grids[h][w] == '#') continue;
                if (dp[h][w][0] == 0) dp[h][w][0] = (dp[h][w][1] + dp[h][w][2] + dp[h][w][3]) % EXTREME;
                if (h != H) dp[h + 1][w][1] = (dp[h + 1][w][1] + dp[h][w][0] + dp[h][w][1]) % EXTREME;
                if (w != W) dp[h][w + 1][2] = (dp[h][w + 1][2] + dp[h][w][0] + dp[h][w][2]) % EXTREME;
                if (h != H && w != W) dp[h + 1][w + 1][3] = (dp[h + 1][w + 1][3] + dp[h][w][0] + dp[h][w][3]) % EXTREME;
            }
        }
        System.out.println((dp[H][W][1] + dp[H][W][2] + dp[H][W][3]) % EXTREME);
    }
}
