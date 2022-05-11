package AtCoder.ABC.ABC186.ProblemD;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SumOfDifference {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = in.nextInt();

        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < N; i++)
            ans += (long) arr[i] * (i - (N - 1 - i));
        out.println(ans);
        out.flush();
    }
}
