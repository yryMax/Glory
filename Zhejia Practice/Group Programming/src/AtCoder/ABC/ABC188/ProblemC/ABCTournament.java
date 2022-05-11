package AtCoder.ABC.ABC188.ProblemC;

import java.util.Scanner;

public class ABCTournament {
    static int N;
    static int[] A;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        A = new int[1 << N];
        for (int i = 0; i < (1 << N); i++)
            A[i] = in.nextInt();

        int index1 = 0;
        int max1 = Integer.MIN_VALUE;
        for (int i = 0; i < (1 << (N - 1)); i++) {
            if (A[i] > max1) {
                index1 = i;
                max1 = A[i];
            }
        }

        int index2 = 0;
        int max2 = Integer.MIN_VALUE;
        for (int i = (1 << (N - 1)); i < (1 << N); i++) {
            if (A[i] > max2) {
                index2 = i;
                max2 = A[i];
            }
        }

        System.out.println((max1 > max2) ? index2 + 1: index1 + 1);
    }
}
