package AtCoder.ABC.ABC197.ProblemE;

import java.util.Scanner;

public class Traveler {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] balls = new int[N][2];
        int[][] colours = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            colours[i][1] = Integer.MAX_VALUE;
            colours[i][2] = Integer.MIN_VALUE;
        }
        long ans = 0;
        for (int i = 0; i < N; i++) {
            balls[i][0] = in.nextInt();
            balls[i][1] = in.nextInt();
            int currColor = balls[i][1];
            colours[currColor][0]++;
            colours[currColor][1] = Math.min(colours[currColor][1], balls[i][0]);
            colours[currColor][2] = Math.max(colours[currColor][2], balls[i][0]);
        }
        for (int i = 1; i <= N; i++) {
            if (colours[i][1] == Integer.MAX_VALUE) continue;
            ans += (colours[i][2] - colours[i][1]);
        }

        long[][] dp = new long[N + 1][2];
        long lastLeft = 0;
        long lastRight = 0;
        for (int level = 1; level <= N; level++) {
            if (colours[level][0] == 0) {
                dp[level][0] = dp[level - 1][0];
                dp[level][1] = dp[level - 1][1];
                continue;
            }
            long prevDis = lastRight - lastLeft;
            dp[level][0] = Math.min(dp[level - 1][0] + (Math.abs(colours[level][2] - lastLeft)),
                                        dp[level - 1][1] + Math.abs(colours[level][2] - lastRight));

            dp[level][1] = Math.min(dp[level - 1][0] + (Math.abs(colours[level][1] - lastLeft)),
                    dp[level - 1][1] + (Math.abs(colours[level][1] - lastRight)));
            lastLeft = colours[level][1];
            lastRight = colours[level][2];
        }


        System.out.println(ans + Math.min(dp[N][0] + Math.abs(lastLeft), dp[N][1] + Math.abs(lastRight)));
    }

}
