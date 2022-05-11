package AcWing.P278;

import java.util.Scanner;

public class NumberCombination {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt();
        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) numbers[i] = in.nextInt();
        long[] dp = new long[M + 1];
        dp[0] = 1;
        for (int num = 1; num <= N; num++)
            for (int weight = M; weight > 0; weight--)
                if (weight - numbers[num] >= 0) dp[weight] += dp[weight - numbers[num]];
        System.out.println(dp[M]);
    }
}
