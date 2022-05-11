package AtCoder.ABC.ABC192.ProblemC;

import java.util.Scanner;

public class KaprekarNumber {

    private static long g(String val, boolean on) {
        int[] nums = new int[10];
        for (int i = 0; i < val.length(); i++)
            nums[Integer.parseInt(val.substring(i, i + 1))]++;
        long out = 0;
        int digit = val.length() - 1;
        if (on) {
            for (int i = 0; i <= 9; i++)
                while (nums[i]-- > 0)
                    out += Math.pow(10, digit--) * i;
        } else {
            for (int i = 9; i >= 0; i--)
                while (nums[i]-- > 0)
                    out += Math.pow(10, digit--) * i;
        }
        return out;
    }

    private static String f(String num) {
        return "" + (g(num, false) - g(num, true));
    }




    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String N = in.next();
        int K = in.nextInt();
        String acc = N;
        for (int i = 1; i <= K; i++)
            acc = f(acc);
        System.out.println(acc);
    }
}
