package AtCoder.ABC.ABC199.ProblemE;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Permutation {
    static int N;
    static int ULTIMATE;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        int M = in.nextInt();
        int[][] restrictions = new int[M + 1][3];
        for (int i = 1; i <= M; i++) {
            restrictions[i][0] = in.nextInt();
            restrictions[i][1] = in.nextInt();
            restrictions[i][2] = in.nextInt();
        }

        if (M == 0) edgeCase();

        ULTIMATE = 1 << (N + 1);
        long[] dp = new long[ULTIMATE];
        dp[1] = 1;
        for (int bitmask = 1; bitmask < ULTIMATE; bitmask++) {
            int currentBitsCount = Integer.bitCount(bitmask) - 1;
            for (int restriction = 1; restriction <= M; restriction++) {
                if (restrictions[restriction][0] != currentBitsCount) continue;
                int checkBit = (1 << (restrictions[restriction][1] + 1)) - 1;
                int numOutcome = Integer.bitCount(checkBit & bitmask) - 1;
                if (numOutcome > restrictions[restriction][2]) {
                    dp[bitmask] = 0;
                    break;
                }
            }
            for (int next = 1; next <= N; next++) {
                if ((bitmask & (1 << next)) != 0) continue;
                dp[bitmask | (1 << next)] += dp[bitmask];
            }
        }
        System.out.println(dp[ULTIMATE - 1]);

    }



    private static void edgeCase() {
        long ans = 1;
        for (int i = 1; i <= N; i++) ans *= i;
        System.out.println(ans);
        System.exit(0);
    }
}
