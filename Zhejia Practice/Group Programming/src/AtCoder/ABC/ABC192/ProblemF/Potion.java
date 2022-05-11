package AtCoder.ABC.ABC192.ProblemF;

import java.util.Scanner;

public class Potion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        long X = in.nextLong();
        long[] potions = new long[N + 1];
        for (int i = 1; i <= N; i++)
            potions[i] = in.nextLong();

        long ans = Long.MAX_VALUE;
        for (int domain = 1; domain <= N; domain++) {
            int smallX = (int) (X % domain);
            long[][][] dp = new long[N + 1][domain + 1][domain];


            for (int last = 0; last < N; last++) {

                for (int numPotion = 0; numPotion <= domain; numPotion++) {
                    for (int kernel = 0; kernel < domain; kernel++) {
                        // Option 1: choose the next potion
                        if (numPotion > last) continue;
                        if (numPotion + 1 <= domain) {
                            long newVol = (numPotion > 0 && dp[last][numPotion][kernel] == 0) ? dp[last][numPotion][kernel] : dp[last][numPotion][kernel] + potions[last + 1];
                            int newKernel = (int) (newVol % domain);
                            dp[last + 1][numPotion + 1][newKernel] = Math.max(dp[last + 1][numPotion + 1][newKernel], newVol);
                        }

                        // Option 2: not choose the next potion
                        dp[last + 1][numPotion][kernel] = Math.max(dp[last + 1][numPotion][kernel], dp[last][numPotion][kernel]);
                    }
                }
            }

            if (dp[N][domain][smallX] != 0) ans = Math.min(ans, (X - dp[N][domain][smallX]) / domain);
        }
        System.out.println(ans);
    }
}
