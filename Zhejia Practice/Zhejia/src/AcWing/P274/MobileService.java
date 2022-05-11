package AcWing.P274;

import java.util.Scanner;

public class MobileService {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int R = in.nextInt();
        int[][] costs = new int[L + 1][L + 1];
        for (int i = 1; i <= L; i++)
            for (int j = 1; j <= L; j++)
                costs[i][j] = in.nextInt();
        int[] requests = new int[R + 1];
        requests[0] = 3;
        for (int i = 1; i <= R; i++) requests[i] = in.nextInt();
        long[][][] dp = new long[R + 1][L + 1][L + 1];
        for (int i = 0; i <= R; i++) for (int j = 0; j <= L; j++) for (int k = 0; k <= L; k++) dp[i][j][k] = Long.MAX_VALUE;
        dp[0][1][2] = 0;
        for (int request = 0; request < R; request++) {
            int currPos = requests[request];
            int destination = requests[request + 1];
            for (int posA = 1; posA <= L; posA++) {
                for (int posB = 1; posB <= L; posB++) {
                    if (dp[request][posA][posB] == Long.MAX_VALUE) continue;
                    if (posA == posB || posA == currPos || posB == currPos) continue;
                    if (destination == currPos || destination == posA || destination == posB) {
                        if (destination == currPos) dp[request + 1][posA][posB] = Math.min(dp[request + 1][posA][posB], dp[request][posA][posB]);
                        else if (destination == posA) dp[request + 1][currPos][posB] = Math.min(dp[request + 1][currPos][posB], dp[request][posA][posB]);
                        else dp[request + 1][posA][currPos] = Math.min(dp[request + 1][posA][currPos], dp[request][posA][posB]);
                        continue;
                    }
                    dp[request + 1][posA][posB] = Math.min(dp[request + 1][posA][posB], dp[request][posA][posB] + costs[currPos][destination]);
                    dp[request + 1][currPos][posB] = Math.min(dp[request + 1][currPos][posB], dp[request][posA][posB] + costs[posA][destination]);
                    dp[request + 1][posA][currPos] = Math.min(dp[request + 1][posA][currPos], dp[request][posA][posB] + costs[posB][destination]);
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int posA = 1; posA <= L; posA++) {
            for (int posB = 1; posB <= L; posB++) {
                if (posA == posB) continue;
                ans = Math.min(ans, dp[R][posA][posB]);
            }
        }
        System.out.println(ans);

    }
}
