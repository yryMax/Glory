package AtCoder.ABC.ABC188.ProblemB;

import java.util.Scanner;

public class Orthogonality {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = in.nextInt();
        for (int i = 0; i < N; i++)
            B[i] = in.nextInt();

        long sum = 0;
        for (int i = 0; i < N; i++)
            sum += (long) A[i] * B[i];
        System.out.println(sum == 0 ? "Yes" : "No");
    }
}
