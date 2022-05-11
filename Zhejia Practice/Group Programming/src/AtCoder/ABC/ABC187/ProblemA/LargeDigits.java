package AtCoder.ABC.ABC187.ProblemA;

import java.util.Scanner;

public class LargeDigits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        int B = in.nextInt();
        System.out.println(Math.max(getSum(A), getSum(B)));
    }

    private static int getSum(int num) {
        int ans = 0;
        while (num > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }
}
