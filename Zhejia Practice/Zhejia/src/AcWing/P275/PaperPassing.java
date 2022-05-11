package AcWing.P275;

import java.util.Scanner;

public class PaperPassing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt();
        int[][] friends = new int[N + 1][M + 1];
        long[][][] dp = new long[M + N - 1][N + 1][N + 1];
        for (int i = 1; i <= N; i++) for (int j = 1; j <= M; j++) friends[i][j] = in.nextInt();
        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j <= N; j++)
                for (int k = 0; k <= N; k++)
                    dp[i][j][k] = Long.MIN_VALUE;

        dp[0][1][1] = 0;

        for (int len = 0; len < dp.length; len++) {
            for (int foreX = 1; foreX <= N; foreX++) {
                for (int backX = 1; backX <= N; backX++) {
                    if (dp[len][foreX][backX] == Long.MIN_VALUE) continue;
                    int foreY = len + 2 - foreX, backY = len + 2 - backX;
                    long accVal = dp[len][foreX][backX];
                    if (foreX < N && backY < M && (foreX + 1 != backX && foreY != backY + 1 || len == dp.length - 2)) dp[len + 1][foreX + 1][backX] = Math.max(dp[len + 1][foreX + 1][backX], accVal + friends[foreX + 1][foreY] + friends[backX][backY + 1]);
                    if (foreY < M && backX < N && (foreY != backY + 1 && backX + 1 != foreX || len == dp.length - 2)) dp[len + 1][foreX][backX + 1] = Math.max(dp[len + 1][foreX][backX + 1], accVal + friends[foreX][foreY + 1] + friends[backX + 1][backY]);
                    if (len != 0 && foreX < N && backX < N) dp[len + 1][foreX + 1][backX + 1] = Math.max(dp[len + 1][foreX + 1][backX + 1], accVal + friends[foreX + 1][foreY] + friends[backX + 1][backY]);
                    if (len != 0 && foreY < M && backY < M) dp[len + 1][foreX][backX] = Math.max(dp[len + 1][foreX][backX], accVal + friends[foreX][foreY + 1] + friends[backX][backY + 1]);
                }
            }
        }
        System.out.println(dp[dp.length - 1][N][N]);
    }
}
