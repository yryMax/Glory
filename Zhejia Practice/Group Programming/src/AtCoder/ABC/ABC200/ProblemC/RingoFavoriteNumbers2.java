package AtCoder.ABC.ABC200.ProblemC;

import java.util.Arrays;
import java.util.Scanner;

public class RingoFavoriteNumbers2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] nums = new int[N];
        int[] buckets = new int[200];
        for (int i = 0; i < N; i++) nums[i] = in.nextInt();
        for (int i = 0; i < N; i++)
            buckets[nums[i] % 200]++;
        long count = 0;
        for (int i = 0; i < 200; i++)
            count += (buckets[i] > 0) ? (long) buckets[i] * (buckets[i] - 1) / 2 : 0;
        System.out.println(count);
    }
}
