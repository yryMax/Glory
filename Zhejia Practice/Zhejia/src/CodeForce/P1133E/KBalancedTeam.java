package CodeForce.P1133E;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class KBalancedTeam {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int numStudent = in.nextInt();
        int maxTeam = in.nextInt();
        int[] students = new int[numStudent];
        for (int i = 0; i < numStudent; i++)
            students[i] = in.nextInt();

        Arrays.sort(students);
        int[][] dp = new int[maxTeam + 1][numStudent];
        for (int c = 1; c < numStudent; c++) {
            int target = students[c] - 5;
            int right = c;
            int left = 0;
            while (left < right) {
                int mid = (right + left) >> 1;
                if (students[mid] >= target) right = mid; else left = mid + 1;
            }
            dp[1][c] = Math.max(c - left + 1, dp[1][c - 1]);
        }

        for (int i = 0; i <= maxTeam; i++) dp[i][0] = 1;
        for (int r = 2; r <= maxTeam; r++) {
            for (int c = 1; c < numStudent; c++) {
                int target = students[c] - 5;
                int right = c;
                int left = 0;
                while (left < right) {
                    int mid = (right + left) >> 1;
                    if (students[mid] >= target) right = mid; else left = mid + 1;
                }
                dp[r][c] =  Math.max(c - left + 1 + ((left - 1 >= 0) ? dp[r - 1][left - 1] : 0), dp[r][c - 1]);
            }
        }

        out.println(dp[maxTeam][numStudent - 1]);
        out.flush();
    }

}
