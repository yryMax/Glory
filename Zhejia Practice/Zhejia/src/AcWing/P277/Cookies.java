package AcWing.P277;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Cookies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numChildren = in.nextInt();
        int numCookie = in.nextInt();
        int[][] angers = new int[numChildren + 1][2];
        angers[0][0] = Integer.MAX_VALUE;
        for (int i = 1; i <= numChildren; i++) {
            angers[i][0] = in.nextInt();
            angers[i][1] = i;
        }
        Arrays.sort(angers, (int[] a, int[] b) -> -a[0] + b[0]);
        angers[0][0] = 0;
        long[] accumulation = new long[numChildren + 1];
        for (int i = 1; i <= numChildren; i++) accumulation[i] = angers[i][0] + accumulation[i - 1];

        long[][] dp = new long[numChildren + 1][numCookie + 1];
        for (long[] a : dp) Arrays.fill(a, Long.MAX_VALUE);
        dp[0][0] = 0;
        for (int child = 1; child <= numChildren; child++) {
            for (int cookie = child; cookie <= numCookie; cookie++) {
                dp[child][cookie] = dp[child][cookie - child];
                for (int capitalist = 0; capitalist < child; capitalist++)
                    dp[child][cookie] = Math.min(dp[child][cookie], dp[capitalist][cookie - (child - capitalist)] + capitalist * (accumulation[child] - accumulation[capitalist]));
            }
        }

        long[] ans = new long[numChildren + 1];
        int indChild = numChildren, indCookie = numCookie, level = 0;
        while (indChild > 0 && indCookie > 0) {
            if (indCookie >= indChild && dp[indChild][indCookie] == dp[indChild][indCookie - indChild]) {
                indCookie -= indChild;
                level++;
            } else {
                for (int poorChild = 1; poorChild <= indChild; poorChild++) {
                    if (dp[indChild][indCookie] != dp[indChild - poorChild][indCookie - poorChild] + (accumulation[indChild] - accumulation[indChild - poorChild]) * (indChild - poorChild)) continue;
                    for (int pnt = indChild; pnt > indChild - poorChild; pnt--) ans[angers[pnt][1]] = 1 + level;
                    indChild -= poorChild; indCookie -= poorChild;
                    break;
                }
            }
        }

        out.println(dp[numChildren][numCookie]);
        for (int i = 1; i <= numChildren; i++) out.print(ans[i] + " ");
        out.flush();

    }
}
