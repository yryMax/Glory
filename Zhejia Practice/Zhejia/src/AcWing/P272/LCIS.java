package AcWing.P272;

import java.util.Scanner;

public class LCIS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arrA = new int[N + 1];
        int[] arrB = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arrA[i] = in.nextInt();
        for (int i = 1; i <= N; i++)
            arrB[i] = in.nextInt();
        int[][] dp = new int[N + 1][N + 1];
        for (int indA = 1; indA <= N; indA++) {
            int currMax = 0;
            currMax = (arrB[0] < arrA[indA]) ? dp[indA - 1][0] : currMax;
            for (int indB = 1; indB <= N; indB++) {
                if (arrA[indA] == arrB[indB])
                    dp[indA][indB] =  Math.max(currMax + 1, dp[indA][indB]);
                else dp[indA][indB] = dp[indA - 1][indB];

                if (arrB[indB] < arrA[indA]) currMax = Math.max(dp[indA][indB], currMax);
            }
        }
        int ans = 0;
        for (int i = 1; i <= N; i++)
            ans = Math.max(ans, dp[N][i]);
        System.out.println(ans);
    }
}
