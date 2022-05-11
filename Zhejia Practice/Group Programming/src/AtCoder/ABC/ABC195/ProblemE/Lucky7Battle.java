package AtCoder.ABC.ABC195.ProblemE;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lucky7Battle {
    static String T = "Takahashi";
    static String A = "Aoki";
    static int N;
    static String S;
    static String X;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        S = in.next();
        X = in.next();

        Set<Integer>[] dp = new Set[N];
        for (int i = 0; i < N; i++)
            dp[i] = new HashSet<Integer>();
        dp[N - 1].add(0);
        for (int ind = N - 1; ind >= 1; ind--) {
            int curr = Integer.parseInt(String.valueOf(S.charAt(ind)));
            if (X.charAt(ind) == 'T') {
                for (int choice = 0; choice <= 6; choice++)
                    if (dp[ind].contains(choice * 10 % 7) || dp[ind].contains((choice * 10 + curr) % 7))
                        dp[ind - 1].add(choice);
            } else {
                for (int choice = 0; choice <= 6; choice++)
                    if (dp[ind].contains(choice * 10 % 7) && dp[ind].contains((choice * 10 + curr) % 7))
                        dp[ind - 1].add(choice);
            }
        }
        if (X.charAt(0) == 'T')
            System.out.println(dp[0].contains(0) || dp[0].contains(Integer.parseInt(String.valueOf(S.charAt(0))) % 7) ? T : A);
        else System.out.println(dp[0].contains(0) && dp[0].contains(Integer.parseInt(String.valueOf(S.charAt(0))) % 7) ? T : A);
    }
}
