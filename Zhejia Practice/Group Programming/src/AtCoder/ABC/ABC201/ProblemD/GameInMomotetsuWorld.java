package AtCoder.ABC.ABC201.ProblemD;

import java.util.Scanner;

public class GameInMomotetsuWorld {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt(), W = in.nextInt();
        int[][] space = new int[H + 1][W + 1];
        in.nextLine();
        for (int h = 1; h <= H; h++) {
            String line = in.nextLine();
            for (int w = 1; w <= W; w++) space[h][w] = line.charAt(w - 1) == '-' ? -1 : 1;
        }
        long[][] dp = new long[H + 1][W + 1];
        for (int h = H; h >= 1; h--) {
            for (int w = W; w >= 1; w--) {
                if (h == H && w == W) continue;
                if ((h + w) % 2 == 0) {
                    long option1 = h != H ? dp[h + 1][w] + space[h + 1][w] : Long.MIN_VALUE;
                    long option2 = w != W ? dp[h][w + 1] + space[h][w + 1] : Long.MIN_VALUE;
                    dp[h][w] = Math.max(option1, option2);
                } else {
                    long option1 = h != H ? dp[h + 1][w] - space[h + 1][w] : Long.MAX_VALUE;
                    long option2 = w != W ? dp[h][w + 1] - space[h][w + 1] : Long.MAX_VALUE;
                    dp[h][w] = Math.min(option1, option2);
                }
            }
        }
        if (dp[1][1] > 0) System.out.println("Takahashi");
        else if (dp[1][1] < 0) System.out.println("Aoki");
        else System.out.println("Draw");
    }
}
