package AtCoder.ABC.ABC187.ProblemB;

import java.util.Scanner;

public class GentlePairs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }
        int ans = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (Math.abs(((double) (arr[i][1] - arr[j][1])) / (arr[i][0] - arr[j][0])) <= 1)
                    ans++;
            }
        }
        System.out.println(ans);
    }
}
