package AtCoder.ABC.ABC190.ProblemF;

import java.util.Scanner;

public class ShiftAndInversions {
    static int N;
    static int[] numbers;
    static int[] clone;
    static int[] sorted;
    static long countInverse;

    private static void numInverse(int left, int right) {
        if (left == right) return;
        if (right - left == 1) {
            if (clone[left] > clone[right]) {
                int temp = clone[right];
                clone[right] = clone[left];
                clone[left] = temp;
                countInverse++;
            }
            return;
        }
        int mid = (left + right) >> 1;
        numInverse(left, mid);
        numInverse(mid + 1, right);
        int pointL = left;
        int pointR = mid + 1;
        for (int i = left; i <= right; i++) {
            if (pointR > right || (pointL <= mid && clone[pointL] <= clone[pointR])) sorted[i] = clone[pointL++];
            else {
                sorted[i] = clone[pointR++];
                countInverse += mid - pointL + 1;
            }
        }
        for (int i = left; i <= right; i++) clone[i] = sorted[i];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        numbers = new int[N];
        clone = new int[N];
        sorted = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = in.nextInt();
            clone[i] = numbers[i];
        }
        countInverse = 0;
        numInverse(0, N - 1);
        System.out.println(countInverse);
        for (int i = 1; i < N; i++) {
            countInverse += (N - 1 - 2L * numbers[i - 1]);
            System.out.println(countInverse);
        }

    }
}
