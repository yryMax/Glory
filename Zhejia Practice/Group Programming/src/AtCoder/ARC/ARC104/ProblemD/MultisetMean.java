package AtCoder.ARC.ARC104.ProblemD;

import java.io.PrintWriter;
import java.util.Scanner;

public class MultisetMean {
    static int N;
    static int K;
    static long M;
    static long[][] dp;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        K = in.nextInt();
        M = in.nextLong();
        dp = new long[N + 1][((1 + N) * N / 2) * K + 1];
        for (int sum = 1; sum <= K; sum++)
            dp[1][sum] = 1;

        for (int last = 1; last <= N; last++)
            dp[last][0] = 1;

        for (int last = 2; last <= N; last++)
            for (int sum = 1; sum <= ((1 + last) * last / 2) * K; sum++) {
                dp[last][sum] = (dp[last - 1][sum] + ((sum >= last) ? dp[last][sum - last] : 0) - ((sum >= (K + 1) * last) ? dp[last - 1][sum - (K + 1) * last] : 0)) % M;
                if (dp[last][sum] < 0) dp[last][sum] += M;
            }


        for (int query = 1; query <= N; query++) {
            long total = 0;
            for (int sum = 1; sum < dp[0].length; sum++) {
                if (dp[N - query][sum] < 0 || dp[query - 1][sum] < 0) continue;
                long temp = (dp[N - query][sum] * dp[query - 1][sum]) % M;
                total += ((K + 1) * temp) % M;
            }
            out.println((total + K) % M);

        }
        out.flush();
    }
}