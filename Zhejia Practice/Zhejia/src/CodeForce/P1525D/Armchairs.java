package CodeForce.P1525D;

import java.util.Scanner;

public class Armchairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] chairs = new int[num];
        int numOne = 0;
        for (int i = 0; i < num; i++) {
            chairs[i] = scanner.nextInt();
            if (chairs[i] == 1) numOne++;
        }
        if (numOne == 0) {
            System.out.println(0);
            return;
        }
        int one = 0;
        int zero = 0;

        int[] ones = new int[numOne];
        int[] zeros = new int[num - numOne];

        for (int i = 0; i < num; i++) {
            if (chairs[i] == 0) zeros[zero++] = i;
            else ones[one++] = i;
        }

        long[][] nums = new long[numOne][num - numOne];
        for (int c = 0; c < num - numOne; c++)
            nums[0][c] = Math.abs(ones[0] - zeros[c]);

        for (int r = 1; r < numOne; r++) {
            long min = nums[r - 1][r - 1];
            for (int c = r; c < num - numOne; c++) {
                min = Math.min(nums[r - 1][c - 1], min);
                nums[r][c] = min + Math.abs(ones[r] - zeros[c]);
            }
        }

        Long result = Long.MAX_VALUE;
        for (long min: nums[numOne - 1]) {
            if (min > 0) result = Math.min(result, min);
        }
        System.out.println(result);
    }
}
