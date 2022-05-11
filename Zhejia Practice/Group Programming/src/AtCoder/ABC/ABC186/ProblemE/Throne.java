package AtCoder.ABC.ABC186.ProblemE;

import java.io.PrintWriter;
import java.util.Scanner;

public class Throne {
    private static int gcd(int x, int y, Pair p) {
        if (y == 0) {
            p.a = 1;
            p.b = 0;
            return x;
        }
        int r = gcd(y, x % y, p);
        int t = p.b;
        p.b = p.a - (x / y) * p.b;
        p.a = t;
        return r;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        while (T-- > 0) {
            int N = in.nextInt();
            int S = in.nextInt();
            int K = in.nextInt();

            Pair solution = new Pair(0, 0);
            int gcd = gcd(N, -K, solution);
            if (S % gcd != 0) {
                out.println(-1);
                out.flush();
                continue;
            }

            int minS = S / gcd;
            int minN = N / gcd;
            long ans = (long) minS * solution.b - ((long) minS * solution.b / minN) * minN;
            out.println(ans > 0 ? ans : ans + Math.abs(minN));
            out.flush();
        }
    }
}

class Pair {
    int a;
    int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
