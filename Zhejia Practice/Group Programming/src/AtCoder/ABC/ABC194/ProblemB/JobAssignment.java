package AtCoder.ABC.ABC194.ProblemB;

import java.util.Scanner;

public class JobAssignment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] times = new int[N][3];
        int aMin = Integer.MAX_VALUE;
        int a_index = -1;
        int bMin = Integer.MAX_VALUE;
        int b_index = -1;
        int sumMin = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            times[i][0] = in.nextInt();
            times[i][1] = in.nextInt();
            times[i][2] = times[i][0] + times[i][1];
            sumMin = Math.min(sumMin, times[i][2]);
        }
        for (int i = 0; i < N; i++) {
            if (times[i][0] < aMin) {
                aMin = times[i][0];
                a_index = i;
            }
            if (times[i][1] < bMin) {
                bMin = times[i][1];
                b_index = i;
            }
        }

        int ans = 0;
        if (a_index == b_index) {
            int secondB = Integer.MAX_VALUE;
            int secondA = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (i == a_index) continue;
                secondB = Math.min(secondB, times[i][1]);
            }
            for (int i = 0; i < N; i++) {
                if (i == b_index) continue;
                secondA = Math.min(secondA, times[i][0]);
            }
            ans = Math.min(Math.max(aMin,secondB), Math.max(bMin, secondA));
        } else ans = Math.max(aMin, bMin);

        System.out.println(Math.min(ans, sumMin));
    }
}
