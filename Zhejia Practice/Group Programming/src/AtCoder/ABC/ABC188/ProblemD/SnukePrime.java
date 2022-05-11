package AtCoder.ABC.ABC188.ProblemD;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SnukePrime {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int C = in.nextInt();
        int[][] services = new int[N][3];
        int[][] all = new int[2 * N][3];
        for (int i = 0; i < N; i++) {
            services[i][0] = in.nextInt();
            services[i][1] = in.nextInt();
            services[i][2] = in.nextInt();
            all[2 * i][0] = services[i][0];
            all[2 * i + 1][0] = services[i][1];
            all[2 * i][1] = i;
            all[2 * i + 1][1] = i;
            all[2 * i][2] = 0;
            all[2 * i + 1][2] = 1;
        }

        Arrays.sort(all, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) return a[0] - b[0];
                else return a[2] - b[2];
            }
        });
        long sum = 0;
        long acc = services[all[0][1]][2];
        for (int i = 1; i < 2 * N; i++) {
            int numDays = all[i][0] - all[i - 1][0];
            sum += Math.min(C, acc) * numDays;
            if (all[i][2] == 1 && all[i - 1][2] == 0) sum += Math.min(C, acc);
            else if (all[i][2] == 0 && all[i - 1][2] == 1) sum -= Math.min(C, acc);
            acc += (all[i][2] == 0) ? services[all[i][1]][2] : -services[all[i][1]][2];
        }
        System.out.println(sum);
    }
}
