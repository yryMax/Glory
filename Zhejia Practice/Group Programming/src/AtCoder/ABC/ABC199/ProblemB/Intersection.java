package AtCoder.ABC.ABC199.ProblemB;

import java.util.Scanner;

public class Intersection {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] nums = new int[N + 1][N + 1];
        int[] ans = new int[2];
        for (int i = 1; i <= N; i++)
            nums[i][0] = in.nextInt();
        for (int i = 1; i <= N; i++)
            nums[i][1] = in.nextInt();
        for (int i = 1; i <= N; i++) {
            ans = intersect(ans, nums[i]);
            if (ans[0] == -1) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(ans[1] - ans[0] + 1);

    }

    public static int[] intersect(int[] a, int[] b) {
        if (a[0] == 0 && a[1] == 0) return new int[] {b[0], b[1]};
        if (a[1] < b[0] || b[1] < a[0]) return new int[] {-1, -1};
        return new int[] {Math.max(a[0], b[0]), Math.min(a[1], b[1])};
    }
}
