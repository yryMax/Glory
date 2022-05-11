package CodeForce.P1141E;

import java.util.Scanner;

public class SuperheroBattle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long hp = scanner.nextLong();
        int length = scanner.nextInt();
        int[] minutes = new int[length + 1];
        long[] sum = new long[length + 1];
        long maxSum = Long.MAX_VALUE;
        for (int i = 1; i <= length; i++) {
            minutes[i] = scanner.nextInt();
            sum[i] = minutes[i] + sum[i - 1];
            maxSum = Math.min(sum[i], maxSum);
        }
        long result = 0;
        if (hp + maxSum > 0 && sum[length] >= 0) {
            System.out.println(-1);
            return;
        }
        if (-sum[length] != 0 && hp + maxSum > 0) {
            long times = (long) Math.ceil((hp + maxSum + 0.0) / -sum[length]);
            result += times * length;
            hp -= -sum[length] * times;
        }
        for (int i = 1; i <= length; i++) {
            if (sum[i] + hp <= 0) {
                result += i;
                System.out.println(result);
                return;
            }
        }
        System.out.println(-1);
    }
}
