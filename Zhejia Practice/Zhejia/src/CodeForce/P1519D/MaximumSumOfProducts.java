package CodeForce.P1519D;

import java.util.Scanner;

public class MaximumSumOfProducts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        long[] albert = new long[size];
        long[] bob = new long[size];

        for (int i = 0; i < size; i++) albert[i] = scanner.nextLong();
        for (int i = 0; i < size; i++) bob[i] = scanner.nextLong();
        long sum = 0;
        for (int i = 0; i < size; i++) sum += albert[i] * bob[i];

        long[][] record = new long[size][size];
        for (int i = 0; i < size; i++) record[i][i] = 0L;
        long maxDelta = 0;
        for (int row = 1; row < size; row++) {
            record[row][row - 1] = (albert[row - 1] * bob[row] + albert[row] * bob[row - 1]) - (albert[row - 1] * bob[row - 1] + albert[row] * bob[row]);
            maxDelta = Math.max(maxDelta, record[row][row - 1]);
        }

        for (int diagonal = 1; diagonal < size - 1; diagonal++) {
            int delta = 1;
            while (diagonal - delta >= 0 && diagonal + delta < size) {
                record[diagonal + delta][diagonal - delta] = record[diagonal + delta - 1][diagonal - delta + 1]
                                                            + ((albert[diagonal - delta] * bob[diagonal + delta] + albert[diagonal + delta] * bob[diagonal - delta])
                                                                - (albert[diagonal - delta] * bob[diagonal - delta] + albert[diagonal + delta] * bob[diagonal + delta]));
                maxDelta = Math.max(maxDelta, record[diagonal + delta][diagonal - delta]);
                delta++;
            }
        }

        for (int row = 2; row < size - 1; row++) {
            int col = row - 1;
            int delta = 1;
            while (row + delta < size && col - delta >= 0) {
                record[row + delta][col - delta] = record[row + delta - 1][col - delta + 1]
                        + ((albert[row + delta] * bob[col - delta] + albert[col - delta] * bob[row + delta])
                        - (albert[row + delta] * bob[row + delta] + albert[col - delta] * bob[col - delta]));
                maxDelta = Math.max(maxDelta, record[row + delta][col - delta]);
                delta++;
            }
        }

        System.out.println(sum + maxDelta);
    }
}
