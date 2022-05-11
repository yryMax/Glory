package AtCoder.ABC.ABC199.ProblemC;

import java.io.PrintWriter;
import java.util.Scanner;

public class IPFL {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        String S = in.next();
        int Q = in.nextInt();
        char[] chars = new char[2 * N];
        for (int i = 0; i < 2 * N; i++) chars[i] = S.charAt(i);
        int rotation = 0;
        while (Q-- > 0) {
            int T = in.nextInt();
            int A = in.nextInt() - 1;
            int B = in.nextInt() - 1;
            if (T == 1) {
                if (rotation % 2 == 1) {
                    A = (A + N) % (2 * N);
                    B = (B + N) % (2 * N);
                }
                char ch = chars[A];
                chars[A] = chars[B];
                chars[B] = ch;
            } else rotation++;
        }
        for (int i = 0; i < 2 * N; i++) {
            int t = i;
            if (rotation % 2 == 1) t = (t + N) % (2 * N);
            out.print(chars[t]);
        }
        out.flush();
    }
}
