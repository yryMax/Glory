package CodeForce.P1579E2;

import java.util.*;


public class ArrayOptimizationByDeque_BinaryIndexedTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTest = scanner.nextInt();
        while (numTest-- > 0) {
            int arrLength = scanner.nextInt();
            int[] arr = new int[arrLength];
            for (int i = 0; i < arrLength; i++) arr[i] = scanner.nextInt();
            optimisation(arr);
        }
    }

    private static void optimisation(int[] arr) {
        discrete(arr);
        int max = (int) Arrays.stream(arr).distinct().count();
        int in = 0;
        long total = 0;
        int[] data = new int[max];
        int[] same = new int[max];
        for (int i = 0; i < arr.length; i++) {
            long numSmallerE = query(data, arr[i]);
            total += Math.min(numSmallerE - same[arr[i]], in - numSmallerE);
            update(data, arr[i]);
            same[arr[i]]++;
            in++;
        }
        System.out.println(total);
    }

    private static void discrete(int[] arr) {
        int[] discrete = new int[(int) Arrays.stream(arr).distinct().count()];
        int[] temp = new int[arr.length];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        Arrays.sort(temp);
        discrete[0] = temp[0];
        int prev = discrete[0];
        int m = 1;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] != prev) {
                discrete[m++] = temp[i];
                prev = temp[i];
            }
        }
        for (int i = 0; i < arr.length; i++)
            arr[i] = Arrays.binarySearch(discrete, arr[i]);
    }

    private static long query(int[] data, int index) {
        long out = 0;
        while (index != 0) {
            out += data[index];
            index -= lowBit(index);
        }
        out += data[0];
        return out;
    }

    private static void update(int[] data, int index) {
        if (index == 0) {
            data[0]++;
            return;
        }
        do {
            data[index]++;
            index += lowBit(index);
        } while (index < data.length);
    }

    private static int lowBit(int index) {
        return index & (-index);
    }
}
