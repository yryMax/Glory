package AtCoder.ABC.ABC194.ProblemD;

import java.util.Scanner;

public class Journey {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        double ans = 0;
        for (int i = N - 1; i >= 1; i--) ans += (N + 0.0) / i;
        System.out.println(ans);
    }
}
