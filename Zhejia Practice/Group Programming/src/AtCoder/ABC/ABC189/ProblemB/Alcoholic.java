package AtCoder.ABC.ABC189.ProblemB;

import java.util.Scanner;

public class Alcoholic {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int max = in.nextInt() * 100;
        int[] alcohol = new int[N + 1];
        int[] acc = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            alcohol[i] =  in.nextInt() * in.nextInt();
            acc[i] = acc[i - 1] + alcohol[i];
        }
        int left = 0;
        int right = N;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (acc[mid] <= max) left = mid; else right = mid - 1;
        }
        System.out.println((left + 1 <= N) ? left + 1 : -1);
    }
}
