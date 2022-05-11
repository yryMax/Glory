package AcWing.P273;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MakingTheGrade {
    static Integer[] unique;

    private static int findOriginal(int newIndex) {
        return unique[newIndex];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int N = in.nextInt();
        int[] arr = new int[N];
        Set<Integer> uniqueSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
            uniqueSet.add(arr[i]);
        }

        unique = uniqueSet.toArray(new Integer[0]);
        Arrays.sort(unique);
        int limit = unique.length;
        long[][] dpIncreasing = new long[N][limit];
        for (int i = 0; i < limit; i++) dpIncreasing[0][i] = Math.abs((long) findOriginal(i) - arr[0]);
        for (int last = 1; last < N; last++) {
            long currMin = dpIncreasing[last - 1][0];
            dpIncreasing[last][0] = currMin + Math.abs((long) findOriginal(0) - arr[last]);

            for (int choice = 1; choice < limit; choice++) {
                currMin = Math.min(currMin, dpIncreasing[last - 1][choice]);
                dpIncreasing[last][choice] = currMin + Math.abs((long) findOriginal(choice) - arr[last]);
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < limit; i++) ans = Math.min(ans, dpIncreasing[N - 1][i]);

        long[][] dpDecreasing = new long[N][limit];
        for (int i = 0; i < limit; i++) dpDecreasing[0][i] = Math.abs((long) findOriginal(i) - arr[0]);
        for (int last = 1; last < N; last++) {
            long currMin = dpDecreasing[last - 1][limit - 1];
            dpDecreasing[last][limit - 1] = currMin + Math.abs(arr[last] - findOriginal(limit - 1));
            for (int choice = limit - 2; choice >= 0; choice--) {
                currMin = Math.min(currMin, dpDecreasing[last - 1][choice]);
                dpDecreasing[last][choice] = currMin + Math.abs((long) findOriginal(choice) - arr[last]);
            }
        }
        for (int i = 0; i < limit; i++) ans = Math.min(ans, dpDecreasing[N - 1][i]);
        System.out.println(ans);

    }
}
