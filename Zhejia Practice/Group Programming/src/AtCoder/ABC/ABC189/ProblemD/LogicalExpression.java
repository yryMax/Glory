package AtCoder.ABC.ABC189.ProblemD;

import java.util.Scanner;

public class LogicalExpression {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] operators = new int[N + 1];
        in.nextLine();
        for (int i = 1; i <= N; i++) operators[i] = in.nextLine().equals("AND") ? 1 : 0;
        long[][] dp = new long[N + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int curr = 1; curr <= N; curr++) {
            if (operators[curr] == 1) {
                dp[curr][0] = 2 * dp[curr - 1][0] + dp[curr - 1][1];
                dp[curr][1] = dp[curr - 1][1];
            } else {
                dp[curr][0] = dp[curr - 1][0];
                dp[curr][1] = dp[curr - 1][0] + 2 * dp[curr - 1][1];
            }
        }
        System.out.println(dp[N][1]);
    }
}
