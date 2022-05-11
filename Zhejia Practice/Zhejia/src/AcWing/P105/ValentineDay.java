package AcWing.P105;

import java.util.Arrays;
import java.util.Scanner;

public class ValentineDay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberRow = scanner.nextInt();
        int numberCol = scanner.nextInt();
        int numberInterest = scanner.nextInt();
        int[] rows = new int[numberRow];
        int[] cols = new int[numberCol];

        for (int i = 0; i < numberInterest; i++) {
            rows[scanner.nextInt() - 1]++;
            cols[scanner.nextInt() - 1]++;
        }

        if (numberInterest % numberRow != 0 && numberInterest % numberCol != 0) {
            System.out.print("impossible");
        } else if (numberInterest % numberRow == 0 && numberInterest % numberCol != 0) {
            System.out.print("row ");
            System.out.print(findMinimumExchange(rows, numberInterest / numberRow));
        } else if (numberInterest % numberRow != 0 && numberInterest % numberCol == 0) {
            System.out.print("column ");
            System.out.print(findMinimumExchange(cols, numberInterest / numberCol));
        } else {
            System.out.print("both ");
            System.out.print(findMinimumExchange(rows, numberInterest / numberRow) +
                                findMinimumExchange(cols, numberInterest / numberCol));
        }

    }

    private static long findMinimumExchange(int[] array, int average) {
        for (int i = 0; i < array.length; i++) array[i] -= average;
        long[] sum = new long[array.length];
        sum[0] = array[0];
        for (int i = 1; i < sum.length; i++) sum[i] = sum[i - 1] + array[i];
        Arrays.sort(sum);
        int breakPoint = array.length / 2;
        long min = 0;
        for (int i = 0; i < array.length; i++) min += Math.abs(sum[i] - sum[breakPoint]);
        return min;
    }
}
