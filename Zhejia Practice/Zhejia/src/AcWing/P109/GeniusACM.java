package AcWing.P109;

import java.util.Scanner;
import java.util.Arrays;
public class GeniusACM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTest = scanner.nextInt();
        while (numTest-- > 0) {
            int num = scanner.nextInt();
            int choices = scanner.nextInt();
            long max = scanner.nextLong();
            int[] numbers = new int[num];
            for (int i = 0; i < num; i++) numbers[i] = scanner.nextInt();
            int result = 0;
            int start = 0;

            Main:
            while (start < num) {
                int previous = 0;
                int step = 1;

                boolean noExist = false;
                while (step != 0) {

                    int toIndex = Math.min(start + previous + step, num - 1);
                    int[] copy = Arrays.copyOfRange(numbers, start, toIndex + 1);
                    Arrays.sort(numbers, start + previous + 1, toIndex + 1);
                    mergeArray(numbers, start, start + previous, start + previous + 1, toIndex);
                    long newSum = checkVal(numbers, start, toIndex, choices);
                    if (!noExist && newSum <= max) {
                        if (toIndex == num - 1) {
                            result++;
                            break Main;
                        }
                        previous += Math.min(step, num - 1 - previous - start);
                        step = Math.min(num - 1 - previous - start, 2 * step);
                    } else if (newSum <= max) {
                        if (toIndex == num - 1) {
                            result++;
                            break Main;
                        }
                        previous += step;
                        step = step / 2;
                    } else {
                        noExist = true;
                        System.arraycopy(copy, 0, numbers, start, toIndex + 1 - start);
                        step /= 2;
                    }
                }
                result += 1;
                start = start + previous + 1;

            }
            System.out.println(result);

        }
    }

    private static long checkVal(int[] numbers, int from, int to, int choices) {
        long out = 0;
        for (int i = 0; i <= Math.min(choices - 1, (to - from - 1) / 2); i++)
            out += (long) (numbers[from + i] - numbers[to - i]) * (numbers[from + i] - numbers[to - i]);
        return out;
    }

    private static boolean checkIncrease(int[] numbers, int from, int to) {
        for (int i = from; i < to; i++)
            if (numbers[i + 1] - numbers[i] < 0) return false;
        return true;
    }

    private static void mergeArray(int[] arr, int start1, int end1, int start2, int end2) {
        int length = end1 - start1 + 1 + end2 - start2 + 1;
        int[] temp = new int[length];
        int left = start1;
        int right = start2;
        int ind = 0;
        while (left <= end1 && right <= end2) {
            if (arr[left] <= arr[right]) temp[ind++] = arr[left++];
            else temp[ind++] = arr[right++];
        }
        while (left <= end1) temp[ind++] = arr[left++];
        while (right <= end2) temp[ind++] = arr[right++];
        System.arraycopy(temp, 0, arr, start1, length);
    }
}
