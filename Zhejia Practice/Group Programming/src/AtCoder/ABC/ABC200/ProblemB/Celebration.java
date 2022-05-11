package AtCoder.ABC.ABC200.ProblemB;

import java.util.Scanner;

public class Celebration {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long N = in.nextLong();
        int K = in.nextInt();
        while (K-- > 0) {
            if (N % 200 == 0) N /= 200;
            else N = 1000 * N + 200;
        }
        System.out.println(N);

    }
}
