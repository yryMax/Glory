package AtCoder.ABC155E;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Payment {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            char[] n = scanner.next().toCharArray();
            int l = n.length;
            long[][] dp = new long[l + 1][2];
            Arrays.stream(dp).forEach(dpi -> Arrays.fill(dpi, 0L));
            dp[0][1] = 1;
            IntStream.range(0, l).forEach(i -> {
                int digit = n[i] - '0';
                dp[i + 1][0] = Math.min(dp[i][0] + digit, dp[i][1] + 10 - digit);
                dp[i + 1][1] = Math.min(dp[i][0] + digit + 1, dp[i][1] + 10 - digit - 1);
            });
            System.out.println(dp[l][0]);
        }
    }
}
