package AcWing.P314;

import java.util.*;

public class BuyLowBuyLower {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] prices = new int[N];
        for (int i = 0; i < N; i++)
            prices[i] = in.nextInt();

        int ans = Integer.MIN_VALUE;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int curr = 0; curr < N; curr++) {
            for (int nxt = curr + 1; nxt < N; nxt++) {
                if (prices[nxt] >= prices[curr] || dp[curr] + 1 < dp[nxt]) continue;
                if (dp[curr] + 1 > dp[nxt]) {
                    dp[nxt] = dp[curr] + 1;
                }
            }
        }

        for (int i = 0; i < N; i++) if (dp[i] > ans) {
            ans = dp[i];
        }

        int[] dpCount = new int[N];

        for (int i = 0; i < N; i++) if (dp[i] == 1) dpCount[i] = 1;
        int total = 0;
        for (int curr = 1; curr < N; curr++) {
            if (dpCount[curr] == 1) continue;
            HashSet<Integer> already = new HashSet<>();
            for (int prev = curr - 1; prev >= 0; prev--) {
                if (prices[prev] <= prices[curr] || already.contains(prices[prev]) || dp[prev] + 1 != dp[curr]) continue;
                dpCount[curr] += dpCount[prev];
                already.add(prices[prev]);
            }
        }
        HashSet<Integer> already = new HashSet<>();
        for (int curr = N - 1; curr >= 0; curr--) {
            if (dp[curr] != ans || already.contains(prices[curr])) continue;
            total += dpCount[curr];
            already.add(prices[curr]);
        }
        System.out.println(ans + " " + total);
    }
}
