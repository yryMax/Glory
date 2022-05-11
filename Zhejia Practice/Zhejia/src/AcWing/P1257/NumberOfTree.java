package AcWing.P1257;

import java.math.BigInteger;
import java.util.Scanner;

public class NumberOfTree {
    static BigInteger four = new BigInteger("4");
    static BigInteger minusTwo = new BigInteger("2").negate();
    static BigInteger one = new BigInteger("1");
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        BigInteger ans = new BigInteger("1");
        for (int curr = 2; curr <= N; curr++) {
            BigInteger currVal = new BigInteger(String.valueOf(curr));
            ans = ans.multiply(currVal.multiply(four).add(minusTwo)).divide(currVal.add(one));

        }
        System.out.println(ans);

    }
}
