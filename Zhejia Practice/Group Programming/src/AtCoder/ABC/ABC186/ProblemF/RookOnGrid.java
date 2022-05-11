package AtCoder.ABC.ABC186.ProblemF;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class RookOnGrid {
    static int[] BIT;

    private static long query(int x) {
        long ans = 0;
        for (; x > 0; x -= x & -x ) ans += BIT[x];
        return ans;
    }

    private static void update(int p) {
        for (; p < BIT.length; p += p & -p) BIT[p] += 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int H = in.nextInt();
        int W = in.nextInt();
        int M = in.nextInt();
        BIT = new int[H + 1];
        if (M == 0) {
            out.println((long) H * W);
            out.flush();
            return;
        }
        int[][] obstacles = new int[M][2];
        for (int i = 0; i < M; i++) {
            obstacles[i][0] = in.nextInt();
            obstacles[i][1] = in.nextInt();
        }

        Arrays.sort(obstacles, (int[] o1, int[] o2) -> (o1[0] != o2[0]) ? o1[0] - o2[0] : o1[1] - o2[1]);
        int Climit = (obstacles[0][0] == 1) ? obstacles[0][1] - 1 : W;
        long ans = 0;
        int ind = 0;
        for (int i = 1; i <= H; i++) {
            if (ind >= M || obstacles[ind][0] != i) ans += W;
            else {
                ans += obstacles[ind][1] - 1;
                if (obstacles[ind][1] == 1) break;
                while (ind < M && obstacles[ind][0] == i) ind++;
            }
        }

        boolean[] appear = new boolean[H + 1];
        Arrays.sort(obstacles, (int[] o1, int[] o2) -> (o1[1] != o2[1]) ? o1[1] - o2[1] : o1[0] - o2[0]);
        if (obstacles[0][1] == 1)
            for (int i = obstacles[0][0]; i <= H; i++) {
                update(i);
                appear[i] = true;
            }
        else {
            update(obstacles[0][0]);
            appear[obstacles[0][0]] = true;
        }

        ind = 0;

        for (int i = obstacles[0][1] + 1; i <= Climit; i++) {
            while (ind < M && obstacles[ind][1] < i) {
                if (!appear[obstacles[ind][0]]) {
                    update(obstacles[ind][0]);
                    appear[obstacles[ind][0]] = true;
                }
                ind++;
            }
            ans += (ind < M && obstacles[ind][1] == i) ? query(obstacles[ind][0] - 1) : query(H);
            while (ind < M && obstacles[ind][1] == i) {
                if (!appear[obstacles[ind][0]]) {
                    update(obstacles[ind][0]);
                    appear[obstacles[ind][0]] = true;
                }
                ind++;
            }
        }

        out.println(ans);
        out.flush();
    }
}
