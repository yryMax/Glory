package AtCoder.ABC153E;

import java.util.Arrays;
import java.util.Scanner;

public class CrestedIbisVSMonster {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt(), N = in.nextInt();
        int[][] spells = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            spells[i][0] = in.nextInt();
            spells[i][1] = in.nextInt();
        }
        long[][] dp = new long[N + 1][H + 1];
        for (int i = 0; i <= N; i++) for (int j = 0; j <= H; j++) dp[i][j] = Long.MAX_VALUE;
        dp[0][0] = 0;
        for (int spell = 0; spell <= N; spell++) {
            for (int accHarm = 0; accHarm <= H; accHarm++) {
                if (dp[spell][accHarm] == Long.MAX_VALUE) continue;
                dp[spell][Math.min(accHarm + spells[spell][0], H)] = Math.min(
                        dp[spell][Math.min(accHarm + spells[spell][0], H)],
                        dp[spell][accHarm] + spells[spell][1]
                );
                if (spell != N) dp[spell + 1][accHarm] = Math.min(dp[spell + 1][accHarm], dp[spell][accHarm]);
            }
        }
        long ans = Long.MAX_VALUE;
        for (int s = 1; s <= N; s++) ans = Math.min(ans, dp[s][H]);
        System.out.println(ans);
    }
}
