package CodeForce.P1509C;

import java.util.Arrays;
import java.util.Scanner;

public class TheSportsFestival {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        long[] numbers = new long[num];
        for (int i = 0; i < num; i++)
            numbers[i] = scanner.nextLong();
        Arrays.sort(numbers);
        long[][] dp = new long[num][num];
        for (int i = 0; i < num; i++) dp[i][i] = 0;
        for (int r = 1; r < num; r++) dp[r][r - 1] = numbers[r] - numbers[r - 1];
        for (int offset = 2; offset < num; offset++)
            for (int row = offset; row < num; row++)
                dp[row][row - offset] = Math.min(dp[row - 1][row - offset], dp[row][row - offset + 1]) + numbers[row] - numbers[row - offset];
        System.out.println(dp[num - 1][0]);
    }


}
