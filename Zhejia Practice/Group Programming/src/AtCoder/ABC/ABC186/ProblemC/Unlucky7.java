package AtCoder.ABC.ABC186.ProblemC;

import java.io.PrintWriter;
import java.util.Scanner;

public class Unlucky7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        int[] dp = new int[N + 1];
        dp[1] = 1;
        Main:
        for (int num = 1; num <= N; num++) {
            int temp = num;
            while (temp > 0) {
                int mod = temp % 10;
                if (mod == 7) {dp[num] = dp[num - 1]; continue Main;}
                temp /= 10;
            }
            temp = num;
            while (temp > 0) {
                int mod = temp % 8;
                if (mod == 7) {dp[num] = dp[num - 1]; continue Main;}
                temp /= 8;
            }
            dp[num] = dp[num - 1] + 1;
        }
        out.println(dp[N]);
        out.flush();
    }

}
