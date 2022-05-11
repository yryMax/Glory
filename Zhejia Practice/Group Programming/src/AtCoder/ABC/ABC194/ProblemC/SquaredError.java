package AtCoder.ABC.ABC194.ProblemC;

import java.util.Arrays;
import java.util.Scanner;

public class SquaredError {
    static int N;
    static int[] nums;
    static long ans;

    private static void calculate(int left, int right) {
        if (left == right) return;
        if (left + 1 == right) {
            ans += Math.pow(nums[right] - nums[left], 2);
            return;
        }

        int mid = (left + right) >> 1;
        calculate(left, mid - 1);
        calculate(mid, right);
        long sumLeft = 0;
        long powerLeft = 0;
        for (int i = left; i < mid; i++) {
            sumLeft += (nums[mid - 1] - nums[i]);
            powerLeft += Math.pow(nums[mid - 1] - nums[i], 2);
        }

        // Right
        for (int i = mid; i <= right; i++)
            ans += ((mid - left) * Math.pow(nums[i] - nums[mid - 1], 2) + 2 * (nums[i] - nums[mid - 1]) * sumLeft + powerLeft);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = in.nextInt();
        Arrays.sort(nums);
        calculate(0, N - 1);
        System.out.println(ans);
    }
}
