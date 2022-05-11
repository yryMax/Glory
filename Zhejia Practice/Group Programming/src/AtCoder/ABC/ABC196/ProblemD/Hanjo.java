package AtCoder.ABC.ABC196.ProblemD;

import java.awt.*;
import java.util.*;

public class Hanjo {
    static long count = 0;
    static int H;
    static int W;

    private static void dfs(int filled, int bit, int ARemaining, int BRemaining) {
        if (filled == H * W) {
            count++;
            return;
        }

        if ((bit & (1 << filled)) > 0) dfs(filled + 1, bit, ARemaining, BRemaining);
        if (BRemaining > 0) dfs(filled + 1, bit | (1 << filled), ARemaining, BRemaining - 1);
        if (ARemaining > 0) {
            if (filled % W != W - 1 && (~bit & 1 << (filled + 1)) > 0) dfs(filled + 1, bit | (1 << filled) | (1 << (filled + 1)), ARemaining - 1, BRemaining);
            if (filled + W < H * W) dfs(filled + 1, bit | (1 << filled) | (1 << (filled + W)), ARemaining - 1, BRemaining);
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        H = in.nextInt(); W = in.nextInt();
        int A = in.nextInt(), B = in.nextInt();
        dfs(0, 0, A, B);
        System.out.println(count);
    }
}


