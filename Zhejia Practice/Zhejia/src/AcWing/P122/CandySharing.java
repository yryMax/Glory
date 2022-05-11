package AcWing.P122;

import java.util.Arrays;
import java.util.Scanner;

public class CandySharing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberChildren = scanner.nextInt();
        long[] children = new long[numberChildren];
        long numberCandies = 0;
        for (int i = 0; i < numberChildren; i++) {
            children[i] = scanner.nextLong();
            numberCandies += children[i];
        }
        System.out.println(findMinimumExchange(children, numberCandies / numberChildren));
    }

    private static long findMinimumExchange(long[] children, long average) {
        long min = 0;
        long[] sum = new long[children.length];
        for (int i = 0; i < children.length; i++) children[i] -= average;
        sum[0] = children[0];
        for (int i = 1; i < sum.length; i++) sum[i] = sum[i - 1] + children[i];
        Arrays.sort(sum);
        int breakPoint = children.length / 2;
        for (long number : sum) min += Math.abs(number - sum[breakPoint]);
        return min;
    }
}
