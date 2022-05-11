import java.io.PrintWriter;
import java.util.Scanner;

// https://codeforces.com/contest/1352/problem/C
public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int cases = sc.nextInt();

        while (cases-- > 0) {
            long n = sc.nextLong(); // divisor
            long k = sc.nextLong(); // k-th

            long step = n-1;

            long times = k / step;

            long remainStep = k - times*step;

            if (remainStep != 0) {
                pw.println(times*n+remainStep);
            } else {
                pw.println(times*n-1);
            }
        }
        pw.close();
    }
}
