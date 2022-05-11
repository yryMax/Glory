package AtCoder.ABC.ABC195.ProblemC;

import java.util.Scanner;

public class Comma {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long N = in.nextLong();
        int digit = 0;
        while (digit <= 15) {
            if (N / (long)  Math.pow(10, digit) == 0)
                break;
            digit++;
        }
        digit--;
        long ans = 0;
        long numMax = digit / 3;
        ans += numMax * (N - (double) Math.pow(10, digit) + 1);
        for (int d = 0; d < digit; d++) {
            ans += d / 3 * (Math.pow(10, d + 1) - Math.pow(10, d));
        }
        System.out.println(ans);
    }
}
