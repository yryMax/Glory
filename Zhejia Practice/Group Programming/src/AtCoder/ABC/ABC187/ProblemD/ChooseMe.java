package AtCoder.ABC.ABC187.ProblemD;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ChooseMe {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] towns = new int[N + 1][2];

        towns[0][0] = Integer.MAX_VALUE;
        towns[0][1] = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            towns[i][0] = in.nextInt();
            towns[i][1] = in.nextInt();
        }

        Arrays.sort(towns, Comparator.comparingLong((int[] a) -> - (long)((long) 2 * a[0] + a[1])));
        towns[0][0] = 0;
        towns[0][1] = 0;
        long[] sumA = new long[N + 1];
        long[] sumT = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            sumA[i] = sumA[i - 1] + (long) towns[i][0];
            sumT[i] = sumT[i - 1] + (long) towns[i][1];
        }

        int left = 1;
        int right = N;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (
                    sumA[mid] + sumT[mid] > sumA[N] - sumA[mid]
            ) right = mid; else left = mid + 1;
        }
        System.out.println(left);
    }
}
