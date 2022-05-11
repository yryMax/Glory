package AtCoder.ABC.ABC193.ProblemB;

import java.util.Scanner;

public class PlaySnuke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] shops = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            shops[i][0] = in.nextInt();
            shops[i][1] = in.nextInt();
            shops[i][2] = in.nextInt();
            shops[i][2] -= shops[i][0];
        }

        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= N; i++)
            if (shops[i][2] > 0)
                ans = Math.min(ans, shops[i][1]);
        System.out.println((ans == Long.MAX_VALUE) ? -1 : ans);
    }
}
